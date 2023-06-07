import { Box, Container, Typography } from '@mui/material';
import React, { useContext, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { IUser } from '../shared/SharedTypes';
import { useSession } from '@inrupt/solid-ui-react';
import { findPersonData, readUserInfo } from '../helpers/SolidHelper';
import { MarkerContext } from '../context/MarkerContextProvider';

const UserStats: React.FC<IUser> = (props) => {
    const [info, setInfo] = useState<IUser>(props);
    const [username, setUsername] = useState<string>();
    const { state: markers } = useContext(MarkerContext);
    const [isLoading, setLoading] = useState<boolean>(false);

    const { t } = useTranslation("translation");

    const { session } = useSession();

    useEffect(() => {
      if (session.info.isLoggedIn)
          searchPersonData(session.info.webId)

    }, [session.info.isLoggedIn] );

    useEffect(() => {
      updateInfo();
    }, [markers]);

    async function updateInfo(){
      setLoading(true)
      let newInfo = await readUserInfo(session.info.webId!);
      setInfo(newInfo!);
      console.log(newInfo, "new info")
      setLoading(false)
    }

    function searchPersonData(webId: string|undefined) {
      if (webId) {
          findPersonData(webId)
            .then(personData => {
              setUsername(personData?.name)
          })
          .catch(error => {
              console.error("Error en findPersonData:", error);
          });
      }
    }
    
    return (
      <Container
          maxWidth="md"
          sx={{
              textAlign: "center",
              mt: 4,
              mb: 4,
              borderRadius: "8px",
              background: "linear-gradient(to top, #2C5364, #203A43, #0F2027)",
              color: "white"
          }}
      >
        {!isLoading ? 
          <Box p={3} sx={{textAlign: 'left'}}>
            <Typography variant="h3" sx={{ mb: 3, textAlign: 'center'}}>
              {username}
            </Typography>
            <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
              {t("Stats.level")} {info.level}
            </Typography>
            <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
              {t("Stats.experience")} {info.experience}
            </Typography>
            <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
              {t("Stats.nextLvl")} {(info.level*100) - info.experience}
            </Typography>
            <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
              {t("Stats.contributions")} {info.numberOfContributions}
            </Typography>
          </Box>
          :
          <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            <img src="loading-gif2.gif" alt="loading-spinner" data-testid="img-spinner" style={{display: 'block'}}/>
          </div>
        }
      </Container>
    );

};

export default UserStats;