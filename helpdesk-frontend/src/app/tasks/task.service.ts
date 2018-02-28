import { TaskWithComments } from './../shared/model/pojo/task-with-comments.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../shared/model/task.model';
import { NewTask } from '../shared/model/pojo/new-task.model';
import { AuthService } from '../auth/auth.service';
import { NewComment } from '../shared/model/pojo/new-comment.model';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TaskService {

  commentSubject: Subject<TaskWithComments> = new Subject();
  taskWithComments: TaskWithComments;

  constructor(private http: HttpClient,
    private router: Router,
    private authService: AuthService) { }

  private baseUrl = 'http://localhost:8080/tasks';

  getAllTasks() {
    return this.http.get<Task[]>(this.baseUrl);
  }

  saveTask(task) {
    const newTask = <NewTask>{};
    newTask.message = task['message'];
    newTask.title = task['title'];
    newTask.ownerId = Number(this.authService.getId());
    console.log(newTask);
    return this.http.post(`${this.baseUrl}/add`, JSON.parse(JSON.stringify(newTask))).subscribe(
      result => {
        console.log(result);
      },
      error => {
        this.handleError(error);
      },
    );
  }

  getTaskWithComments(id: number) {
    this.http.get<TaskWithComments>(`${this.baseUrl}/${id}`).subscribe(
      async tasks => {
        this.taskWithComments = tasks;
        await (this.sendMessage());
      }, error => {
        this.handleError(error);
      }
    );
  }

  sendMessage() {
    this.commentSubject.next(this.taskWithComments);
  }

  getMessage(): Observable<any> {
    return this.commentSubject.asObservable();
  }

  addComment(comment: NewComment) {
    this.http.post<TaskWithComments>(`${this.baseUrl}/comment/add`, comment).subscribe(
      async tasks => {
        this.taskWithComments = tasks;
        await (this.sendMessage());
      }, error => {
        this.handleError(error);
      }
    );
  }

  changeTaskStatus(status: number, id: number) {
    const statusChangeData = { status, userId: +this.authService.getId() };
    this.http.post<Task>(`${this.baseUrl}/task/${id}/status-change`, statusChangeData).subscribe(
      async tasks => {
        this.taskWithComments.task = tasks;
        await (this.sendMessage());
      }, error => {
        this.handleError(error);
      }
    );
  }

  handleError(error) {
    throw error;
  }

}
