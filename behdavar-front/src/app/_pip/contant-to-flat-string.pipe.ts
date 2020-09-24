import {Pipe, PipeTransform} from '@angular/core';
import {ContactLang} from "../model/lang";
import {ContactDto} from "../model/model";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {PHONE_TYPE_TITLE, PhoneType} from "../model/enum/PhoneType";

@Pipe({
  name: 'contactToFlatStringPipe'
})
export class ContactToFlatStringPipe implements PipeTransform {
  lang: ContactLang = new ContactLang();
  phoneType: EnumValueTitle<PhoneType>[] = PHONE_TYPE_TITLE;
  nullStringSymbol = '-'
  transform(value: ContactDto, ...args: unknown[]): unknown {
    if (!value || value === '') {
      return '-';
    }
    return `${this.lang.number}: [${value.number? value.number:this.nullStringSymbol}] , ${this.lang.phoneType} : [${this.getPhoneTypeTitle(value)?this.getPhoneTypeTitle(value):this.nullStringSymbol}]
     , ${this.lang.confirmed}:[${value.confirmed ? 'بله': 'خیر'}] , ${this.lang?.description}: [${value.description ? value.description : this.nullStringSymbol}]
    `;
  }


  private getPhoneTypeTitle(value: ContactDto) {
    return this.phoneType.find(phoneType => phoneType.value === value.phoneType).title;
  }
}
