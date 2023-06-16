import { Box, Dialog, Stack, Typography } from "@mui/material";

interface BadgesViewProps {
    isBadgeWindowOpen: boolean;
    setBadgeWindowOpen: (isBadgeWindowOpen: boolean) => void;
    imageToShow: string
}

const BadgesView: React.FC<BadgesViewProps> = (props) =>{    

    return (
        <Dialog onClose={() => props.setBadgeWindowOpen(false)} open={props.isBadgeWindowOpen} sx={{borderRadius: '50px'}}>
            <Box>
                <Stack direction='column' sx={{ width: '30em', padding: '1em', borderRadius: '50px' }}>
                    <img src={"/img/achievements/"+props.imageToShow+".png"} alt="logoIcon" width={100} style={{display: 'flex', justifyContent: 'center'}}/>
                </Stack>
                <br />
                <Typography>
                    ¡Este logro se obtiene al llegar al nivel necesario!
                </Typography>
            </Box>
        </Dialog>
    );
};

export default BadgesView;

    
