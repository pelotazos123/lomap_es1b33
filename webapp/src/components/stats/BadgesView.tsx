import { Box, Dialog, Divider, Stack, Typography } from "@mui/material";
import { useTranslation } from "react-i18next";

interface BadgesViewProps {
    isBadgeWindowOpen: boolean;
    setBadgeWindowOpen: (isBadgeWindowOpen: boolean) => void;
    imageToShow: string
}

const BadgesView: React.FC<BadgesViewProps> = (props) =>{    

    const { t } = useTranslation("translation");

    return (
        <Dialog onClose={() => props.setBadgeWindowOpen(false)} open={props.isBadgeWindowOpen} sx={{borderRadius: '50px'}}>
            <Box>
                <Stack direction='row' divider={<Divider orientation="vertical" flexItem />}
                sx={{ width: '33em', padding: '1em', borderRadius: '50px' }}>
                    <img src={"/img/achievements/"+props.imageToShow+".png"} alt="logoIcon" width={150} 
                        style={{display: 'flex', justifyContent: 'center', marginRight: '8px'}}/>
                    <br />
                    <Box sx={{marginLeft: '10px'}}>
                        {props.imageToShow.includes("Dis") ?
                            <>
                                <Typography variant="h5" sx={{fontWeight: 'bold'}}>
                                {t("Achievement." + props.imageToShow.substring(0, props.imageToShow.length-3) + ".title")}
                                </Typography>
                                <Typography sx={{ fontStyle: 'italic', fontSize: '12px' }}>
                                    {t("Achievement.statusL")}
                                </Typography>
                                <Typography sx={{mb: '3'}}>
                                    {t("Achievement." + props.imageToShow.substring(0, props.imageToShow.length-3) + ".description")}
                                </Typography>
                            </>
                        :
                            <>
                                <Typography variant="h5" sx={{fontWeight: 'bold'}}>
                                {t("Achievement." + props.imageToShow + ".title")}
                                </Typography>
                                <Typography sx={{ fontStyle: 'italic', fontSize: '12px' }}>
                                    {t("Achievement.statusU")}
                                </Typography>
                                <Typography>
                                    {t("Achievement." + props.imageToShow + ".description")}
                                </Typography>
                            </>
                        }
                    </Box>
                </Stack>
                
            </Box>
        </Dialog>
    );
};

export default BadgesView;

    
