import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient} from '@angular/common/http';
import Url from "../../model/url";
import {Router} from '@angular/router';
import {AuthenticationRequest, AuthenticationResponse} from "../../model/model";
import {BehaviorSubject, Observable} from "rxjs";
import {tap} from "rxjs/operators";

const jwtHelper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authResponseSubject = new BehaviorSubject<AuthenticationResponse>(null);
  private static readonly AUTH_RESPONSE = "authResponse";

  constructor(private http: HttpClient, private router: Router) {
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
      .pipe(tap(resData => {
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
}

