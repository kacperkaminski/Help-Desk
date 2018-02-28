import { User } from './user.model';
import { Status } from './status.model';

export class Task {
    id: number;
    owner: User;
    assignedTo: User;
    title: String;
    date: String;
    status: Status;
}
