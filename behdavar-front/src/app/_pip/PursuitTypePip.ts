import {Pipe, PipeTransform} from '@angular/core';
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {PURSUIT_TYPE_NUMBER, PURSUIT_TYPE_TITLE, PursuitType} from "../model/enum/PursuitType";

@Pipe({
  name: 'pursuitTypePip'
})
export class PursuitTypePip implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {

    if (!value || value === '') {
      return '';
    }

    if (args && args.indexOf('n') >= 0) {
      return PURSUIT_TYPE_NUMBER.find(v => v.stringValue == value).value;
    }

    const pursuitTypeList: EnumValueTitle<PursuitType>[] = PURSUIT_TYPE_TITLE;
    return pursuitTypeList.find(v => v.value === value).title
  }

}
