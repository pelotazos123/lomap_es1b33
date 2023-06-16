    import './Map.css';
    import { v4 as uuid } from 'uuid';
    import { IPMarker } from '../../shared/SharedTypes';
    import { useSession } from '@inrupt/solid-ui-react';
    import { MarkerContext, Types } from '../../context/MarkerContextProvider';
    import React, { useEffect, useRef, useState, useContext, MutableRefObject } from 'react';
    import { useTranslation } from 'react-i18next';

    interface IMarker {
        name: string;
        address: string;
        category: string;
        description: string;
        latLng: GoogleLatLng;
    }

    interface ICouple {
        marker: GoogleMarker;
        //infoWindow: GoogleInfoWindow;
    }

    type GoogleMap = google.maps.Map;
    type GoogleLatLng = google.maps.LatLng;
    type GoogleMarker = google.maps.Marker;

    interface IMapProps {
        globalLat: number;
        globalLng: number;
        globalName: string;
        globalMode: string;
        globalAddress: string;
        globalCategory: string;
        acceptedMarker: boolean;
        globalFilterName: string;
        mapTypeControl?: boolean;
        globalDescription: string;
        mapType: google.maps.MapTypeId;
        globalFilterCategories: string[];
        nextID: MutableRefObject<string>;
        friendsMap: boolean;
        isNewUbiOpen: boolean;
        setGlobalLat: (globalLat: number) => void;
        setGlobalLng: (globalLng: number) => void;
        setMarkerShown: (marker: IPMarker) => void;
        setDetailedIWOpen: (open: boolean) => void;
        setGlobalAddress: (globalAddress: string) => void;
        setAcceptedMarker: (acceptedMarker: boolean) => void;
        setFriendsMap: (friendsMap: boolean) => void;
        showLocationDeleted: () => void;
    }

    // Aclaración: los comentarios en los useEffect deshabilitan warnings.
    // Elegí esta opción pues intentar solucionarlos solo llevaría a
    // quebraderos de cabeza y/o bucles infinitos, lo que se escapa
    // del ámbito de esta asignatura.

const LoMap: React.FC<IMapProps> = (props, {showLocationDeleted}) => {
    const { session } = useSession();
    const ref = useRef<HTMLDivElement>(null);                               // Contenedor HTML del mapa
    const [map, setMap] = useState<GoogleMap>();                            // useState para conservar la referencia al mapa
    const [marker, setMarker] = useState<IMarker>();                        // useState para comunicar el listener con el método
    const listenerRef = useRef<google.maps.MapsEventListener>();
    const { state: markers, dispatch } = useContext(MarkerContext);         // Proveedor de los marcadores en el POD
    const [lastAddedCouple, setLastAddedCouple] = useState<ICouple>();      // Último par (marcador, ventana de información) añadidos al mapa
    const [googleMarkers, setGoogleMarkers] = useState<GoogleMarker[]>([]); // useState para conservar referencias a todos los marcadores que se crean
    const [isLoaded, setLoaded] = useState<boolean>(false);

    const DEFAULT_MAP_ZOOM = 15;

    const { t } = useTranslation("translation");

        /**
         * Inicia y/o inicializa el mapa
         */
        const startMap = (): void => {
            if (!map) {
                defaultMapStart();           // Si el mapa no está iniciado, lo inicia
            } else {
                if (session.info.isLoggedIn) {
                    setLoaded(true);
                    addInitMarker();                // Añade un marcador para evitar problemas con los Spinner del formulario
                    initEventListener();            // Inicia el listener encargado de escuchar clicks en el mapa
                }
                addHomeMarker(map.getCenter()); // Añade un marcador en la posición actual del usuario
            }
        };

        /**
         * UseEffect encargado de iniciar y/o inicializar el mapa
         */
        useEffect(() => {
            startMap();
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [map]);

        /**
         * Intenta conseguir la posición del usuario para iniciar y centrar el mapa en dicha posición
         */
        const defaultMapStart = (): void => {
            
            const defaultAddress = new google.maps.LatLng(43.5276892, -5.6355573);  // Posición por defecto en caso de problemas
            if (navigator.geolocation) {                                            // Si se puede usar la geolocalización
                navigator.geolocation.getCurrentPosition(({ coords }) => {

                    initMap(DEFAULT_MAP_ZOOM, new google.maps.LatLng(coords.latitude, coords.longitude))

                }, () => {                                                          // En caso de error
                    initMap(DEFAULT_MAP_ZOOM, defaultAddress);
                })
            } else {
                initMap(DEFAULT_MAP_ZOOM, defaultAddress);
            }
        };

        /**
         * Inicia el listener encargado de escuchar clicks en el mapa
         */
        const initEventListener = () => {
            
            listenerRef.current = google.maps.event.addListener(map!, 'click', async function (e) { 
                props.setGlobalLat(e.latLng.lat());                           
                props.setGlobalLng(e.latLng.lng());
                
                setMarker({                                                   
                    address: "",
                    latLng: e.latLng,
                    name: "Placeholder nombre",                               
                    category: "Placeholder categoría",
                    description: "Placeholder descripción"
                })
                // eslint-disable-next-line @typescript-eslint/no-misused-promises
            })            
        };

        session.onLogout(() => {
            lastAddedCouple?.marker.setMap(null);
            google.maps.event.removeListener(listenerRef.current!);
        });

        /**
         * UseEffect encargado de añadir un marcador cuando se actualiza el useState "marker"
         */
        useEffect(() => {
            if (marker) {
                marker.name = formatName(); 
                marker.category = props.globalCategory;
                marker.description = formatDescription();

                addMarker(marker);          
            }
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [marker]);

        /**
         * Devuelve un nombre, si el campo del formulario está relleno
         * @returns string nombre
         */
        const formatName = (): string => {
            return props.globalName ? props.globalName : t("MapView.noname");
        }

        /**
         * Devuelve una descripción, si el campo del formulario está relleno
         * @returns string descripción
         */
        const formatDescription = (): string => {
            return props.globalDescription ? props.globalDescription : t("MapView.nodescp");
        }

        /**
         * Añade un marcador al mapa
         * @param iMarker parámetros del marcador a añadir
         */
        const addMarker = (iMarker: IMarker): void => {
            if (lastAddedCouple) {
                lastAddedCouple.marker.setMap(null);                            
            }

            setLastAddedCouple(generateMarker(iMarker, props.nextID.current));  
        };

        /**
         * Genera un par (marcador, ventana de información)
         * @param notAddedMarker parámetros del marcador a añadir 
         * @param id id del marcador correspondiente en el POD 
         * @returns ICouple par(marcador, ventana de información) 
         */
        const generateMarker = (notAddedMarker: IMarker, id: string): ICouple => {
            const marker: GoogleMarker = new google.maps.Marker({
                position: notAddedMarker.latLng,                             
                icon: "/img/marker.png",                                     
                map: map                                                     
            });

            marker.addListener('click', () => {                             
                const detailedMarker = markers.find(marker => marker.id === id);
                if (detailedMarker) {
                    props.setMarkerShown(detailedMarker);
                    props.setDetailedIWOpen(true);
                }
            });

            marker.addListener('rightclick', () => {
                if (!props.friendsMap) {
                    if (markers.find(marker => marker.id === id)) {
                        props.setDetailedIWOpen(false);

                        marker.setMap(null);
                        dispatch({ type: Types.DELETE_MARKER, payload: { id: id } });
                    }
                    props.showLocationDeleted();
                } 
            });

            setGoogleMarkers(googleMarkers => [...googleMarkers, marker]);   

            return { marker };
        }

        /**
         * Añade un marcador; al hacer click, el mapa se centra en él.
         * @param location coordenadas del marcador
         */
        const addHomeMarker = (location: GoogleLatLng): void => {
            const homeMarkerConst: GoogleMarker = new google.maps.Marker({ 
                icon: "/img/home.png",
                position: location,
                map: map
            });

            homeMarkerConst.addListener('click', () => {                   
                map?.panTo(location);                                      
                map?.setZoom(DEFAULT_MAP_ZOOM);                                           
            });
        };

        /**
         * Crea un marcador con las mismas características que el último marcador generado
         */
        const addInitMarker = (): void => {
            setMarker({
                name: "Placeholder nombre",
                address: props.globalAddress,
                category: "Placeholder categoría",
                description: "Placeholder descripción",
                latLng: new google.maps.LatLng(props.globalLat, props.globalLng)
            })
        }

        /**
         * UseEffect encargado de actualizar el último marcador añadido cuando se cambian
         * las coordenadas en el formulario
         */
        useEffect(() => {
            const location = new google.maps.LatLng(props.globalLat, props.globalLng);      
            
            coordinateToAddress(location).then(address => props.setGlobalAddress(address)).catch(error => console.error(error)); 

            if (lastAddedCouple) {
                lastAddedCouple.marker.setPosition(location);                               
            }
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [props.globalLat, props.globalLng]);

        /**
         * Función auxiliar para evitar tener que guardar referencias
         * a los listener del último marcador
         */
        const updateMarkerListeners = () => {
            const updatedMarker = markers.find(marker => marker.id === props.nextID.current)!; 
            generateMarker(parseMarker(updatedMarker), updatedMarker.id);                    
            props.nextID.current = uuid();                                             

            lastAddedCouple?.marker.setMap(null);                                          
        }

        /**
         * UseEffect atento a la propiedad "marcador aceptado"
         */
        useEffect(() => {
            if (lastAddedCouple && props.acceptedMarker) {
                updateMarkerListeners();                                    

                lastAddedCouple.marker = new google.maps.Marker();          

                props.setAcceptedMarker(false);                             

                addInitMarker();                                            
            }
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [props.acceptedMarker]);

        /**
         * UseEffect encargado de cambiar los marcadores del mapa 
         * en función de la opción seleccionada
         */
        useEffect(() => {
            deleteAllMarkers(); 

            switch (props.globalMode) {
                case 'M':
                    loadContext(); 
                    break;
                case 'A':
                    loadFriendMarkers();
                    break;
                case 'E':
                    break;
                default:
            }
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [props.globalMode, props.globalFilterName, props.globalFilterCategories, isLoaded]);

        /**
         * Borra todos los marcadores del mapa y vacía el useState correspondiente
         */
        const deleteAllMarkers = (): void => {
            googleMarkers.forEach((googleMarker) => {
                if (googleMarker !== lastAddedCouple?.marker) {  
                    googleMarker.setMap(null);                   
                }
            });

            setGoogleMarkers([]);
        }

        /**
         * Carga los marcadores del contexto
         */
        const loadContext = (): void => {
            markers.filter(m => m.webId === session.info.webId!
                && props.globalFilterCategories.includes(m.category)
                && m.name.includes(props.globalFilterName)).forEach((marker) => {
                    generateMarker(parseMarker(marker), marker.id); 
                })
        }

        const loadFriendMarkers = (): void => {
            markers.filter(m => m.webId !== session.info.webId!
                && props.globalFilterCategories.includes(m.category)
                && m.name.includes(props.globalFilterName)).forEach((marker) => {
                    generateMarker(parseMarker(marker), marker.id); 
                })
        }

    /**
     * Transforma la versión persistente de un marcador 
     * a su versión correspondiente en el mapa
     * @param iPMarker marcador persistente
     * @returns parámetros necesarios para generar un marcador
     */
    const parseMarker = (iPMarker: IPMarker): IMarker => {
        return {
            name: iPMarker.name,
            address: iPMarker.address,
            category: iPMarker.category,
            description: iPMarker.description,
            latLng: new google.maps.LatLng(iPMarker.lat, iPMarker.lng)
        };
    }

        /**
         * Usa geolocalización inversa para obtener la dirección de unas coordenadas
         * @param coordinate coordenadas
         * @returns dirección formateada
         */
        const coordinateToAddress = (coordinate: GoogleLatLng): Promise<string> => {
            const geocoder = new google.maps.Geocoder();
            return new Promise((resolve, reject) => {
                geocoder.geocode(
                    {
                        location: coordinate
                    },
                    (results, status) => {
                        if (status === google.maps.GeocoderStatus.OK) {                     
                            const formatedAddress = results[0].formatted_address;           
                            resolve(formatedAddress);
                        } else if (status === google.maps.GeocoderStatus.ZERO_RESULTS) {    
                            const formatedAddress = "Sin resultados";
                            resolve(formatedAddress);
                        } else {                                                            
                            reject(new Error(status));
                        }
                    }
                )
            })
        };

        /**
         * Inicia el mapa
         * @param zoomLevel zoom inicial
         * @param address dirección en la que centrar el mapa
         */
        const initMap = (zoomLevel: number, address: GoogleLatLng): void => {
            if (ref.current) {
                setMap(
                    new google.maps.Map(ref.current, {
                        zoom: zoomLevel,
                        center: address,
                        panControl: false,
                        zoomControl: true,
                        scaleControl: true,
                        rotateControl: false,
                        mapTypeId: props.mapType,
                        streetViewControl: false,
                        fullscreenControl: false,
                        draggableCursor: 'pointer',
                        gestureHandling: 'cooperative',
                        mapTypeControl: props.mapTypeControl,
                        clickableIcons: false,
                        styles: [
                            { elementType: "geometry", stylers: [{ color: "#242f3e" }] },
                            { elementType: "labels.text.stroke", stylers: [{ color: "#242f3e" }] },
                            { elementType: "labels.text.fill", stylers: [{ color: "#746855" }] },
                            {
                              featureType: "administrative.locality",
                              elementType: "labels.text.fill",
                              stylers: [{ color: "#d59563" }],
                            },
                            {
                              featureType: "poi",
                              stylers: [{visibility: 'off'}],
                            },
                            {
                              featureType: "road",
                              elementType: "geometry",
                              stylers: [{ color: "#38414e" }],
                            },
                            {
                              featureType: "road",
                              elementType: "geometry.stroke",
                              stylers: [{ color: "#212a37" }],
                            },
                            {
                              featureType: "road",
                              elementType: "labels.text.fill",
                              stylers: [{ color: "#9ca5b3" }],
                            },
                            {
                              featureType: "road.highway",
                              elementType: "geometry",
                              stylers: [{ visibility: 'off' }],
                            },
                            {
                              featureType: "road.highway",
                              elementType: "geometry.stroke",
                              stylers: [{ visibility: 'off' }],
                            },
                            {
                              featureType: "road.highway",
                              elementType: "labels.text.fill",
                              stylers: [{ visibility: 'off' }],
                            },
                            {
                              featureType: "transit",
                              elementType: "geometry",
                              stylers: [{ color: "#2f3948" }],
                            },
                            {
                              featureType: "transit.station",
                              elementType: "labels.text.fill",
                              stylers: [{ color: "#d59563" }],
                            },
                            {
                              featureType: "water",
                              elementType: "geometry",
                              stylers: [{ color: "#17263c" }],
                            },
                            {
                              featureType: "water",
                              elementType: "labels.text.fill",
                              stylers: [{ color: "#515c6d" }],
                            },
                            {
                              featureType: "water",
                              elementType: "labels.text.stroke",
                              stylers: [{ color: "#17263c" }],
                            },
                          ],
                    })
                );
            }
        };

        return (
                <div ref={ref} className="map" style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                    <img src="/img/loading-gif2.gif" alt="loading-map" style={{display: 'block'}}/>
                </div>
        );
    };

    export default LoMap;