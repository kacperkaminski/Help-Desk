import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'status'
})
export class StatusPipe implements PipeTransform {

  transform(value: boolean): any {
    if (value === true) {
      return 'Active';
    } else {
      return 'Inactive';
    }
  }
}
