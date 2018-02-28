import { Role } from './../../shared/model/role.model';
import { ValidationService } from './../../shared/service/validation.service';
import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit, HostListener } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserService } from '../user.service';
import { FormGroup, Validators, FormControl, AbstractControl, ValidationErrors } from '@angular/forms';

import { User } from './../../shared/model/user.model';
import { HttpClient } from '@angular/common/http';
import { AsyncValidatorFn } from '@angular/forms/src/directives/validators';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})

export class AddUserComponent implements OnInit {


  userForm: FormGroup;
  roles: Role[] = [
    {
      id: 1,
      name: 'ROLE_ADMIN',
    }, {
      id: 2,
      name: 'ROLE_HD_SPECIALIST'
    }, {
      id: 3,
      name: 'ROLE_USER'
    }];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private userService: UserService) {
  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.userForm = new FormGroup({
      'firstName': new FormControl('', Validators.required),
      'lastName': new FormControl('', Validators.required),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'username': new FormControl('', Validators.required, ValidationService.validatorUsernameTaken(this.userService)),
      'password': new FormControl('', Validators.required),
      'confirmPassword': new FormControl('', Validators.required),
      'active': new FormControl(true, Validators.required),
      'role': new FormControl(this.roles[2], Validators.required)
    }, ValidationService.validatorMatchingPasswords);
  }

  onSubmit() {
    this.userService.saveUser(this.userForm.value);
    setTimeout(() => { this.onCancel(); }, 300);
  }

  onCancel() {
    this.router.navigate(['/..'], { relativeTo: this.route });
  }
}
