import {Pipe, PipeTransform} from '@angular/core';
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {RESULT_TYPE_NUMBER, RESULT_TYPE_TITLE, ResultType} from "../model/enum/ResultType";

@Pipe({
  name: 'resultTypePip'
})
export class ResultTypePip implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {

    if (!value || value === '') {
      return '';
    }

    if (args && args.indexOf('n') >= 0) {
      return RESULT_TYPE_NUMBER.find(v => v.stringValue == value).value;
    }

    const resultTypeList: EnumValueTitle<ResultType>[] = RESULT_TYPE_TITLE;
    return resultTypeList.find(v => v.value === value).title
  }

}
