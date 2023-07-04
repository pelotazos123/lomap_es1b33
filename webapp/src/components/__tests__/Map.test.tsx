import { render, screen } from '@testing-library/react';
import { MarkerContextProvider } from '../../context/MarkerContextProvider';
import { loadMapApi } from '../../utils/GoogleMapsUtils';
import LoMap from '../map/Map';
import { IPMarker, Comment } from "../../shared/SharedTypes";

const setupGoogleMock = () => {
  global.window.google = {
    maps: {
      Geocoder: class {
        public async geocode() {
          return Promise.resolve()
        }
      },
      LatLng: jest.fn(),
      Map: class {
        public async getCenter() {
          return Promise.resolve()
        }
      },
      Marker: class {
        public async addListener() {
          // TODO        
        }
      },
      event: { removeListener: jest.fn(),
      addListener: jest.fn() },

      GeocoderStatus: {
        ERROR: 'ERROR',
        INVALID_REQUEST: 'INVALID_REQUEST',
        OK: 'OK',
        OVER_QUERY_LIMIT: 'OVER_QUERY_LIMIT',
        REQUEST_DENIED: 'REQUEST_DENIED',
        UNKNOWN_ERROR: 'UNKNOWN_ERROR',
        ZERO_RESULTS: 'ZERO_RESULTS',
      },
      MapTypeId: {
        ROADMAP: 'ROADMAP'
      }
    } as any,
  };
};


// Mock the useSession hook
jest.mock('@inrupt/solid-ui-react', () => ({
  __esModule: true,
  useSession: () => ({
    session: { info: { isLoggedIn: true }, onLogout: jest.fn },
  }),
}));

describe('Map component', () => {
  setupGoogleMock();

  const comment: Comment = {
    author: "autor",
    text: "ola"
  }

  const marker1: IPMarker = {
    id: "1",
    owner: "me",
    date: new Date(),
    lat: 0,
    lng: 0,
    name: "Test marker 1",
    webId: "https://example.com/user1#me",
    address: "123 Main St",
    category: "Test",
    isPublic: false,
    ratings: [0],
    comments: [comment],
    description: "This is a test marker",
  };

  const markerList = [marker1];

  const props: any = {
    globalLat: 0,
    globalLng: 0,
    globalName: '',
    globalMode: '',
    globalAddress: '',
    globalCategory: '',
    acceptedMarker: false,
    globalFilterName: '',
    mapTypeControl: true,
    globalDescription: '',
    mapType: 'ROADMAP',
    globalFilterCategories: [],
    nextID: { current: '' },
    tMarkers: markerList,
    setGlobalLat: jest.fn(),
    setGlobalLng: jest.fn(),
    setMarkerShown: jest.fn(),
    setDetailedIWOpen: jest.fn(),
    setGlobalAddress: jest.fn(),
    setAcceptedMarker: jest.fn(),
  };

  it('should render the component', () => {
    const googleMapScript = loadMapApi();
    googleMapScript.addEventListener('load', function () {
      render(<MarkerContextProvider><LoMap {...props} /></MarkerContextProvider>);
      const mapElement = screen.getByTestId('map');
      expect(mapElement).toBeInTheDocument();
    });
  });

  it('should render the component', () => {
    const {container} = render(<LoMap {...props}/ >);
    const mapElement = container.getElementsByClassName('map')
    expect(mapElement[0]).toBeInTheDocument();
  });
});