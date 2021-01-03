import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import Url from "../model/url";
import {PursuitDto} from "../model/model";

@Injectable()
export class PursuitService {

  constructor(private httpClient: HttpClient, private messageService: MessageService) {
  }


  deleteById(pursuitId: number, onComplete: (deletedId: number) => void): void {
    this.httpClient
      .post<void>(Url.PURSUIT_DELETE, pursuitId)
      .subscribe(() => {
        if (onComplete) {
          onComplete(pursuitId);
        }
      });
  }

  editPursuit(pursuitDto: PursuitDto, onComplete: (pursuitDto: PursuitDto) => void): void {
    this.clearAuditFields(pursuitDto);
    this.httpClient
      .post<void>(Url.PURSUIT_EDIT, pursuitDto)
      .subscribe(() => {
        if (onComplete) {
          onComplete(pursuitDto);
        }
      });
  }

  private clearAuditFields(pursuitDto: PursuitDto) {
    if(!pursuitDto)
      return;
    pursuitDto.createdDate = null;
    pursuitDto.lastModifiedDate = null;
  }
}
