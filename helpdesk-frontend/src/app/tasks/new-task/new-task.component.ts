import { TaskService } from './../task.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  taskForm: FormGroup;

  constructor(private taskService: TaskService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.taskForm = new FormGroup({
      'title': new FormControl('', Validators.required),
      'message': new FormControl('', Validators.required)
    });
  }

  onSubmit() {
    this.taskService.saveTask(this.taskForm.value);
    setTimeout(() => { this.onCancel(); }, 300);
  }

  onCancel() {
    this.router.navigate(['/..'], { relativeTo: this.route });
  }
}
