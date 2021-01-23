import {Component, OnInit} from "@angular/core";
import {DocumentFlowLang} from "../model/lang";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {ActivatedRoute, Router} from "@angular/router";
import {ContractService} from "../service/contract-service";
import {CartableDto} from "../model/model";
import Url from "../model/url";
import {AuthService} from "../service/auth/auth.service";
import {HttpClient} from "@angular/common/http";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";

@Component({
  selector: 'document-flow',
  templateUrl: './document-flow.component.html',
  styleUrls: ['./document-flow.component.css']
})
export class DocumentFlowComponent implements OnInit {
  lang: DocumentFlowLang = new DocumentFlowLang();
  contractNumber: string;
  tableColumns: TableColumn[];
  documentFlowHttpDataSource: HttpDataSource<CartableDto>;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private authService: AuthService, private router: Router, private contractService: ContractService) {
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

    this.contractService.getById(this.contractService.currentId, contractDto => {
      this.contractNumber = contractDto?.contractNumber;
    });
    const defaultFilter: SearchCriteria = {
      key: 'contract.id',
      operation: SearchOperation.EQUAL,
      value: this.contractService.currentId
    };
    this.documentFlowHttpDataSource = new HttpDataSource<CartableDto>(Url.CARTABLE_FIND_PAGING_DOC_FLOW, this.httpClient, [defaultFilter]);

    this.tableColumns = [
      {
        fieldName: 'previousReceiver.firstName+previousReceiver.lastName',
        title: this.lang.previousExpert,
        pipNames: [{pip: new BlankToDashPipe()}]
      },
      // {
      //   fieldName: 'newReceiver.firstName+newReceiver.lastName',
      //   title: this.lang.newExpert,
      //   pipNames: [{pip: new BlankToDashPipe()}]
      // },
      {
        fieldName: 'sender.firstName+sender.lastName',
        title: this.lang.referralUnit,
        pipNames: [{pip: new BlankToDashPipe()}]
      },
      {
        fieldName: 'createdDate',
        colName: 'createdDate',
        title: this.lang.date,
        pipNames: [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}]
      },
      {
        fieldName: 'createdDate',
        title: this.lang.time,
        pipNames: [{pip: new JalaliPipe(), args: ['HH:mm']}, {pip: new BlankToDashPipe()}]
      }
    ]
  }

}
