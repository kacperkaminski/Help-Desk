import { Component, OnInit, Input } from '@angular/core';
import { User } from '../shared/model/user.model';
import { UserService } from './user.service';
import { UsersListComponent } from './users-list/users-list.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {


  ngOnInit() {
  }

}

