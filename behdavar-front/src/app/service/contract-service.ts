import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {ContractDto} from "../model/model";
import Url from "../model/url";
import {Subject} from "rxjs";
import {ContractStatus} from "../model/enum/ContractStatus";

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

  updateCurrentId(id: number): void {
    this.currentIdSubject.next(id);
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
      });
  }

  updateStatus(contractId: number, newStatus: ContractStatus, onComplete: () => void): void {
    this.httpClient
      .post<void>(Url.CONTRACT_UPDATE_STATUS, {contractId, newStatus})
      .subscribe(value => {
        if (onComplete) {
          onComplete();
        }
      });
  }
}
