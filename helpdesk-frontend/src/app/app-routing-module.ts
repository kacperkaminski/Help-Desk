import { AdminGuard } from './auth/guards/AdminGuard.service';
import { TasksComponent } from './tasks/tasks.component';
import { StartPageComponent } from './start-page/start-page.component';
import { SigninComponent } from './auth/signin/signin.component';
import { RolesComponent } from './roles/roles.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { UserComponent } from './user/user.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { EditUserComponent } from './user/edit-user/edit-user.component';

import { ChangePasswordComponent } from './user/change-password/change-password.component';
import { AuthenticatedGuard } from './auth/guards/AuthenticatedGuard.service';
import { TasksListComponent } from './tasks/tasks-list/tasks-list.component';
import { NewTaskComponent } from './tasks/new-task/new-task.component';
import { TaskDetailComponent } from './tasks/task-detail/task-detail.component';

const appRoutes: Routes = [

    { path: '', component: StartPageComponent },
    { path: 'users', component: UserComponent, canActivate: [AdminGuard] },
    { path: 'users/form', component: AddUserComponent, canActivate: [AdminGuard] },
    { path: 'users/:id/edit', component: EditUserComponent, canActivate: [AdminGuard] },
    { path: 'users/:id/form', component: AddUserComponent, canActivate: [AdminGuard] },
    { path: 'users/:id/password', component: ChangePasswordComponent, canActivate: [AdminGuard] },
    { path: 'roles', component: RolesComponent, canActivate: [AdminGuard]  },
    { path: 'login', component: SigninComponent },
    { path: 'tasks', component: TasksListComponent, canActivate: [AuthenticatedGuard]  },
    { path: 'tasks/new', component: NewTaskComponent, canActivate: [AuthenticatedGuard]  },
    { path: 'tasks/:id/edit', component: TaskDetailComponent, canActivate: [AuthenticatedGuard]  },

    { path: 'not-found', component: PageNotFoundComponent },
    { path: '**', redirectTo: '/not-found' }
];


@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})

export class AppRoutingModule {

}
