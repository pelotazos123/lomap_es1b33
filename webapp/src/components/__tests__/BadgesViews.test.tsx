import React from 'react';
import { render } from '@testing-library/react';
import BadgesView from '../stats/BadgesView';

describe('BadgesView', () => {
  test('renders the badge view dialog', () => {
    const initialProps = {
      isBadgeWindowOpen: true,
      setBadgeWindowOpen: jest.fn(),
      imageToShow: 'badge1',
    };

    const { getByText, getByAltText } = render(<BadgesView {...initialProps} />);

    const dialog = getByText('¡Este logro se obtiene al llegar al nivel necesario!');
    expect(dialog).toBeInTheDocument();

    const badgeImage = getByAltText('logoIcon');
    expect(badgeImage).toBeInTheDocument();
    expect(badgeImage).toHaveAttribute('src', '/img/achievements/badge1.png');
  });
});
