import {Pipe, PipeTransform} from '@angular/core';
import {CONTRACT_STATUS_NUMBER, CONTRACT_STATUS_TITLE} from "../model/enum/ContractStatus";

@Pipe({
  name: 'contractStatusPip'
})
export class ContractStatusPip implements PipeTransform {

  transform(value: unknown, ...args: string[]): unknown {

    if (!value || value === '') {
      return '';
    }

    if (args && args.indexOf('n') >= 0) {
      return CONTRACT_STATUS_NUMBER.find(v => v.stringValue == value).value;
    }

    return CONTRACT_STATUS_TITLE.find(v => v.value === value).title
  }

}
