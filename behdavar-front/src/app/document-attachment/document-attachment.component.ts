import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AttachmentLang} from "../model/lang";
import {FormGroup} from "@angular/forms";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {AttachmentDto, CartableDto, ContractDto} from "../model/model";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import Url from "../model/url";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../service/message.service";
import {SearchOperation} from "../_custom-component/data-table/PaginationModel";

@Component({
  selector: 'document-attachment',
  templateUrl: './document-attachment.component.html',
  styleUrls: ['./document-attachment.component.css']
})
export class DocumentAttachmentComponent implements OnInit, AfterViewInit {

  lang = new AttachmentLang();
  attachmentForm: FormGroup;
  fileName: string = null;
  fileToUpload: string = null;

  documentNumber: string;
  attachmentHttpDataSource: HttpDataSource<AttachmentDto>;
  tableColumns: TableColumn[] = [
    {fieldName: 'fileName', title: this.lang.titleDocument},
    // {fieldName: 'content', title: this.lang.fileDocument},
    {fieldName: 'fileName',colName:'content', title: this.lang.titleDocument},
  ];

  constructor(private router: Router ,private route: ActivatedRoute, private httpClient: HttpClient, private messageService: MessageService) {
  }

  ngOnInit(): void {
    const filterByContractId = [{
      key: 'contract.id',
      value: this.getIdParam(),
      operation: SearchOperation.EQUAL
    }];
    this.attachmentHttpDataSource = new HttpDataSource<AttachmentDto>(Url.ATTACHMENT_FIND_PAGING, this.httpClient, filterByContractId);
  }

  ngAfterViewInit(): void {
    this.findContractNumber();
  }

  private findContractNumber() {
    this.httpClient.post<ContractDto>(Url.CONTRACT_FIND_BY_ID, this.getIdParam())
      .subscribe(value => this.documentNumber = value.contractNumber);
  }

  handleFileInput(files: FileList) {
    const userFile = files.item(0);
    this.fileName = userFile.name;
    const reader = new FileReader();
    reader.readAsDataURL(userFile);
    reader.onload = () => {
      this.fileToUpload = reader.result as string;
      this.fileToUpload = this.fileToUpload.slice(this.fileToUpload.indexOf('base64') + 7, this.fileToUpload.length);
    };
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

  }

  attach() {
    const attachmentDto: AttachmentDto = new AttachmentDto();
    attachmentDto.fileName = this.fileName;
    attachmentDto.content = this.fileToUpload;
    attachmentDto.contract = {id: this.getIdParam()}

    // TODO must fix
    attachmentDto.attachmentType = {id: 1};

    this.httpClient.post(Url.ATTACHMENT_SAVE, attachmentDto)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.router.navigate(['.'], {relativeTo: this.route.parent});
        },
        error => this.messageService.showGeneralError(this.lang.error));
  }
}
