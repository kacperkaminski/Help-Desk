import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BsDropdownModule } from 'ngx-bootstrap';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing-module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './user/user.component';
import { UserService } from './user/user.service';
import { UsersListComponent } from './user/users-list/users-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { StatusPipe } from './shared/pipes/status.pipe';
import { AddUserComponent } from './user/add-user/add-user.component';
import { EditUserComponent } from './user/edit-user/edit-user.component';
import { ValidationService } from './shared/service/validation.service';
import { ChangePasswordComponent } from './user/change-password/change-password.component';
import { RolesComponent } from './roles/roles.component';
import { RoleService } from './shared/service/role.service';
import { SigninComponent } from './auth/signin/signin.component';
import { AuthService } from './auth/auth.service';
import { AuthInterceptor } from './shared/auth-interceptor';
import { AuthHttpService } from './auth/auth-http.service';
import { StartPageComponent } from './start-page/start-page.component';
import { TasksComponent } from './tasks/tasks.component';
import { AdminGuard } from './auth/guards/AdminGuard.service';
import { AuthenticatedGuard } from './auth/guards/AuthenticatedGuard.service';
import { HDSpecialistGuard } from './auth/guards/HDSpecialistGuard.service';
import { TasksListComponent } from './tasks/tasks-list/tasks-list.component';
import { NewTaskComponent } from './tasks/new-task/new-task.component';
import { TaskService } from './tasks/task.service';
import { TaskDetailComponent } from './tasks/task-detail/task-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserComponent,
    UsersListComponent,
    PageNotFoundComponent,
    StatusPipe,
    AddUserComponent,
    RolesComponent,
    EditUserComponent,
    ChangePasswordComponent,
    SigninComponent,
    StartPageComponent,
    TasksComponent,
    TasksListComponent,
    NewTaskComponent,
    TaskDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    BsDropdownModule.forRoot()
  ],

  providers: [
    ValidationService,
    UserService,
    RoleService,
    TaskService,
    AuthService,
    AuthHttpService,
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AdminGuard,
    AuthenticatedGuard,
    HDSpecialistGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
