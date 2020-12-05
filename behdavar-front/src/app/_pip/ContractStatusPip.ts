import {Pipe, PipeTransform} from '@angular/core';
import {CONTRACT_STATUS_NUMBER, CONTRACT_STATUS_TITLE, ContractStatus} from "../model/enum/ContractStatus";
import {RowClassName} from "../_custom-component/data-table/data-table.component";

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

    if (args && args.indexOf('cn') >= 0) {
      return this.getClassName(value);
    }

    return CONTRACT_STATUS_TITLE.find(v => v.value === value).title
  }

  private getClassName(value: unknown): RowClassName {
    const status = CONTRACT_STATUS_NUMBER.find(v => v.stringValue == value).value
    switch (status) {
      case ContractStatus.AVAILABLE:
        return 'green-back';
      case ContractStatus.CLEARING:
        return "blue-back";
      case ContractStatus.RAW:
        return "purple-back";
      case ContractStatus.LEGAL:
        return "gray-back";
      case ContractStatus.PARKING:
        return 'black-back';
      case ContractStatus.RETURN:
        return 'red-back';
      default:
        return undefined;
    }
  }
}
