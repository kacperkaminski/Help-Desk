import { Role } from './role.model';

export class User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    password: string;
    active: boolean;
    role: Role;
}
