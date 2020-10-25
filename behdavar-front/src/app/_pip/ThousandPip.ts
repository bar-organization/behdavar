import {Pipe, PipeTransform} from '@angular/core';
import numeral from "numeral";
import {Lang} from "../model/lang";

@Pipe({
  name: 'thousandPip'
})
export class ThousandPip implements PipeTransform {
  lang: Lang = new Lang();
  transform(value: unknown, ...args: unknown[]): string | number {
    const result = numeral(value).format('0,0');
    return this.isRialArgument(args) ? `${result} ${this.lang.rial}` : result;
  }

  private isRialArgument(args: unknown[]) {
    return args && args.indexOf('r') >= 0;
  }
}
