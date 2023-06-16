export interface IPMarker {
  id: string,
  owner: string,
  date: Date,
  lat: number,
  lng: number,
  name: string,
  webId: string,
  address: string,
  category: string,
  isPublic: boolean,
  ratings: number[],
  comments: Comment[],
  description: string
}

export interface Comment {
  author: string,
  text: string,
  img?: string
}

export interface IUser {
  level: number,
  experience: number,
  numberOfContributions: number,
  badgesObtained: string[],
  loading?: boolean
}