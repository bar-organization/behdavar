import {Pipe, PipeTransform} from '@angular/core';
import {PAYMENT_TYPE_NUMBER, PAYMENT_TYPE_TITLE} from "../model/enum/PaymentType";

@Pipe({
  name: 'paymentTypePip'
})
export class PaymentTypePip implements PipeTransform {

  transform(value: unknown, ...args: string[]): unknown {

    if (!value || value === '') {
      return '';
    }

    if (args && args.indexOf('n') >= 0) {
      return PAYMENT_TYPE_NUMBER.find(v => v.stringValue == value).value;
    }

    return PAYMENT_TYPE_TITLE.find(v => v.value === value).title
  }

}
