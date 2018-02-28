import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  constructor(private cookieService: CookieService) { }

  deleteCookies() {
    this.cookieService.deleteAll();
  }

  getToken(): string {
    return this.cookieService.get('token');
  }

  getFirstName(): string {
    return this.cookieService.get('firstName');
  }

  getLastName(): string {
    return this.cookieService.get('lastName');
  }

  getId(): number {
    return +this.cookieService.get('userId');
  }

  isAuthenticated(): boolean {
    return this.cookieService.check('token');
  }

  isAdmin(): boolean {
    if (this.cookieService.get('role') === 'ROLE_ADMIN') {
      return true;
    }
    return false;
  }

  isHDSpecialist(): boolean {
    if (this.cookieService.get('role') === 'ROLE_HD_SPECIALIST') {
      return true;
    }
    return false;
  }
}
