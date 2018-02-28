import { ValidationService } from './../../shared/service/validation.service';
import { UserService } from './../user.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  userForm: FormGroup;
  id: number;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private userService: UserService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
      }
    );
    this.initForm();
  }

  private initForm() {
    this.userForm = new FormGroup({
      'oldPassword': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required),
      'confirmPassword': new FormControl('', Validators.required)
    }, ValidationService.validatorMatchingPasswords);
  }

  onSubmit() {
    this.userService.changeUserPassword(this.userForm.value, this.id);
    setTimeout(() => { this.onCancel(); }, 500);
  }

  onCancel() {
    this.router.navigate(['/..'], { relativeTo: this.route });
  }
}

