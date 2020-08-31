import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {NgxSpinnerService} from "ngx-spinner";
import {finalize, tap} from "rxjs/operators";
import {Injectable} from "@angular/core";

@Injectable()
export class SpinnerHttpInterceptor implements HttpInterceptor {

  count = 0;

  constructor(private spinner: NgxSpinnerService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinner.show()

    this.count++;

    return next.handle(req)

      .pipe(tap(
        event => console.log(event),

        error => console.log(error)
        ), finalize(() => {

          this.count--;

          if (this.count == 0) this.spinner.hide()
        })
      );
  }

}
