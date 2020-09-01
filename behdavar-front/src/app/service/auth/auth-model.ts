import {UserDto} from "../../model/model";

export class AuthenticationRequest {
  username: string;
  password: string;
}

export class AuthenticationResponse {
  jwt: string;
  userDto: UserDto;
}
