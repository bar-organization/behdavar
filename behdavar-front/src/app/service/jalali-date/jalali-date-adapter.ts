import * as moment from 'jalali-moment';
import {NativeDateAdapter} from "@angular/material/core";
import {Platform} from "@angular/cdk/platform";

export class JalaliDateAdapter extends NativeDateAdapter {
  constructor(matDateLocale: string) {
    super(matDateLocale, new Platform(null));
  }
  format(date: Date, displayFormat: object): string {
    const faDate = moment(date.toDateString()).locale('fa').format('YYYY/MM/DD');
    return faDate;
  }
}

export const JALALI_DATE_FORMATS = {
  parse: {
    dateInput: { month: 'short', year: 'numeric', day: 'numeric' }
  },
  display: {
    dateInput: 'input',
    monthYearLabel: { year: 'numeric', month: 'short' },
    dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric' },
    monthYearA11yLabel: { year: 'numeric', month: 'long' }
  }
}
