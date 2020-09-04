import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FinancialStatusLang} from "../model/lang";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {CartableDto, PaymentDto} from "../model/model";
import Url from "../model/url";
import {TableColumn} from "../_custom-component/data-table/data-table.component";

@Component({
  selector: 'app-financial-status',
  templateUrl: './financial-status.component.html',
  styleUrls: ['./financial-status.component.css']
})
export class FinancialStatusComponent implements OnInit {

  financialStatusLang = new FinancialStatusLang();

  paymentHttpDataSource: HttpDataSource<PaymentDto> = new HttpDataSource<PaymentDto>(Url.CARTABLE_FIND_PAGING, this.httpClient);

  tableColumns: TableColumn[] = [
    {fieldName: 'amount', title: this.financialStatusLang.amount},
    {fieldName: 'paymentType', title: this.financialStatusLang.paymentType},
    {fieldName: 'user.fullName', title: this.financialStatusLang.expert},
    {fieldName: 'contract.contractStatus', title: this.financialStatusLang.contractStatus},
    {fieldName: 'paymentDate', title: this.financialStatusLang.paymentDate},
  ];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
  }

}
