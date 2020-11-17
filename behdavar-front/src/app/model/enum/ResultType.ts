import {EnumValueTitle} from "./EnumValueTitle";
import {ResultTypeLang} from "../lang";


const resultTypeLang: ResultTypeLang = new ResultTypeLang();

export interface ResultTypeNumber {
  stringValue: string;
  value: number;
}

export enum ResultType {
  REQUEST_TIME = 'REQUEST_TIME',
  NO_ANSWER = 'NO_ANSWER',
  FULL_SETTLEMENT = 'FULL_SETTLEMENT',
  LEGAL_REFERENCE = 'LEGAL_REFERENCE'
}

export const RESULT_TYPE_NUMBER: ResultTypeNumber[] = [
  {stringValue: 'REQUEST_TIME', value: 0},
  {stringValue: 'NO_ANSWER', value: 1},
  {stringValue: 'FULL_SETTLEMENT', value: 2},
  {stringValue: 'LEGAL_REFERENCE', value: 3},
]


export const RESULT_TYPE_TITLE: EnumValueTitle<ResultType>[] = [

  {value: ResultType.REQUEST_TIME, title: resultTypeLang.REQUEST_TIME},
  {value: ResultType.NO_ANSWER, title: resultTypeLang.NO_ANSWER},
  {value: ResultType.FULL_SETTLEMENT, title: resultTypeLang.FULL_SETTLEMENT},
  {value: ResultType.LEGAL_REFERENCE, title: resultTypeLang.LEGAL_REFERENCE},
];
