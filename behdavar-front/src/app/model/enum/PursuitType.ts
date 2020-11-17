import {PursuitTypeLang} from "../lang";
import {EnumValueTitle} from "./EnumValueTitle";

const pursuitTypeLang: PursuitTypeLang = new PursuitTypeLang();

export interface PursuitTypeNumber {
  stringValue: string;
  value: number;
}

export enum PursuitType {
  PHONE_CALL = "PHONE_CALL",
  SEND_SMS = "SEND_SMS",
  SEND_NOTICE = "SEND_NOTICE",
}

export const PURSUIT_TYPE_NUMBER: PursuitTypeNumber[] = [
  {stringValue: 'PHONE_CALL', value: 0},
  {stringValue: 'SEND_SMS', value: 1},
  {stringValue: 'SEND_NOTICE', value: 2},
];
export const PURSUIT_TYPE_TITLE: EnumValueTitle<PursuitType>[] = [
  {value: PursuitType.PHONE_CALL, title: pursuitTypeLang.PHONE_CALL},
  {value: PursuitType.SEND_SMS, title: pursuitTypeLang.SEND_SMS},
  {value: PursuitType.SEND_NOTICE, title: pursuitTypeLang.SEND_NOTICE},
]
