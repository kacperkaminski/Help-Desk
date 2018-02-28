import { RoleService } from './../shared/service/role.service';
import { Role } from './../shared/model/role.model';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';




@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit, OnDestroy {

  private roles: Role[];
  sub: Subscription;

  constructor(private roleService: RoleService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.getUserGroups();
  }

  getUserGroups() {
    this.sub = this.roleService.getAllRoles().subscribe(
      roles => {
        this.roles =   roles;
      },
      err => {
        console.log(err);
      }
    );
  }


  ngOnDestroy () {
    this.sub.unsubscribe();
  }
}
