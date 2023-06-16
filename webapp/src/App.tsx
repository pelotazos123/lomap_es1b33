import './App.css';
import HomeView from './components/HomeView';
import { NavBar } from './components/NavBar';
import { IPMarker, IUser } from './shared/SharedTypes';
import { Routes, Route } from "react-router-dom";
import { useSession } from '@inrupt/solid-ui-react';
import { loadMapApi } from './utils/GoogleMapsUtils';
import FriendsList from './components/friends/Friends';
import React, { useContext, useEffect, useState } from 'react';
import MapView from './components/map/mapAddons/MapView';
import UbicationsView from './components/map/mapAddons/UbicationsView';
import { readFriendMarkers, readMarkers, readUserInfo, saveUserInfo } from './helpers/SolidHelper';
import { MarkerContext, Types } from './context/MarkerContextProvider';
import AboutUs from './components/AboutUs';
import NotificationsSystem, { atalhoTheme, setUpNotifications, useNotifications } from "reapop";
import i18n from './localize/i18n';
import UserStats from './components/stats/UserStats';
import { useTranslation } from 'react-i18next';

setUpNotifications({
  defaultProps: {
    position: "top-right",
    dismissible: true,
    showDismissButton: true,
    dismissAfter: 3000,
  },
});

function App(): React.JSX.Element {
    const { session } = useSession();
    const { dispatch } = useContext(MarkerContext);
    const [scriptLoaded, setScriptLoaded] = useState(false);
    const { notifications, dismissNotification } = useNotifications();
    const [info, setInfo] = useState<IUser>();
    const [lang, setLang] = useState<string>(["en", "es", "fr"].includes(i18n.language) ? i18n.language : "en");

    const isLoggedIn = session.info.isLoggedIn;

    const { t } = useTranslation('translation')

    const { notify } = useNotifications();

    useEffect(() => {
      const googleMapScript = loadMapApi();
      googleMapScript.addEventListener('load', function () {
        setScriptLoaded(true);
      });
    }, []);

    session.onLogin(async () => {
      let markers = await readFriendMarkers(session.info.webId!);
      (await readMarkers(session.info.webId!)).forEach(m => markers.push(m));
      prepareUserInfo();
      setMarkers(markers);
    })

    session.onLogout(async () => {
      setMarkers([])
      window.location.reload();
    })

    async function prepareUserInfo(){
      let info = await readUserInfo(session.info.webId!);

      if (info == null){
        let basicBadges: string[] = [];
 
        const newUser: IUser = {
          level: 0,
          experience: 0,
          numberOfContributions: 0,
          badgesObtained: basicBadges
        }
        
        await saveUserInfo(newUser, session.info.webId!);
      }
      info = await readUserInfo(session.info.webId!);
      setInfo(info!)
    }

    function setMarkers(markers: IPMarker[]) {
      dispatch({ type: Types.SET_MARKERS, payload: { markers: markers } });
    }

    const showDeletedFriendNoti = () => {
      notify(t("Notifications.delF"), "success");
    }

    const showAddedFriendNoti = () => {
      notify(t("Notifications.addF"), "success");
    }

    const showLocationAdded = () => {
      notify(t("Notifications.okUbiAdd"), "success");
    }

    const showLocationDeleted = () => {
        notify(t("Notifications.okUbi"), "success");
    }

    const showExperienceNoti = () => {
      notify(t("Notifications.newExp"), 'success');
    }

    const showLevelUpNoti = () => {
      notify(t("Notifications.newLvl"), 'success');
    }

    const showNewAchievementNoti = () => {
      notify(t("Notifications.newLog"), 'success');
    }

    return (
      <>
        <NavBar lang={lang} setLang={setLang} opt={true} >
        </NavBar>
          <Routes>
            <Route path="/" element={
              <HomeView />
            } />
            <Route path="/map" element={scriptLoaded &&
              (<MapView showLocationAddedNoti={showLocationAdded} showLocationDeletedNoti={showLocationDeleted} showExperienceNoti={showExperienceNoti} showLevelUpNoti={showLevelUpNoti} showNewAchievementNoti={showNewAchievementNoti}/>)
            } />
            <Route path={isLoggedIn ? "/ubications" : "/map"} element={
              <UbicationsView opt={true} showDeletedUbicationNoti={showLocationDeleted}/>
            } />
            <Route path={isLoggedIn ? "/friends" : "/map"} element={
              <FriendsList loading={true} showAddedFriend={showAddedFriendNoti} showDeletedFriend={showDeletedFriendNoti}/>
            } />
            <Route path="/aboutus" element={
              <AboutUs />
            } />
            <Route path={isLoggedIn ? "/userstats" : "/map"} element={
              <UserStats {...info!} />
            } />
          </Routes>
          <NotificationsSystem
            notifications={notifications}
            dismissNotification={(id) => dismissNotification(id)}
            theme={atalhoTheme}
          />
      </>
    );
  }
export default App;