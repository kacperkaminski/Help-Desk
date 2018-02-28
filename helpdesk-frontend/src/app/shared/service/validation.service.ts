
import { AbstractControl, ValidationErrors } from '@angular/forms';
import { UserService } from '../../user/user.service';



export class ValidationService {

  constructor() { }

  static validatorUsernameTaken(userService: UserService) {
    return (control: AbstractControl) => {
      return userService.checkUsernameNotTaken(control.value).map(res => {
        return res ? null : { usernameTaken: true };
      });
    };
  }
  static validatorEditUsernameTaken(userService: UserService, oldUsername: string) {
    return (control: AbstractControl) => {
      return userService.checkEditUsernameNotTaken(control.value, oldUsername).map(res => {
        return res ? null : { usernameTaken: true };
      });
    };
  }
  static validatorMatchingPasswords(control: AbstractControl): ValidationErrors | null {
    return control.root.get('password').value === control.root.get('confirmPassword').value ? null : { notSame: true };
  }
}
