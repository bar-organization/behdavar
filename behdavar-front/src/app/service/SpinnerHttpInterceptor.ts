import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {NgxSpinnerService} from "ngx-spinner";
import {catchError, finalize, tap} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {MessageService} from "./message.service";

@Injectable()
export class SpinnerHttpInterceptor implements HttpInterceptor {

  count = 0;

  constructor(private spinner: NgxSpinnerService, private messageService: MessageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinner.show()

    this.count++;

    return next.handle(req)

      .pipe(tap(
        // TODO is necessary to something hear ?
        ), catchError((error: HttpErrorResponse) => {
          let data = {};
          data = {
            reason: error && error.error && error.error.reason ? error.error.reason : '',
            status: error.status
          };

          if (error.status === 403) {
            this.messageService.showGeneralError("خطا در احراز هویت.");
          } else if (error.status === 500) {
            this.messageService.showGeneralError('خطا در سرور.', error?.error?.message);
          } else if (error.status === 0) {
            this.messageService.showGeneralError('خطا در ارتباط با سرور.', error?.error?.message);
          }
          return throwError(error);
        })
        , finalize(() => {

          this.count--;

          if (this.count == 0) this.spinner.hide()
        })
      );
  }

}
