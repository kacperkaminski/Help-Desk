import { Observable } from 'rxjs/Observable';
import { Injectable, Output } from '@angular/core';
import { HttpClient, HttpHeaders, } from '@angular/common/http';
import { AuthService } from './auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Subject } from 'rxjs/Subject';
import { UserData } from '../shared/model/pojo/user-data.model';



@Injectable()
export class AuthHttpService {

  notifyHeader: Subject<UserData> = new Subject();
  userData: UserData;

  constructor(private http: HttpClient,
    private authService: AuthService,
    private cookieService: CookieService) {
    this.userData = <UserData>{};
  }

  signin(username, password) {

    const headers = new HttpHeaders()
      .set('Authorization', 'Basic ' + btoa('my-trusted-client:secret'))
      .set('Content-Type', 'application/x-www-form-urlencoded');

    return this.http.post(`http://localhost:8080/oauth/token?grant_type=password&username=${username}&password=${password}`,
      null, { headers: headers, withCredentials: true });
  }

  logout() {
    this.authService.deleteCookies();
    this.http.post(`http://localhost:8080/logouts`, this.authService.getToken());
  }

  retrieveUserDataToCookies() {
    this.http.get(`http://localhost:8080/user`).subscribe(
      data => {
        this.cookieService.set('userId', data['id']);
        this.cookieService.set('firstName', data['firstName']);
        this.cookieService.set('lastName', data['lastName']);
        this.cookieService.set('username', data['username']);
        this.cookieService.set('role' , data['role']);
        this.userData.firstName = data['firstName'];
        this.userData.lastName = data['lastName'];
        this.sendMessage();
      }
    );
  }

  sendMessage() {
    this.notifyHeader.next(this.userData);
  }

  getMessage(): Observable<any> {
    return this.notifyHeader.asObservable();
  }
}
