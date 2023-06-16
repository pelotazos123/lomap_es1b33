import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import BadgesView from '../stats/BadgesView';

describe('BadgesView', () => {
  test('renders badge view unlocked', () => {
    // Set up initial props
    const initialProps = {
      isBadgeWindowOpen: true,
      setBadgeWindowOpen: jest.fn(),
      imageToShow: 'badge1',
    };

    render(<BadgesView {...initialProps} />);

    const badgeViewDialog = screen.getByText('Achievement.statusU');
    expect(badgeViewDialog).toBeInTheDocument();

    const badgeImage = screen.getByAltText('logoIcon');
    expect(badgeImage).toBeInTheDocument();
    expect(badgeImage).toHaveAttribute('src', '/img/achievements/badge1.png');
  });

  test('renders different content for "Dis" image', () => {
    const initialProps = {
      isBadgeWindowOpen: true,
      setBadgeWindowOpen: jest.fn(),
      imageToShow: 'badge2Dis',
    };

    render(<BadgesView {...initialProps} />);

    const badgeStatus = screen.getByText('Achievement.statusL');
    const badgeDescription = screen.getByText('Achievement.badge2');
    expect(badgeStatus).toBeInTheDocument();
    expect(badgeDescription).toBeInTheDocument();
  });
});
