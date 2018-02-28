import { Subscription } from 'rxjs/Subscription';
import { AuthService } from './../auth/auth.service';
import { AuthHttpService } from './../auth/auth-http.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from '../shared/model/pojo/user-data.model';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {

  subscription: Subscription;
  userData: UserData;

  constructor(private authHttpService: AuthHttpService,
    private authService: AuthService,
    private router: Router) {}

  ngOnInit() {
    this.userData = <UserData>{};
     this.authHttpService.getMessage().subscribe(
      data => {
        this.userData = data;
      });
  }

  onLogout() {
    this.authHttpService.logout();
    this.router.navigate(['/']);
  }
}
