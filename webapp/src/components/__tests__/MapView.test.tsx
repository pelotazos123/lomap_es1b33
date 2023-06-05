import { getByTestId, getByText, render, screen } from '@testing-library/react';
import MapView from '../map/mapAddons/MapView';
import userEvent from '@testing-library/user-event';

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

                }
            },
            getCenter: jest.fn(),
            event: { removeListener: jest.fn() },
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

describe('MapView component', () => {
    jest.mock('@inrupt/solid-ui-react', () => ({
        __esModule: true,
        useSession: () => ({
            session: { info: { isLoggedIn: true, webId: "https://example.com/user1#me" }, onLogout: jest.fn },
        }),
    }));

    setupGoogleMock();

    test('renders MapView component', () => {
        render(
            <MapView />
        );
        const test = screen.getByText("MapView.filtros")
        expect(test).toBeInTheDocument();
    });

    test('renders the filter dialog when filter button is clicked', () => {
        render(
            <MapView />
        );
        const filterButton = screen.getByText('MapView.filtros');
        userEvent.click(filterButton);
        const filterDialog = screen.getByRole('dialog');
        expect(filterDialog).toBeInTheDocument();
    });

    test('displays the name and category filter input fields', () => {
        render(
            <MapView />
        );
        const filterButton = screen.getByText('MapView.filtros');
        userEvent.click(filterButton);
        expect(screen.getByText('MapView.filtra')).toBeInTheDocument();
    });
});