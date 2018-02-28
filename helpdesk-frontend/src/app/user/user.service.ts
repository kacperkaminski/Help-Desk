import { Injectable, Pipe, OnDestroy, ErrorHandler } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/filter';

import { User } from '../shared/model/user.model';
import { JsonPipe } from '@angular/common/src/pipes/json_pipe';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { UserStatusChange } from '../shared/model/pojo/user-status-change.model';

@Injectable()
export class UserService implements OnDestroy, ErrorHandler {

  private baseUrl = 'http://localhost:8080/users';
  sub: Subscription;

  constructor(private http: HttpClient, private router: Router) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  saveUser(user: Object) {
    return this.http.post(`${this.baseUrl}/add`, JSON.parse(JSON.stringify(user))).subscribe(
      result => {
        console.log(result);
      },
      error => {
        this.handleError(error);
      },
    );
  }

  updateUser(id: number, user: Object) {
    return this.http.put(`${this.baseUrl}/${id}/update`, JSON.parse(JSON.stringify(user))).subscribe(
      result => {
        console.log(result);
      },
      error => {
        this.handleError(error);
      },
    );
  }

  changeUserStatus(id: number, status: UserStatusChange) {
    this.sub = this.http.patch(`${this.baseUrl}/${id}/statusupdate`, JSON.parse(JSON.stringify(status))).subscribe(
      () => { },
      error => {
        this.handleError(error);
      }
    );
  }

  changeUserPassword(form: Object, id: number) {
    this.sub = this.http.patch(`${this.baseUrl}/${id}/passwordupdate`, JSON.parse(JSON.stringify(form))).subscribe(
      () => { },
      error => {
        this.handleError(error);
      }
    );
  }

  checkUsernameNotTaken(username: string) {
    return this.http
      .get<User[]>(this.baseUrl)
      .map(users => users.filter(user => user.username.toLowerCase() === username.toLowerCase()))
      .map(users => !users.length);
  }

  checkEditUsernameNotTaken(username: string, oldUsername: string) {
    return this.http
      .get<User[]>(this.baseUrl)
      .map(users => users.filter(user => user.username.toLowerCase() === username.toLowerCase() &&
        user.username.toLowerCase() !== oldUsername.toLowerCase()))
      .map(users => !users.length);
  }

  handleError(error) {
    throw error;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
