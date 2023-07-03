import { Box, Container, Typography} from '@mui/material';
import React, { useContext, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { IUser } from '../../shared/SharedTypes';
import { useSession } from '@inrupt/solid-ui-react';
import { findPersonData, readUserInfo } from '../../helpers/SolidHelper';
import { MarkerContext } from '../../context/MarkerContextProvider';
import BadgesView from './BadgesView';
import { allAchievements } from '../../helpers/UsersHelper';

const UserStats: React.FC<IUser> = (props) => {
    const [info, setInfo] = useState<IUser>(props);
    const [username, setUsername] = useState<string>();
    const [isLoading, setLoading] = useState<boolean>(false);
    const [isBadgeWindowOpen, setBadgeWindowOpen] = useState<boolean>(false);
    const [imageToShow, setImageToShow] = useState<string>("");
    const { state: markers } = useContext(MarkerContext);

    const achievements: string[] = allAchievements;

    const { t } = useTranslation("translation");

    const { session } = useSession();

    useEffect(() => {
      if (session.info.isLoggedIn)
          searchPersonData(session.info.webId)

    }, [session.info.isLoggedIn] );

    useEffect(() => {
      updateInfo().catch(error => console.error("Unable to update info"));
    }, [markers]);

    async function updateInfo(){
      setLoading(true)
      const newInfo = await readUserInfo(session.info.webId!);
      if ((newInfo as unknown as IUser).badgesObtained.length)
      setInfo(newInfo!);
      setLoading(false)
    }

    function searchPersonData(webId: string|undefined) {
      if (webId) {
          findPersonData(webId)
            .then(personData => {
              setUsername(personData?.name.toString())
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
        {((props.loading ?? isLoading)) || props.badgesObtained===undefined ? 
          <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            <img src="/img/loading-gif2.gif" alt="loading-spinner" data-testid="img-spinner" style={{display: 'block'}}/>
          </div>
          :
          <>
            <Box p={3}>
              <Box sx={{textAlign: 'left'}}>
                <Typography variant="h4" sx={{ mb: 3, textAlign: 'center'}}>
                  {username}
                </Typography>
                <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
                  {t("Stats.level")} {info.level}
                </Typography>
                <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
                  {t("Stats.experience")} {info.experience + " xp"}
                </Typography>
                <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
                  {t("Stats.nextLvl")} {(info.level*100) - info.experience + " xp"}
                </Typography>
                <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
                  {t("Stats.contributions")} {info.numberOfContributions}
                </Typography>
                <Typography sx={{ color: "#AAA", mb: 3, fontSize: 20 }}>
                  {t("Stats.numAch")} {info.badgesObtained.length}
                </Typography>
              </Box>
              
              <Box sx={{textAlign: 'left'}}>
                <Typography variant="h4" sx={{ mb: 3, textAlign: 'center'}}>
                  {t("Stats.achievement")}
                </Typography>   
                <Box p={4}>
                  { achievements.map((image, keyVal) => {
                      if (info.badgesObtained.includes(image.substring(0, image.length - 3))){
                        return <img src={"/img/achievements/"+image.substring(0, image.length - 3)+".png"} key={keyVal} 
                            title={t("Stats.unlocked")!} alt={image.substring(0, image.length - 3)} width={180} style={{cursor: 'pointer'}}
                              onClick={() => {
                                setBadgeWindowOpen(true)
                                setImageToShow(image.substring(0, image.length - 3))
                              }}/>

                      } else{
                        return <img src={"/img/achievements/"+image+".png"} key={keyVal} 
                            title={t("Stats.locked")!} alt={image} width={180} style={{cursor: 'pointer'}}
                              onClick={() => {
                                setBadgeWindowOpen(true)
                                setImageToShow(image)
                              }}/>

                      }
                    })
                  }
                </Box>
              </Box>
            </Box>
            <BadgesView imageToShow={imageToShow} setBadgeWindowOpen={setBadgeWindowOpen} isBadgeWindowOpen={isBadgeWindowOpen}></BadgesView>
          </>
        }
      </Container>
    );

};

export default UserStats;