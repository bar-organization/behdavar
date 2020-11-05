import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {PaymentDto} from "../model/model";
import Url from "../model/url";

@Injectable()
export class PaymentService {
  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }

  getAllPaymentByContractId(contractId: number, onComplete: (result: PaymentDto[]) => void): void {
    this.httpClient
      .post<PaymentDto[]>(Url.PAYMENT_FIND_BY_CONTRACT, contractId)
      .subscribe(value => {
        if (onComplete) {
          onComplete(value);
        }
      }, error => this.messageService.showGeneralError(`cant find Payment by contract id: ${contractId}`));
  }
}
