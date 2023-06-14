import { IUser } from "../shared/SharedTypes";
import { readUserInfo, saveUserInfo } from "./SolidHelper";

const EXPERIENCE_REQUIREMENT = 100;
const EXPERIENCE_OBTAINED = 20;
  
export async function GainExperience (webId: string) {
    let info = await readUserInfo(webId);

    let newExp = (info! as IUser).experience + EXPERIENCE_OBTAINED;
    let newContributions = (info! as IUser).numberOfContributions + 1;
    let newLvl = (info! as IUser).level;
    let badges = (info! as IUser).badgesObtained;

    let hasLvlUp = false;
    let newAchievement = false;

    if (newExp >= EXPERIENCE_REQUIREMENT*(info! as IUser).level){
        newLvl = (info! as IUser).level + 1;
        newExp = 0;
        hasLvlUp = true;
    }

    let newRes = CheckAchievements(newLvl, newContributions, badges, hasLvlUp, newAchievement);

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
        let res = CheckLevels(lvl, newBadges)
        if (res[0]){
            console.log("logroo")
            newAchievement = true
            newBadges = res[1]
        }
    }
    // let resA = CheckContributions(nCont, newBadges)
    // if (resA[0])
    //     newAchievement = true
    return [newAchievement, newBadges]
}

function CheckLevels(level: number, newBadges: string[]): any[]{
    let index: number;
    let newBadge: string;
    let newAchievement = false;
    switch(level){
        case (1):
            index = newBadges.indexOf("logroLvl1Dis");
            newBadge = "logroLvl1"
            newAchievement = true
            break;
        case (10):
            index = newBadges.indexOf("logroLvl10Dis");
            newBadge = "logroLvl10"
            newAchievement = true
            break;
        case (25):
            index = newBadges.indexOf("logroLvl25Dis");
            newBadge = "logroLvl25"
            newAchievement = true
            break;
        case (50):
            index = newBadges.indexOf("logroLvl50Dis");
            newBadge = "logroLvl50"
            newAchievement = true
            break;
        case (100):
            index = newBadges.indexOf("logroLvl100Dis");
            newBadge = "logroLvl100"
            newAchievement = true
            break;
        default:
            index = -1;
            newBadge = "";
    }
    if (index !== -1)
        newBadges[index] = newBadge;

    return [newAchievement, newBadges];
}

function CheckContributions(nCont: number, newBadges: string[]): any[]{
    let index: number;
    let newBadge: string;
    let newAchievement = false;
    switch(nCont){
        case (1):
          console.log("baby steps");
          newAchievement = true
          break;
        case (10):
          console.log("regular user");
          newAchievement = true
          break;
        case (50):
          console.log("quite awesome");
          newAchievement = true
          break;
        case (100):
          console.log("the biggest one");
          newAchievement = true
          break;
      }
    // if (index != -1)
    //     newBadges[index] = newBadge;

    return [newAchievement, newBadges];
}

