import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'yesNoPipe'
})
export class YesNoPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): string {
    if ( typeof value === "boolean")
      return value ? 'بله' : 'خیر';

    return undefined;
  }

}
