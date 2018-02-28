import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';

import { User } from '../../shared/model/user.model';
import { UserService } from '../user.service';
import { UserStatusChange } from '../../shared/model/pojo/user-status-change.model';


@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css'],
})

export class UsersListComponent implements OnInit, OnDestroy {

  name: String;
  users: User[];
  filteredUsers: User[];
  sub: Subscription;
  updatedStatus: UserStatusChange;

  constructor(private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.getUsers();
    this.updatedStatus = <UserStatusChange>{};
  }

  getUsers() {
    this.sub = this.userService.getAllUsers().subscribe(
      users => {
        this.users = users;
        this.assignCopy();
      }, error => {
        this.handleError(error);
      }
    );

  }

  redirectUserDetailPage(user: User) {
    this.router.navigate([user.id], { relativeTo: this.route });
  }

  redirectNewUserPage() {
    this.router.navigate(['/users/form']);
  }

  redirectEditUser(user: User) {
    this.router.navigate([user.id + '/edit'], { relativeTo: this.route });
  }

  redirectChangePassword(user: User) {
    this.router.navigate([user.id + '/password'], { relativeTo: this.route });
  }

  changeUserStatus(user: User) {
    this.updatedStatus.status = !user.active;
    user.active = !user.active;
    this.userService.changeUserStatus(user.id, this.updatedStatus);
  }

  assignCopy() {
    this.filteredUsers = Object.assign([], this.users);
  }

  searchUser(value) {
    if (!value) {
      this.assignCopy();
    } else {
      value = value.toLowerCase();
      this.filteredUsers = Object.assign([], this.users).filter(
        user => ((user.firstName.toLowerCase().indexOf(value) > -1) ||
          (user.lastName.toLowerCase().indexOf(value) > -1) ||
          (user.email.toLowerCase().indexOf(value) > -1))
      );
    }
  }

  handleError(error) {
    throw error;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}

