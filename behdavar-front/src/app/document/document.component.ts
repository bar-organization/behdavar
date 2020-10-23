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
import {ContractStatusPip} from "../_pip/ContractStatusPip";
import {AuthService} from "../service/auth/auth.service";
import {AuthorityConstantEnum} from "../model/enum/AuthorityConstantEnum";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html'
})
export class DocumentComponent {
  contractId: number;
  documentLang = new DocumentLang();
  catalogHttpDataSource: HttpDataSource<CartableDto>;

  constructor(private httpClient: HttpClient, private router: Router, private authService: AuthService) {
    this.catalogHttpDataSource = new HttpDataSource<CartableDto>(this.getUrl(), this.httpClient);
  }


  private getUrl(): string {
    return this.router && this.router.url.match('my-basket') ? Url.CARTABLE_FIND_PAGING : Url.CARTABLE_FIND_PAGING_ALL;
  }

  tableColumns: TableColumn[] = [
    {fieldName: "contract.customers[0].person.fullName", title: this.documentLang.customerName},
    {fieldName: 'contract.contractNumber', title: this.documentLang.facilityNumber, pipNames: this.getSimplePip()},
    {fieldName: 'contract.contractStatus', title: this.documentLang.status, pipNames: this.getContractStatusPip()},
    {fieldName: 'contract.lending.lateFees', title: this.documentLang.lateFees, pipNames: this.getSimplePip()},
    {
      fieldName: 'contract.lending.defferedAmount',
      title: this.documentLang.deferredAmount,
      pipNames: this.getSimplePip()
    },
    {
      fieldName: 'contract.lending.defferedCount',
      title: this.documentLang.deferredCount,
      pipNames: this.getSimplePip()
    },
    {fieldName: 'contract.lending.masterAmount', title: this.documentLang.totalAmount, pipNames: this.getSimplePip()},
    {fieldName: 'contract.submitDate', title: this.documentLang.registrationDate, pipNames: this.getDatePip()},
    {
      fieldName: 'sender.firstName+sender.lastName',
      title: this.documentLang.expert,
      hidden:  !this.authService.hasAuthority(AuthorityConstantEnum.VIEW_DOCUMENT_ENTRY)
    },

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

  private getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }

  onSelectedValueChange(selectedValue: CartableDto) {
    this.contractId = selectedValue?.contract?.id;
  }
}
