import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Role } from '../model/role.model';


@Injectable()
export class RoleService {

  private baseUrl = 'http://localhost:8080/roles';

  constructor(private http: HttpClient) { }

  getAllRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(this.baseUrl)
      .catch((error: any) => Observable.throw( console.log(error) || 'Server error'));
  }
}
