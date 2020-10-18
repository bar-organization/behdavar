import {EnumValueTitle} from "./EnumValueTitle";
import {ContractStatusLang} from "../lang";


const contractStatusLang: ContractStatusLang = new ContractStatusLang();

export enum ContractStatus {
  AVAILABLE = 'AVAILABLE',
  CLEARING = 'CLEARING',
  RAW = 'RAW',
  LEGAL = 'LEGAL',
  PARKING = 'PARKING',
  RETURN = 'RETURN'
}

export const CONTRACT_STATUS_TITLE: EnumValueTitle<ContractStatus>[] = [

  {value: ContractStatus.AVAILABLE, title: contractStatusLang.AVAILABLE},
  {value: ContractStatus.CLEARING, title: contractStatusLang.CLEARING},
  {value: ContractStatus.RAW, title: contractStatusLang.RAW},
  {value: ContractStatus.LEGAL, title: contractStatusLang.LEGAL},
  {value: ContractStatus.PARKING, title: contractStatusLang.PARKING},
  {value: ContractStatus.RETURN, title: contractStatusLang.RETURN},

];
