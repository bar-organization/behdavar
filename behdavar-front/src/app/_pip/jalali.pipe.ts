import {Pipe, PipeTransform} from '@angular/core';
import * as moment from 'jalali-moment';

@Pipe({
  name: 'jalali'
})
export class JalaliPipe implements PipeTransform {

  transform(value: any, format?: any): any {
    if (!value || value == '') {
      return '';
    }
    let momentDate: moment.Moment;
    if (format && format[0]) {
      momentDate = moment(value, 'YYYY/MM/DD HH:mm');
      return momentDate.locale('fa').format(format[0]);
    } else {
      momentDate = moment(value, 'YYYY/MM/DD');
      return momentDate.locale('fa').format('YYYY/M/D');

    }
  }

}
