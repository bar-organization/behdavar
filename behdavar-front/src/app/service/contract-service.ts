import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {ContractDto} from "../model/model";
import Url from "../model/url";

@Injectable()
export class ContractService {
  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }

  getById(contractId: number, onComplete: (result: ContractDto) => void): void {
    this.httpClient
      .post<ContractDto>(Url.CONTRACT_FIND_BY_ID, contractId)
      .subscribe(value => {
        if (onComplete) {
          onComplete(value);
        }
      }, error => this.messageService.showGeneralError(`cant find contract with id: ${contractId}`));
  }
}
