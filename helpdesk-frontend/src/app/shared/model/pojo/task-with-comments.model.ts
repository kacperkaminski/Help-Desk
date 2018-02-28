import { Task } from '../task.model';
import { Comment } from '@angular/compiler';

export class TaskWithComments {
    task: Task;
    comments: Comment[];
}
