import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {exhaustMap, take} from "rxjs/operators";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this
      .authService
      .authResponseSubject.pipe(
        take(1), exhaustMap(authResponse => {
          if(!authResponse){
            return next.handle(req);
          }
          const modifiedRequest = req.clone(
            {headers: new HttpHeaders().set("Authorization", `Bearer ${authResponse.jwt}`)});
          return next.handle(modifiedRequest);
        })
      )
  }
}
