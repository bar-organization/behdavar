import {EnumValueTitle} from "./EnumValueTitle";
import {PaymentTypeLang} from "../lang";


const paymentTypeLang: PaymentTypeLang = new PaymentTypeLang();

export interface PaymentTypeValueNumber {
  stringValue: string;
  value: number;
}

export enum PaymentType {
  CHECK = 0,
  CASH = 1,
  NOTEBOOK = 2,
  OTHER = 3,
  INSTALLMENT_LOCATION_CASH = 4,
  INSTALLMENTS = 5
}

export const PAYMENT_TYPE_TITLE: EnumValueTitle<string>[] = [

  {value: 'CHECK', title: paymentTypeLang.CHECK},
  {value: 'CASH', title: paymentTypeLang.CASH},
  {value: 'NOTEBOOK', title: paymentTypeLang.NOTEBOOK},
  {value: 'OTHER', title: paymentTypeLang.OTHER},
  {value: 'INSTALLMENT_LOCATION_CASH', title: paymentTypeLang.INSTALLMENT_LOCATION_CASH},
  {value: 'INSTALLMENTS', title: paymentTypeLang.INSTALLMENTS},

];

export const PAYMENT_TYPE_NUMBER: PaymentTypeValueNumber[] = [
  {stringValue: 'CHECK', value: 0},
  {stringValue: 'CASH', value: 1},
  {stringValue: 'NOTEBOOK', value: 2},
  {stringValue: 'OTHER', value: 3},
  {stringValue: 'INSTALLMENT_LOCATION_CASH', value: 4},
  {stringValue: 'INSTALLMENTS', value: 5},
];

