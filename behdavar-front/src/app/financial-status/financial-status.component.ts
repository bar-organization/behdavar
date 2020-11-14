import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FinancialStatusLang} from "../model/lang";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {PaymentDto} from "../model/model";
import Url from "../model/url";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {ContractStatusPip} from "../_pip/ContractStatusPip";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {ActivatedRoute} from "@angular/router";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {PaymentService} from "../service/payment-service";
import {ContractService} from "../service/contract-service";

@Component({
  selector: 'app-financial-status',
  templateUrl: './financial-status.component.html',
  styleUrls: ['./financial-status.component.css']
})
export class FinancialStatusComponent implements OnInit {

  financialStatusLang = new FinancialStatusLang();

  paymentHttpDataSource: HttpDataSource<PaymentDto>
  receiveAmount: number;
  tableColumns: TableColumn[] = [
    {fieldName: 'amount', title: this.financialStatusLang.amount},
    {fieldName: 'paymentType', title: this.financialStatusLang.paymentType},
    {fieldName: 'user.firstName+user.lastName', title: this.financialStatusLang.expert},
    {
      fieldName: 'contract.contractStatus',
      title: this.financialStatusLang.contractStatus,
      pipNames: FinancialStatusComponent.getContractStatusPip()
    },
    {fieldName: 'paymentDate', title: this.financialStatusLang.paymentDate},
  ];

  private static getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }


  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private paymentService: PaymentService, private contractService: ContractService) {
  }


  ngOnInit(): void {
    const filters: SearchCriteria[] = [];
    filters.push({key: 'contract.id', value: this.contractService.currentId, operation: SearchOperation.EQUAL});
    this.paymentHttpDataSource = new HttpDataSource<PaymentDto>(Url.PAYMENT_FIND_PAGING, this.httpClient, filters);
    this.paymentService.getAllPaymentByContractId(this.contractService.currentId, result => {
      this.receiveAmount = result.map(payment => payment.amount).reduce((pre, cur) => pre + cur);
    })
  }

}
