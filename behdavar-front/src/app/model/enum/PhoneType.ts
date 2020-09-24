import {EnumValueTitle} from "./EnumValueTitle";
import {PhoneTypeLang} from "../lang";

const phoneTypeLang: PhoneTypeLang = new PhoneTypeLang();

export enum PhoneType {
  PHONE = "PHONE",
  MOBILE = "MOBILE",
  FAX = "FAX"
}


export const PHONE_TYPE_TITLE: EnumValueTitle<PhoneType>[] = [
  {value: PhoneType.PHONE, title: phoneTypeLang.phone},
  {value: PhoneType.MOBILE, title: phoneTypeLang.mobile},
  {value: PhoneType.FAX, title: phoneTypeLang.fax},

];
