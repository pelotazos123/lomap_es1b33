import React from 'react';
import { render, waitFor, screen } from '@testing-library/react';
import UserStats from '../stats/UserStats';

jest.mock('../../helpers/SolidHelper', () => ({
    readUserInfo: jest.fn().mockResolvedValue({}),
    findPersonData: jest.fn().mockResolvedValue({ name: 'John Doe' }),
}));

describe('UserStats', () => {

    test('renders the component when is loading', async () => {

        const initialProps = {
            badgesObtained: ['logroLvl1'],
            level: 5,
            experience: 250,
            numberOfContributions: 10,
            loading: true
        };

        render(<UserStats {...initialProps} />);
    
        // Assert loading spinner is displayed
        const loadingSpinner = screen.getByAltText('loading-spinner');
        expect(loadingSpinner).toBeInTheDocument();

    })

    test('renders the component properly when is not loading', async () => {
    const initialProps = {
        badgesObtained: ['logroLvl1'],
        level: 5,
        experience: 250,
        numberOfContributions: 10,
        loading: false
    };

    render(<UserStats {...initialProps} />);

    expect(screen.getByText('Stats.level 5')).toBeInTheDocument();
    expect(screen.getByText('Stats.experience 250')).toBeInTheDocument();
    expect(screen.getByText('Stats.nextLvl 250')).toBeInTheDocument();
    expect(screen.getByText('Stats.contributions 10')).toBeInTheDocument();
    expect(screen.getByText('Stats.numAch 1')).toBeInTheDocument();

    const badgeImage1 = screen.getByAltText('logroLvl1'); // Achievement obtained
    const badgeImage2 = screen.getByAltText('logroLvl10Dis'); // Achievements not obtained
    const badgeImage3 = screen.getByAltText('logroCont1Dis');
    const badgeImage4 = screen.getByAltText('logroCont10Dis');

    expect(badgeImage1).toBeInTheDocument();
    expect(badgeImage1).toHaveAttribute('src', '/img/achievements/logroLvl1.png');

    expect(badgeImage2).toBeInTheDocument();
    expect(badgeImage2).toHaveAttribute('src', '/img/achievements/logroLvl10Dis.png');

    expect(badgeImage3).toBeInTheDocument();
    expect(badgeImage3).toHaveAttribute('src', '/img/achievements/logroCont1Dis.png');

    expect(badgeImage4).toBeInTheDocument();
    expect(badgeImage4).toHaveAttribute('src', '/img/achievements/logroCont10Dis.png');
    });
});


