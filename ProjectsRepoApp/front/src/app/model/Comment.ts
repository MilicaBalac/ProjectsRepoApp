import { Project } from './Project';


export interface Comment {
    id?: number;
    userName: String;
    text: String;
    projectDTO?: Project;
}