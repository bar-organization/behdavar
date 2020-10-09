import {Component} from "@angular/core";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {CartableDto} from "../model/model";
import Url from "../model/url";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {DocumentLang} from "../model/lang";
import {HttpClient} from "@angular/common/http";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {Router} from "@angular/router";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html'
})
export class DocumentComponent {

  documentLang = new DocumentLang();
  catalogHttpDataSource: HttpDataSource<CartableDto>;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.catalogHttpDataSource = new HttpDataSource<CartableDto>(this.getUrl(), this.httpClient);
  }


  private getUrl(): string {
    return this.router && this.router.url.match('my-basket') ? Url.CARTABLE_FIND_PAGING : Url.CARTABLE_FIND_PAGING_ALL;
  }

  tableColumns: TableColumn[] = [
    {fieldName: "contract.customers[0].person.fullName", title: this.documentLang.customerName},
    {fieldName: 'contract.lendingNumber', title: this.documentLang.facilityNumber, pipNames: this.getSimplePip()},
    {fieldName: 'contract.contractStatus', title: this.documentLang.status, pipNames: this.getSimplePip()},
    {fieldName: 'contract.lateFees', title: this.documentLang.lateFees, pipNames: this.getSimplePip()},
    {fieldName: 'contract.defferedAmount', title: this.documentLang.deferredAmount, pipNames: this.getSimplePip()},
    {fieldName: 'contract.defferedCount', title: this.documentLang.deferredCount, pipNames: this.getSimplePip()},
    {fieldName: 'contract.masterAmount', title: this.documentLang.totalAmount, pipNames: this.getSimplePip()},
    {fieldName: 'contract.submitDate', title: this.documentLang.registrationDate, pipNames: this.getDatePip()},
    {fieldName: 'sender.firstName', title: this.documentLang.expert},
    // {fieldName: 'contract.lending.ideaIssueDate', title: this.documentLang.ideaIssueDate, pipNames: this.getDatePip()},
    // {fieldName: 'contract.lending.receiveLendingDate', title: this.documentLang.receiveLendingDate, pipNames: this.getDatePip()},
    // {fieldName: 'contract.lending.branchBank.code', title: this.documentLang.branch,pipNames:this.getSimplePip()},
    // {fieldName: 'contract.lending.branchBank.name', title: this.documentLang.bank,pipNames:this.getSimplePip()},
    {fieldName: 'contract.product.productPlate', title: this.documentLang.plateNumber, pipNames: this.getSimplePip()},
    {fieldName: 'contract.product.productName', title: this.documentLang.vehicleType, pipNames: this.getSimplePip()},
  ];

  private getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  private getSimplePip() {
    return [{pip: new BlankToDashPipe()}];

  }
}
