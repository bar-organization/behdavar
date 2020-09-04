import {PursuitTypeLang} from "../lang";
import {EnumValueTitle} from "./EnumValueTitle";

const pursuitTypeLang: PursuitTypeLang = new PursuitTypeLang();

export enum PursuitType {
  PHONE_CALL = "PHONE_CALL",
  APPOINTMENT = "APPOINTMENT",
  NOTICE = "NOTICE",
  PAYMENT = "PAYMENT",
  REFER_PROPERTY = "REFER_PROPERTY",
  OTHER = "OTHER",
  INQUIRY_BRANCH = "INQUIRY_BRANCH",
  RECEIPT_POST_FAX = "RECEIPT_POST_FAX",
  SEND_SMS = "SEND_SMS",
  REVIEW_DOCUMENT = "REVIEW_DOCUMENT",
  REQUEST_DOCUMENT = "REQUEST_DOCUMENT",
  PEACE_AND_RECONCILIATION = "PEACE_AND_RECONCILIATION",
  LETTER_HELP = "LETTER_HELP",
  LEGAL_REVIEW = "LEGAL_REVIEW",
  REFER_LAWYER = "REFER_LAWYER",
  PREPARING_REFER_LAWYER = "PREPARING_REFER_LAWYER",
  REQUEST_RELIGION_DOCUMENTS = "REQUEST_RELIGION_DOCUMENTS",
  SEND_LETTER = "SEND_LETTER",
  EXECUTIVE_REGISTRATION_LETTER = "EXECUTIVE_REGISTRATION_LETTER",
  NOTIFICATION_RECEIVE = "NOTIFICATION_RECEIVE",
  REGISTRATION_EXECUTIVE_CLASS = "REGISTRATION_EXECUTIVE_CLASS",
  PROPERTY_IDENTIFICATION = "PROPERTY_IDENTIFICATION",
  MISSION = "MISSION",
  REPRESENTATIVE_REFER = "REPRESENTATIVE_REFER",
  REQUEST_SEIZURE = "REQUEST_SEIZURE",
  IVR = "IVR",
  UPDATED = "UPDATED"
}

export const PURSUIT_TYPE_TITLE: EnumValueTitle<PursuitType>[] = [
  {value: PursuitType.PHONE_CALL, title: pursuitTypeLang.PHONE_CALL},
  {value: PursuitType.APPOINTMENT, title: pursuitTypeLang.APPOINTMENT},
  {value: PursuitType.NOTICE, title: pursuitTypeLang.NOTICE},
  {value: PursuitType.PAYMENT, title: pursuitTypeLang.PAYMENT},
  {value: PursuitType.REFER_PROPERTY, title: pursuitTypeLang.REFER_PROPERTY},
  {value: PursuitType.OTHER, title: pursuitTypeLang.OTHER},
  {value: PursuitType.INQUIRY_BRANCH, title: pursuitTypeLang.INQUIRY_BRANCH},
  {value: PursuitType.RECEIPT_POST_FAX, title: pursuitTypeLang.RECEIPT_POST_FAX},
  {value: PursuitType.SEND_SMS, title: pursuitTypeLang.SEND_SMS},
  {value: PursuitType.REVIEW_DOCUMENT, title: pursuitTypeLang.REVIEW_DOCUMENT},
  {value: PursuitType.REQUEST_DOCUMENT, title: pursuitTypeLang.REQUEST_DOCUMENT},
  {value: PursuitType.PEACE_AND_RECONCILIATION, title: pursuitTypeLang.PEACE_AND_RECONCILIATION},
  {value: PursuitType.LETTER_HELP, title: pursuitTypeLang.LETTER_HELP},
  {value: PursuitType.LEGAL_REVIEW, title: pursuitTypeLang.LEGAL_REVIEW},
  {value: PursuitType.REFER_LAWYER, title: pursuitTypeLang.REFER_LAWYER},
  {value: PursuitType.PREPARING_REFER_LAWYER, title: pursuitTypeLang.PREPARING_REFER_LAWYER},
  {value: PursuitType.REQUEST_RELIGION_DOCUMENTS, title: pursuitTypeLang.REQUEST_RELIGION_DOCUMENTS},
  {value: PursuitType.SEND_LETTER, title: pursuitTypeLang.SEND_LETTER},
  {value: PursuitType.EXECUTIVE_REGISTRATION_LETTER, title: pursuitTypeLang.EXECUTIVE_REGISTRATION_LETTER},
  {value: PursuitType.NOTIFICATION_RECEIVE, title: pursuitTypeLang.NOTIFICATION_RECEIVE},
  {value: PursuitType.REGISTRATION_EXECUTIVE_CLASS, title: pursuitTypeLang.REGISTRATION_EXECUTIVE_CLASS},
  {value: PursuitType.PROPERTY_IDENTIFICATION, title: pursuitTypeLang.PROPERTY_IDENTIFICATION},
  {value: PursuitType.MISSION, title: pursuitTypeLang.MISSION},
  {value: PursuitType.REPRESENTATIVE_REFER, title: pursuitTypeLang.REPRESENTATIVE_REFER},
  {value: PursuitType.REQUEST_SEIZURE, title: pursuitTypeLang.REQUEST_SEIZURE},
  {value: PursuitType.IVR, title: pursuitTypeLang.IVR},
  {value: PursuitType.UPDATED, title: pursuitTypeLang.UPDATED}
]
