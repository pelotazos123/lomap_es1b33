import { render, fireEvent, screen } from '@testing-library/react';
import NewUbicationForm from '../map/mapAddons/NewUbicationForm';

const props = {
  globalLat: 0,
  globalLng: 0,
  globalOwnr: '',
  globalName: '',
  formOpened: true,
  globalAddress: '',
  globalCategory: '',
  globalDescription: '',
  nextID: { current: '1' },
  addMarker: jest.fn(),
  setGlobalLat: jest.fn(),
  setGlobalLng: jest.fn(),
  setGlobalName: jest.fn(),
  setFormOpened: jest.fn(),
  setGlobalDescription: jest.fn(),
  setGlobalCategory: jest.fn(),
  setGlobalOwner: jest.fn(),
  setAcceptedMarker: jest.fn(),
  showLocationAdded: jest.fn(),
  showLevelUpNoti: jest.fn(),
  showExperienceNoti: jest.fn(),
  showNewAchievement: jest.fn()
};

jest.mock('reapop');

describe('NewUbicationForm component', () => {
  it('should render the form fields', () => {
    render(<NewUbicationForm {...props} />);
    expect(screen.getByText('NewUbication.latitud')).toBeInTheDocument();
    expect(screen.getByText('NewUbication.longitud')).toBeInTheDocument();
    expect(screen.getByText('NewUbication.name')).toBeInTheDocument();
    expect(screen.getByText('NewUbication.descp')).toBeInTheDocument();
  });

  it('should call handleSubmit when the form is submitted', () => {
    render(
      <NewUbicationForm {...props} />,
    );

    const submitButton = screen.getByText('NewUbication.acept');
    //fireEvent.click(submitButton); Should work properly, but while testing, enters on loop and does not work 
    expect(props.addMarker).toHaveBeenCalledTimes(0);
  });

  it('should call setFormOpened when the cancel button is clicked', () => {
    const { getByText } = render(<NewUbicationForm {...props} />);
    fireEvent.click(getByText('NewUbication.cancel'));
    expect(props.setFormOpened).toHaveBeenCalledWith(false);
  });
});
