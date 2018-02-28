import { AuthService } from './../../auth/auth.service';
import { Comment } from './../../shared/model/comment.model';
import { TaskWithComments } from './../../shared/model/pojo/task-with-comments.model';
import { Component, OnInit, NgZone, OnDestroy } from '@angular/core';
import { Task } from '../../shared/model/task.model';
import { TaskService } from '../task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NewComment } from '../../shared/model/pojo/new-comment.model';
import { Subscription } from 'rxjs/Subscription';


@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit, OnDestroy {
  newComment: string;
  taskWithComments: TaskWithComments;
  filteredComments: TaskWithComments;
  taskId: number;
  userId: number;
  sub: Subscription;

  constructor(private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.taskWithComments = <TaskWithComments>{};
    this.taskId = this.route.snapshot.params.id;
    this.userId = +this.authService.getId();
    this.taskService.getTaskWithComments(this.taskId);
    this.sub = this.taskService.getMessage().subscribe(
      tasks => {
        this.taskWithComments = tasks;
        console.log(this.taskWithComments);
      });
  }

  onSubmit() {
    const comment = <NewComment>{};
    comment.message = this.newComment;
    comment.authorId = this.userId;
    comment.taskId = this.taskId;
    this.newComment = '';
    this.taskService.addComment(comment);
    this.taskService.getTaskWithComments(this.taskId);
  }

  changeStatus(status: number) {
    this.taskService.changeTaskStatus(status, this.taskId);
  }

  goBack() {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
