import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'blankToDash'
})
export class BlankToDashPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    if (!value || value === '') {
      return '-';
    }
    return value;
  }

}
