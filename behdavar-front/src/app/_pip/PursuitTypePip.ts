import {Pipe, PipeTransform} from '@angular/core';
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {PURSUIT_TYPE_TITLE, PursuitType} from "../model/enum/PursuitType";

@Pipe({
  name: 'pursuitTypePip'
})
export class PursuitTypePip implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {

    if (!value || value === '') {
      return '';
    }

    const pursuitTypeList: EnumValueTitle<PursuitType>[] = PURSUIT_TYPE_TITLE;
    return pursuitTypeList.find(v => v.value === value).title
  }

}
