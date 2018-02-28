import { User } from './user.model';

export class Comment {
    id: number;
    taskId: number;
    author: User;
    message: string;
    date: string;
}
