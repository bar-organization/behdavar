import {Component, OnInit} from "@angular/core";
import {DocumentChangeStatusLang} from "../model/lang";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {HttpClient} from "@angular/common/http";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {ContractStatusPip} from "../_pip/ContractStatusPip";
import {ContractService} from "../service/contract-service";
import {ActivatedRoute} from "@angular/router";
import {FormControl} from "@angular/forms";
import {CONTRACT_STATUS_TITLE} from "../model/enum/ContractStatus";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {MessageService} from "../service/message.service";

@Component({
  selector: 'document-change-status',
  templateUrl: './document-change-status.component.html',
  styleUrls: ['./document-change-status.component.css']
})
export class DocumentChangeStatusComponent implements OnInit {
  lang: DocumentChangeStatusLang = new DocumentChangeStatusLang();
  documentNumber: string;
  documentStatusFormControl: FormControl = new FormControl('');

  documentHistoryHttpDataSource: HttpDataSource<unknown>;
  tableColumns: TableColumn[];
  contractStatusList: EnumValueTitle<string>[] = CONTRACT_STATUS_TITLE;


  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private contractService: ContractService, private messageService: MessageService) {
  }

  private updateContractId() {
    let id = this.route.snapshot.params['id'];
    try {
      this.contractService.updateCurrentId(Number(id));
    } catch (e) {
    }
  }

  ngOnInit(): void {
    this.updateContractId();

    //TODO must fix type and url
    // this.documentHistoryHttpDataSource = new HttpDataSource<unknown>(null, this.httpClient);
    // this.tableColumns = [
    //   {fieldName: 'date', title: this.lang.date, pipNames: DocumentChangeStatusComponent.getDatePip()},
    //   {
    //     fieldName: 'changeStatus',
    //     title: this.lang.status,
    //     pipNames: DocumentChangeStatusComponent.getContractStatusPip()
    //   },
    // ];

    this.contractService.getById(this.contractService.currentId, contractDto => {
      this.documentNumber = contractDto?.contractNumber;
      this.documentStatusFormControl.setValue(contractDto?.contractStatus);
    });
  }


  private static getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  private static getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }

  applyChange() {
    this.contractService.updateStatus(this.contractService.currentId, this.documentStatusFormControl.value, () => {
      this.messageService.showGeneralSuccess(this.lang.successSave);
    })
  }
}
