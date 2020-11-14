import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {ContractDto} from "../model/model";
import Url from "../model/url";
import {Subject} from "rxjs";

@Injectable()
export class ContractService {
  private _currentId: number;

  constructor(private httpClient: HttpClient, private messageService: MessageService) {
    this.currentIdSubject.subscribe(v => this._currentId = v);
  }

  currentIdSubject: Subject<number> = new Subject<number>();

  get currentId() {
    return this._currentId;
  }

  clearCurrentId(): void {
    this.currentIdSubject.next(null);
  }

  getById(contractId: number, onComplete: (result: ContractDto) => void): void {
    this.httpClient
      .post<ContractDto>(Url.CONTRACT_FIND_BY_ID, contractId)
      .subscribe(value => {
        if (onComplete) {
          onComplete(value);
        }
      }, () => this.messageService.showGeneralError(`cant find contract with id: ${contractId}`));
  }
}
