import {Injectable} from '@angular/core';
import {AuthService} from "./auth.service";
import {CanActivate, Router} from "@angular/router";
import {Observable} from "rxjs";
import {map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(): Observable<boolean> {
    //  TODO must fix map function
    return this.auth.authResponseSubject.pipe(
      map(value => !!value && !!value.jwt && this.auth.isAuthenticated(value.jwt)),
      tap(isAuthenticated => {
        if (!isAuthenticated)
          this.router.navigate(['login']);
      }));

  }
}
