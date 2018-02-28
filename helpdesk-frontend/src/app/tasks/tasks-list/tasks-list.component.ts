import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from '../../shared/model/user.model';
import { Subscription } from 'rxjs/Subscription';
import { UserService } from '../../user/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from '../../shared/model/task.model';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit, OnDestroy {

  name: String;
  tasks: Task[];
  filteredTasks: Task[];
  sub: Subscription;

  constructor(private userService: UserService,
    private taskService: TaskService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.tasks = <Task[]>{};
    this.getTasks();

  }

  getTasks() {
    this.sub = this.taskService.getAllTasks().subscribe(
      tasks => {
        this.tasks = tasks;
        this.assignCopy();
      }, error => {
        this.handleError(error);
      }
    );

  }

  redirectNewTaskPage() {
    this.router.navigate(['/tasks/new']);
  }

  assignCopy() {
    this.filteredTasks = Object.assign([], this.tasks);
  }

  searchTask(value) {
    if (!value) {
      this.assignCopy();
    } else {
      value = value.toLowerCase();
      this.filteredTasks = Object.assign([], this.tasks).filter(
        task => (task.title.toLowerCase().indexOf(value) > -1)
        // ||
        //   (user.lastName.toLowerCase().indexOf(value) > -1) ||
        //   (user.email.toLowerCase().indexOf(value) > -1))
      );
    }
  }

  redirectEditTask(taskId) {
    this.router.navigate(['/tasks/new']);
  }
  handleError(error) {
    throw error;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}

