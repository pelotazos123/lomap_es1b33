import { IUser } from "../shared/SharedTypes";
import { readUserInfo, saveUserInfo } from "./SolidHelper";

const EXPERIENCE_REQUIREMENT = 100;
const EXPERIENCE_OBTAINED = 20;

export const allAchievements: string[] = ["logroLvl1Dis", "logroLvl10Dis", "logroLvl50Dis", "logroLvl100Dis",
     "logroCont1Dis", "logroCont10Dis", "logroCont50Dis", "logroCont100Dis"]
  
export async function GainExperience (webId: string) {
    const info = await readUserInfo(webId);

    let newExp = (info! as IUser).experience + EXPERIENCE_OBTAINED;
    const newContributions = (info! as IUser).numberOfContributions + 1;
    let newLvl = (info! as IUser).level;
    const badges = (info! as IUser).badgesObtained;

    let hasLvlUp = false;
    let newAchievement = false;

    if (newExp >= EXPERIENCE_REQUIREMENT*(info! as IUser).level){
        newLvl = (info! as IUser).level + 1;
        newExp = 0;
        hasLvlUp = true;
    }

    const newRes = CheckAchievements(newLvl, newContributions, badges, hasLvlUp, newAchievement);

    newAchievement = newRes[0]

    const newInfo: IUser = {
        level: newLvl,
        experience: newExp,
        numberOfContributions: newContributions,
        badgesObtained: newRes[1]
    }

    await saveUserInfo(newInfo, webId);

    return [hasLvlUp, newAchievement];
}

function CheckAchievements(lvl: number, nCont: number, badges: string[], hasLvlUp: boolean, newAchievement: boolean): any[] {
    let newBadges = badges;
    if (hasLvlUp){
        const res = CheckLevels(lvl, newBadges)
        if (res[0]){
            newAchievement = true
            newBadges = res[1]
        }
    }
    const resA = CheckContributions(nCont, newBadges)
    if (resA[0]){
        newAchievement = true
        newBadges = resA[1]
    }
    return [newAchievement, newBadges]
}

function CheckLevels(level: number, newBadges: string[]): any[]{
    let newBadge: string;
    let newAchievement = false;
    switch(level){
        case (1):
            newBadge = "logroLvl1"
            newAchievement = true
            break;
        case (10):
            newBadge = "logroLvl10"
            newAchievement = true
            break;
        case (50):
            newBadge = "logroLvl50"
            newAchievement = true
            break;
        case (100):
            newBadge = "logroLvl100"
            newAchievement = true
            break;
        default:
            newBadge = "";
    }

    if (newBadge !== "")
        newBadges.push(newBadge);

    return [newAchievement, newBadges];
}

function CheckContributions(nCont: number, newBadges: string[]): any[]{
    let newBadge: string;
    let newAchievement = false;
    switch(nCont){
        case (1):
            newBadge = "logroCont1"
            newAchievement = true
            break;
        case (10):
            newBadge = "logroCont10"
            newAchievement = true
            break;
        case (50):
            newBadge = "logroCont50"
            newAchievement = true
            break;
        case (100):
            newBadge = "logroCont100"
            newAchievement = true
            break;
        default:
            newBadge = "";
      }
      
      if (newBadge !== "")
        newBadges.push(newBadge);

    return [newAchievement, newBadges];
}

