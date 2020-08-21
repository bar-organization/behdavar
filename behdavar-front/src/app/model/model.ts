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
  fatherName: string;
  birthDate: Date;
  nationalNumber: string;
  postalCode: string;
  mobile: string;
  telephone: string;
  birthPlace: string;
  workPlacePhone: string;
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
  username: string;
  enabled: boolean;
  tokenExpired: boolean;
  isAccountNonExpired: boolean;
  isAccountNonLocked: boolean;
  isCredentialsNonExpired: boolean;
  roles: RoleDto[];
}

export class RoleDto {
  roleName: string;
  privileges: string[];
}
