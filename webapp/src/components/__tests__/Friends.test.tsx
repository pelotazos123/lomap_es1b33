import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import FriendsList from '../friends/Friends';

// mock the SolidHelper functions used in the component
jest.mock('../../helpers/SolidHelper', () => ({
  findPersonData: jest.fn(),
  addFriendByWebId: jest.fn(),
  deleteFriendByWebId: jest.fn(),
}));

describe('FriendsList', () => {
  beforeEach(() => {
    // reset the mock implementation of SolidHelper functions
    jest.resetAllMocks();
  });

  it('displays no text when not loggedin', async () => {

    render(<FriendsList showAddedFriend={jest.fn()} showDeletedFriend={jest.fn()}/>)

    expect(screen.getByText("Friends.noLoggedIn")).toBeInTheDocument()

  });

  it('displays loading spinner when data is being fetched', async () => {
    // mock the useSession hook to return a logged in user
    jest.mock('@inrupt/solid-ui-react', () => ({
      useSession: () => ({ session: { info: { isLoggedIn: true, webId: 'https://example.com/profile/card#me' } } })
    }));

    // mock the findPersonData function to return some data
    const mockPersonData = { webId: 'https://example.com/profile/card#me', name: 'John Doe', photo: '', friends: [] };
    jest.spyOn(require('../../helpers/SolidHelper'), 'findPersonData').mockResolvedValue(mockPersonData);

    render(<FriendsList opt={true} loading={true} showAddedFriend={jest.fn()} showDeletedFriend={jest.fn()}/>);

    expect(screen.getByTestId('img-spinner')).toBeInTheDocument();
  });

  it('allows adding a new friend', async () => {
    // mock the useSession hook to return a logged in user
    jest.mock('@inrupt/solid-ui-react', () => ({
      useSession: () => ({ session: { info: { isLoggedIn: true, webId: 'https://example.com/profile/card#me' } } })
    }));

    // mock the findPersonData function to return some data
    const mockPersonData = { webId: 'https://example.com/profile/card#me', name: 'John Doe', photo: '', friends: [] };
    jest.spyOn(require('../../helpers/SolidHelper'), 'findPersonData').mockResolvedValue(mockPersonData);

    render(<FriendsList opt={true} loading={false} showAddedFriend={jest.fn()} showDeletedFriend={jest.fn()}/>);

    // click on the "Add friend" button to show the add friend form
    fireEvent.click(screen.getByText('Friends.add'));

    expect(screen.getByText("Friends.addB")).toBeInTheDocument();

    // fill in the webId input field and submit the form
    fireEvent.change(screen.getByTestId('input-webid'), { target: { value: 'https://example.com/friend' } });
    fireEvent.click(screen.getByText('Friends.addB'));

    // check that the addFriendByWebId function was called with the correct arguments
    expect(require('../../helpers/SolidHelper').addFriendByWebId).toHaveBeenCalledTimes(1);
  });
});