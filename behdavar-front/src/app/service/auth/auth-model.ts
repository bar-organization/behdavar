import {UserDto} from "../../model/model";
import {AuthLang} from "../../model/lang";

export class AuthenticationRequest {
  username: string;
  password: string;
}

export class AuthenticationResponse {
  jwt: string;
  userDto: UserDto;
}

export class AuthenticationException {
  private static authLang: AuthLang = new AuthLang();

  message: string;
  code: string;

  constructor(code: string, message: string) {
    this.code = code;
    this.message = message;
  }

  public static readonly USERNAME_NOT_FOUND: AuthenticationException = new AuthenticationException("1", AuthenticationException.authLang.usernameNotFound);
  public static readonly INCORRECT_USERNAME_OR_PASSWORD: AuthenticationException = new AuthenticationException("2", AuthenticationException.authLang.incorrectUsernameOrPassword);
  public static readonly OTHER: AuthenticationException = new AuthenticationException("3", AuthenticationException.authLang.other);
}
