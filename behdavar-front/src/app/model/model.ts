class BaseModel<T> {
  id: T;
  version ?: number;
}


export class Guarantors extends BaseModel<number> {
  person: Person;
}

export class Person {
  name: string;
  family: string;
  fatherName?: string;
  birthDate?: Date;
  nationalNumber: string;
  postalCode?: string;
  mobile?: string;
  telephone?: string;
  birthPlace?: string;
  workPlacePhone?: string;
}

export class BaseAuditorDto<U, I> {
  id: I;
  createdBy: U;
  createdDate: Date;
  lastModifiedBy: U;
  lastModifiedDate: Date;
  version: number;
}

export class GuarantorDto extends BaseAuditorDto<string, number> {
  relationType: RelationType;
  contract: ContractDto;
  person: PersonDto;
}

export class CustomerDto extends BaseAuditorDto<string, number> {
  person: PersonDto;
  contract: ContractDto;
}

export class PersonDto extends BaseAuditorDto<string, number> {
  firstName: string;
  lastName: string;
  email: string;
  description: string;
}

export class UserDto extends BaseAuditorDto<string, number> {
  firstName: string;
  lastName: string;
  fullName: string = `${this.firstName} ${this.lastName}`
  username: string;
  enabled: boolean;
  tokenExpired: boolean;
  isAccountNonExpired: boolean;
  isAccountNonLocked: boolean;
  isCredentialsNonExpired: boolean;
  roles: RoleDto[];
}

export class PursuitDto extends BaseAuditorDto<string, number> {
  description: string;
  coordinateAppointment: boolean;
  depositAppointment: boolean;
  submitAccordingFinal: boolean;
  nextPursuitDate: Date;
  customerDeposit: boolean;
  pursuitType: PursuitType;
  resultType: ResultType;
  payment: PaymentDto;
  user: UserDto;
  contract: ContractDto;
}

export class PaymentDto {
  amount: number;
  paymentDate: Date;
  paymentType: PaymentType;
  contract: ContractDto;
  user: UserDto;
  attachment: AttachmentDto;
}

export class AttachmentDto {
  content: string;
  fileName: string;
  attachmentType: CatalogDetailDto;
  contract: ContractDto;
}

export class CatalogDto extends BaseAuditorDto<string, number> {
  id: number;
  code: string;
  englishTitle: string;
  title: string;
  active: boolean;
}

export class CartableDto extends BaseAuditorDto<string, number> {
  sender: UserDto;
  receiver: UserDto;
  contract: ContractDto;
}

export class ContractDto extends BaseAuditorDto<string, number> {
  masterAmount: number;
  defferedAmount: number;
  idealIssueDate: Date;
  lendingNumber: string;
  defferedCount: number;
  lateFees: number;
  submitDate: Date;
  lending: LendingDto;
  product: ProductDto;
  corporation: CatalogDetailDto;
  contractStatus: ContractStatus;
  contractType: ContractType;
}

export class LendingDto extends BaseAuditorDto<string, number> {
  masterAmount: number;
  ideaIssueDate: Date;
  receiveLendingDate: Date;
  branchBank: BankDto;

}

export class ProductDto extends BaseAuditorDto<string, number> {
  productShasiNumber: string;
  productPlate: string;
  productName: string;
}

export class BankDto extends BaseAuditorDto<string, number> {
  code: string;
  name: string;
  phone: string;
  fax: string;
  address: AddressDto;
  heads: string;
  bankType: CatalogDetailDto;
  active: boolean;
}

export class CatalogDetailDto extends BaseAuditorDto<string, number> {
  englishTitle: string;
  title: string;
  code: string;
  active: boolean;
  catalog: CatalogDto;

}

export class AddressDto extends BaseAuditorDto<string, number> {
  mainStreet: string;
  subStreet: string;
  mainAlley: string;
  subAlley: string;
  postalCode: string;
  plate: string;
  description: string;
  geoDivision: GeoDivisionDto;
  person: Person;
}

export class GeoDivisionDto extends BaseAuditorDto<string, number> {
  code: string;
  name: string;
  geoDivisionType: GeoDivisionType;
  parent: GeoDivisionDto;
  children: GeoDivisionDto[];
}

export enum GeoDivisionType {
  PROVINCE = "PROVINCE",
  CITY = "CITY"
}

export class RoleDto extends BaseAuditorDto<string, number> {
  roleName: string;
  privileges: string[];
}

export enum ContractStatus {
  STATUS_1 = "STATUS_1",
  STATUS_2 = "STATUS_2"
}

export enum ContractType {
  TYPE_1 = "TYPE_1",
  TYPE_2 = "TYPE_2"
}

export enum RelationType {
  GUARANTOR = "GUARANTOR",
  S1 = "S1",
  S2 = "S2"

}

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

export enum PaymentType {
  CHECK = "CHECK",
  CASH = "CASH",
  NOTEBOOK = "NOTEBOOK",
  OTHER = "OTHER",
  INSTALLMENT_LOCATION_CASH = "INSTALLMENT_LOCATION_CASH",
  INSTALLMENTS = "INSTALLMENTS"
}
