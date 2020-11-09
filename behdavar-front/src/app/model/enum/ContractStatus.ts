import {EnumValueTitle} from "./EnumValueTitle";
import {ContractStatusLang} from "../lang";


const contractStatusLang: ContractStatusLang = new ContractStatusLang();

export interface ContractStatusValueNumber {
  stringValue: string;
  value: number;
}

export enum ContractStatus {
  AVAILABLE = 0,
  CLEARING = 1,
  RAW = 2,
  LEGAL = 3,
  PARKING = 4,
  RETURN = 5
}

export const CONTRACT_STATUS_TITLE: EnumValueTitle<string>[] = [

  {value: 'AVAILABLE', title: contractStatusLang.AVAILABLE},
  {value: 'CLEARING', title: contractStatusLang.CLEARING},
  {value: 'RAW', title: contractStatusLang.RAW},
  {value: 'LEGAL', title: contractStatusLang.LEGAL},
  {value: 'PARKING', title: contractStatusLang.PARKING},
  {value: 'RETURN', title: contractStatusLang.RETURN},

];

export const CONTRACT_STATUS_NUMBER: ContractStatusValueNumber[] = [
  {stringValue: 'AVAILABLE', value: 0},
  {stringValue: 'CLEARING', value: 1},
  {stringValue: 'RAW', value: 2},
  {stringValue: 'LEGAL', value: 3},
  {stringValue: 'PARKING', value: 4},
  {stringValue: 'RETURN', value: 5},
];

