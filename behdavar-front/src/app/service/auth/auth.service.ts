import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import Url from "../../model/url";
import {Router} from '@angular/router';
import {AuthenticationException, AuthenticationRequest, AuthenticationResponse} from "./auth-model";
import {BehaviorSubject, Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {AuthorityConstantEnum} from "../../model/enum/AuthorityConstantEnum";
import {UserDto} from "../../model/model";

const jwtHelper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authResponseSubject:BehaviorSubject<AuthenticationResponse>;
  private static readonly AUTH_RESPONSE = "authResponse";

  constructor(private http: HttpClient, private router: Router) {
    if (!this.authResponseSubject) {
      this.authResponseSubject = new BehaviorSubject<AuthenticationResponse>(null);
    }
  }

  public isAuthenticated(jwtToken: string): boolean {
    if (!jwtToken)
      return false;

    try {
      return !jwtHelper.isTokenExpired(jwtToken);
    } catch (e) {
      return false;
    }
  }

  public login(username: string, password: string): Observable<AuthenticationResponse> {
    const authenticationRequest: AuthenticationRequest = {username, password};

    return this.http.post<AuthenticationResponse>(Url.LOGIN, authenticationRequest)
      .pipe(catchError(this.handelError), tap(resData => {
        this.authResponseSubject.next(resData);
        localStorage.setItem(AuthService.AUTH_RESPONSE, JSON.stringify(resData));
      }));
  }

  public logout(): void {
    this.authResponseSubject.next(null);
    localStorage.removeItem(AuthService.AUTH_RESPONSE);
    this.router.navigate(['/login']);
  }

  public autoLogin(): void {
    const authResponseStorage: AuthenticationResponse = JSON.parse(localStorage.getItem(AuthService.AUTH_RESPONSE));
    if (!authResponseStorage) {
      return;
    }
    // TODO must check if jwt inside localStorage not expired, then added to subject
    this.authResponseSubject.next(authResponseStorage);
  }

  private handelError(errorRes: HttpErrorResponse): Observable<never> {

    if (!errorRes || !errorRes.error || !errorRes.error['message'])
      return throwError(AuthenticationException.OTHER)

    if (AuthService.isInvalidUserName(errorRes.error['message'])) {
      return throwError(AuthenticationException.USERNAME_NOT_FOUND)
    }

    if (AuthService.isInvalidPassword(errorRes.error['message'])) {
      return throwError(AuthenticationException.INCORRECT_USERNAME_OR_PASSWORD)

    }


    return throwError(AuthenticationException.OTHER)
  }

  private static isInvalidUserName(errorMessage: string) {
    return !!errorMessage.match('Username not found');
  }

  private static isInvalidPassword(errorMessage: string): boolean {
    return !!errorMessage.match('Incorrect username or password');
  }

  public hasAuthority(authority: AuthorityConstantEnum): boolean {
    if (!this.authResponseSubject || !this.authResponseSubject.value || !this.authResponseSubject.value.userDto) {
      console.log('No authentication instance')
      return true;
    }

    const user: UserDto = this.authResponseSubject.value.userDto;

    for (let role of user.roles) {
      for (let privilege of role.privileges) {
        if (privilege === authority.toString()) {
          return true;
        }
      }
    }

    return false;

  }
}

