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
                    {props.imageToShow.includes("Dis") ? 
                        <Box sx={{marginLeft: '10px'}}>
                            <Typography variant="h5" sx={{fontWeight: 'bold'}}>
                                {t("Achievement.statusL")}
                            </Typography>
                            <Typography sx={{mb: '3'}}>
                                {t("Achievement." + props.imageToShow.substring(0, props.imageToShow.length-3))}
                            </Typography>
                        </Box>
                        : 
                        <Box sx={{marginLeft: '10px'}}>
                            <Typography variant="h5" sx={{fontWeight: 'bold'}}>
                                {t("Achievement.statusU")}
                            </Typography>
                            <Typography>
                                {t("Achievement." + props.imageToShow)}
                            </Typography>
                        </Box>
                    }
                </Stack>
                
            </Box>
        </Dialog>
    );
};

export default BadgesView;

    
