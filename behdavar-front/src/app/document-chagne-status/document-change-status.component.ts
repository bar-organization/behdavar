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
  contractStatusList: EnumValueTitle<string>[] =   CONTRACT_STATUS_TITLE;



  constructor(private httpClient: HttpClient,private route: ActivatedRoute,private contractService:ContractService) {
  }

  ngOnInit(): void {

    //TODO must fix type and url
    this.documentHistoryHttpDataSource = new HttpDataSource<unknown>(null, this.httpClient);
    this.tableColumns = [
      {fieldName: 'date', title: this.lang.date, pipNames: this.getDatePip()},
      {fieldName: 'changeStatus', title: this.lang.status, pipNames: this.getContractStatusPip()},
    ];

    this.contractService.getById(this.getIdParam(), contractDto => {
      this.documentNumber = contractDto?.contractNumber;
    });
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

  }
  private getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  private getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }

  applyChange() {
    // TODO must call right service
    console.log(this.documentStatusFormControl.value);
  }
}
