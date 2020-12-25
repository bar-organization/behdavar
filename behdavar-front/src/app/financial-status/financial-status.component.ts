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
import {ThousandPip} from "../_pip/ThousandPip";
import {PaymentTypePip} from "../_pip/PaymentTypePip";
import {JalaliPipe} from "../_pip/jalali.pipe";

@Component({
  selector: 'app-financial-status',
  templateUrl: './financial-status.component.html',
  styleUrls: ['./financial-status.component.css']
})
export class FinancialStatusComponent implements OnInit {

  financialStatusLang = new FinancialStatusLang();

  paymentHttpDataSource: HttpDataSource<PaymentDto>
  totalAmount: number;
  receiveAmount: number;
  tableColumns: TableColumn[] = [
    {fieldName: 'amount', title: this.financialStatusLang.amount, pipNames: FinancialStatusComponent.getThousandPip()},
    {
      fieldName: 'paymentType', title: this.financialStatusLang.paymentType,
      pipNames: FinancialStatusComponent.getPaymentTypePip()
    },
    {fieldName: 'user.firstName+user.lastName', title: this.financialStatusLang.expert},
    {
      fieldName: 'contract.contractStatus',
      title: this.financialStatusLang.contractStatus,
      pipNames: FinancialStatusComponent.getContractStatusPip()
    },
    {
      fieldName: 'paymentDate',
      title: this.financialStatusLang.paymentDate,
      pipNames: FinancialStatusComponent.getDatePip()
    },
  ];

  private static getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }

  private static getPaymentTypePip() {
    return [{pip: new PaymentTypePip()}, {pip: new BlankToDashPipe()}];
  }

  private static getThousandPip() {
    return [{pip: new ThousandPip()}, {pip: new BlankToDashPipe()}];
  }

  private static getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private paymentService: PaymentService, private contractService: ContractService) {
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
      this.totalAmount = contractDto?.lending?.masterAmount;
    });

    this.paymentService.getTotalDepositAmount(this.contractService.currentId, totalDepositAmount => {
      this.receiveAmount = totalDepositAmount
    });

    const filters: SearchCriteria[] = [];
    filters.push({key: 'contract.id', value: this.contractService.currentId, operation: SearchOperation.EQUAL});
    this.paymentHttpDataSource = new HttpDataSource<PaymentDto>(Url.PAYMENT_FIND_PAGING, this.httpClient, filters);

  }

}
