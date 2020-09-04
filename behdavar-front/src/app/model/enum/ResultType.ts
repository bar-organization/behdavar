import {EnumValueTitle} from "./EnumValueTitle";
import {ResultTypeLang} from "../lang";


const resultTypeLang: ResultTypeLang = new ResultTypeLang();

export enum ResultType {
  RETURNED = "RETURNED",
  NOT_ANNOUNCED = "NOT_ANNOUNCED",
  COLLECTION = "COLLECTION",
  RETURN = "RETURN",
  GUARANTEE = "GUARANTEE",
  CONVERSION = "CONVERSION",
  DURATION = "DURATION",
  ACCEPTED = "ACCEPTED",
  DAY_CHECK = "DAY_CHECK",
  SEND_TO_BANK = "SEND_TO_BANK",
  SEND_TO_FINANCE = "SEND_TO_FINANCE",
  SEND_TO_BRANCH = "SEND_TO_BRANCH",
  SEND_TO_AREA = "SEND_TO_AREA",
  NON_APPROVAL_AREA = "NON_APPROVAL_AREA",
  FINAL_CONFIRMATION = "FINAL_CONFIRMATION",
  RENEWAL_BANK_RESOURCES = "RENEWAL_BANK_RESOURCES"
}

export const RESULT_TYPE_TITLE: EnumValueTitle<ResultType>[] = [

  {value: ResultType.RETURNED, title: resultTypeLang.RETURNED},
  {value: ResultType.NOT_ANNOUNCED, title: resultTypeLang.NOT_ANNOUNCED},
  {value: ResultType.COLLECTION, title: resultTypeLang.COLLECTION},
  {value: ResultType.RETURN, title: resultTypeLang.RETURN},
  {value: ResultType.GUARANTEE, title: resultTypeLang.GUARANTEE},
  {value: ResultType.CONVERSION, title: resultTypeLang.CONVERSION},
  {value: ResultType.DURATION, title: resultTypeLang.DURATION},
  {value: ResultType.ACCEPTED, title: resultTypeLang.ACCEPTED},
  {value: ResultType.DAY_CHECK, title: resultTypeLang.DAY_CHECK},
  {value: ResultType.SEND_TO_BANK, title: resultTypeLang.SEND_TO_BANK},
  {value: ResultType.SEND_TO_FINANCE, title: resultTypeLang.SEND_TO_FINANCE},
  {value: ResultType.SEND_TO_BRANCH, title: resultTypeLang.SEND_TO_BRANCH},
  {value: ResultType.SEND_TO_AREA, title: resultTypeLang.SEND_TO_AREA},
  {value: ResultType.NON_APPROVAL_AREA, title: resultTypeLang.NON_APPROVAL_AREA},
  {value: ResultType.FINAL_CONFIRMATION, title: resultTypeLang.FINAL_CONFIRMATION},
  {value: ResultType.RENEWAL_BANK_RESOURCES, title: resultTypeLang.RENEWAL_BANK_RESOURCES}
];
