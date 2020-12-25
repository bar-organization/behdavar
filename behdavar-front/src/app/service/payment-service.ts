import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {PaymentDto} from "../model/model";
import Url from "../model/url";

@Injectable()
export class PaymentService {
  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }

  getTotalDepositAmount(contractId: number, onComplete: (result: number) => void): void {
    this.httpClient
      .post<number>(Url.PAYMENT_TOTAL_DEPOSIT, contractId)
      .subscribe(value => {
        if (onComplete) {
          onComplete(value);
        }
      }, error => this.messageService.showGeneralError(`cant find Payment by contract id: ${contractId}`));
  }
}
