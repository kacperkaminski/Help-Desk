import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { Role } from './../../shared/model/role.model';
import { ValidationService } from './../../shared/service/validation.service';
import { User } from './../../shared/model/user.model';
import { UserService } from './../user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit, OnDestroy {

  id: number;
  user: User;
  sub: Subscription;
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
    this.user = <User>{};

    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
      }
    );

    this.sub = this.userService.getUserById(this.id).subscribe(
      async user2 => {
        this.user = user2;
        await (this.initForm());
      },
      err => {
        console.log(err);
      }
    );
  }

  private initForm() {
    let userFirstName = '';
    let userLastName = '';
    let userEmail = '';
    let userUsername = '';
    let userActive = false;
    let role = <Role>{};

    userFirstName = this.user.firstName;
    userLastName = this.user.lastName;
    userEmail = this.user.email;
    userUsername = this.user.username;
    userActive = this.user.active;
    role = this.user.role;

    this.userForm = new FormGroup({
      'firstName': new FormControl(userFirstName, Validators.required),
      'lastName': new FormControl(userLastName, Validators.required),
      'email': new FormControl(userEmail, [Validators.required, Validators.email]),
      'username': new FormControl(userUsername, Validators.required,
        ValidationService.validatorEditUsernameTaken(this.userService, userUsername)),
      'active': new FormControl(userActive, Validators.required),
      'role': new FormControl(this.roles[this.user.role.id - 1], Validators.required)
    });
  }

  onSubmit() {
    this.userService.updateUser(this.id, this.userForm.value);
    setTimeout(() => { this.onCancel(); }, 1000);
  }

  onCancel() {
    this.router.navigate(['/..'], { relativeTo: this.route });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
