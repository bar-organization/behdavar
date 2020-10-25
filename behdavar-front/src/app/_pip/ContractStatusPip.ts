import {Pipe, PipeTransform} from '@angular/core';
import {CONTRACT_STATUS_TITLE} from "../model/enum/ContractStatus";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";

@Pipe({
  name: 'contractStatusPip'
})
export class ContractStatusPip implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {

    if (!value || value === '') {
      return '';
    }

    const titles: EnumValueTitle<string>[] = CONTRACT_STATUS_TITLE;
    return titles.find(v => v.value === value).title
  }

}
