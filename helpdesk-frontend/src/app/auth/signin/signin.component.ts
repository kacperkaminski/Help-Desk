import { AuthHttpService } from './../auth-http.service';
import { CookieService } from 'ngx-cookie-service';
import { Subscription } from 'rxjs/Subscription';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  signinForm: FormGroup;

  constructor(private authHttpService: AuthHttpService,
    private router: Router,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.signinForm = new FormGroup({
      'username': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required),
    });
  }

  onSignin() {
    const username = this.signinForm.value['username'];
    const password = this.signinForm.value['password'];
    this.authHttpService.signin(username, password).subscribe(
       data => {
        this.cookieService.set('token', data['access_token']);
        this.authHttpService.retrieveUserDataToCookies();
        this.router.navigateByUrl('/');
      });
  }

}
