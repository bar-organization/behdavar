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

export class AuthenticationRequest {
  username: string;
  password: string;
}

export class AuthenticationResponse {
  jwt: string;
  userDto: UserDto;
}

export class UserDto {
  firstName: string;
  lastName: string;
  fullName:string = `${this.firstName} ${this.lastName}`
  username: string;
  enabled: boolean;
  tokenExpired: boolean;
  isAccountNonExpired: boolean;
  isAccountNonLocked: boolean;
  isCredentialsNonExpired: boolean;
  roles: RoleDto[];
}

export class CatalogDto {
  id: number;
  code: string;
  englishTitle: string;
  title: string;
  active: boolean;
}

export class CartableDto {
  sender: UserDto;
  receiver: UserDto;
  contract: ContractDto;
}

export class ContractDto {
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

export class LendingDto {
  masterAmount: number;
  ideaIssueDate: Date;
  receiveLendingDate: Date;
  branchBank: BankDto;

}

export class ProductDto {
  productShasiNumber: string;
  productPlate: string;
  productName: string;
}

export class BankDto {
  code: string;
  name: string;
  phone: string;
  fax: string;
  address: AddressDto;
  heads: string;
  bankType: CatalogDetailDto;
  active: boolean;
}

export class CatalogDetailDto {
  englishTitle: string;
  title: string;
  code: string;
  active: boolean;
  catalog: CatalogDto;

}

export class AddressDto {
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

export class GeoDivisionDto {
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

export class RoleDto {
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
