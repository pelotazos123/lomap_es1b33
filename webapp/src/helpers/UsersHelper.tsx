import { IUser } from "../shared/SharedTypes";
import { readUserInfo, saveUserInfo } from "./SolidHelper";

const EXPERIENCE_REQUIREMENT = 100;
const EXPERIENCE_OBTAINED = 20;
  
export async function GainExperience (webId: string) {
    let info = await readUserInfo(webId);

    let newExp = (info! as IUser).experience + EXPERIENCE_OBTAINED;
    let newContributions = (info! as IUser).numberOfContributions + 1;
    let newLvl = (info! as IUser).level;

    let hasLvlUp = false;

    if (newExp >= EXPERIENCE_REQUIREMENT*(info! as IUser).level){
        newLvl = (info! as IUser).level + 1;
        newExp = 0;
        hasLvlUp = true;
    }

    const newInfo: IUser = {
        level: newLvl,
        experience: newExp,
        numberOfContributions: newContributions
    }

    await saveUserInfo(newInfo, webId);

    return hasLvlUp;
}
