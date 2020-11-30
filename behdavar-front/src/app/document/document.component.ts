import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {CartableDto} from "../model/model";
import Url from "../model/url";
import {DataTableComponent, TableColumn} from "../_custom-component/data-table/data-table.component";
import {DocumentLang} from "../model/lang";
import {HttpClient} from "@angular/common/http";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {ActivatedRoute, Router} from "@angular/router";
import {ContractStatusPip} from "../_pip/ContractStatusPip";
import {AuthService} from "../service/auth/auth.service";
import {AuthorityConstantEnum} from "../model/enum/AuthorityConstantEnum";
import {ThousandPip} from "../_pip/ThousandPip";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {ContractStatus} from "../model/enum/ContractStatus";
import {ContractService} from "../service/contract-service";
import {DocumentCacheService} from "../service/document-cache.service";

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html'
})
export class DocumentComponent implements OnInit, AfterViewInit {
  documentLang = new DocumentLang();
  catalogHttpDataSource: HttpDataSource<CartableDto>;
  @ViewChild("table") documentTable: DataTableComponent;

  constructor(private httpClient: HttpClient, private router: Router, private route: ActivatedRoute, private authService: AuthService, private contractService: ContractService, private documentCacheService: DocumentCacheService) {
    this.catalogHttpDataSource = new HttpDataSource<CartableDto>(this.getUrl(), this.httpClient, this.isMyBaskUrl() ? [DocumentComponent.getMyBasketFilter()] : null);
  }

  ngOnInit(): void {
    this.contractService.clearCurrentId();
  }

  ngAfterViewInit(): void {
    this.documentCacheService.initCache({columnToDisplay: this.documentTable.columnToDisplay});

    this.documentCacheService.applyCache(value => {
      this.documentTable.columnToDisplay = value.columnToDisplay;
      this.documentTable.refreshSelectableColumns();
    })
  }

  onColumnDisplayChange(columnToDisplay: string[]) {
    this.documentCacheService.updateCache({columnToDisplay: columnToDisplay})
  }

  private getUrl(): string {
    if (!this.router) {
      return Url.CARTABLE_FIND_PAGING;
    }

    if (this.router.url.match('document-manage'))
      return Url.CARTABLE_FIND_PAGING_ALL;

    if (this.router.url.match('search'))
      return this.authService.hasAuthority(AuthorityConstantEnum.SEARCH_ALL) ? Url.CARTABLE_FIND_PAGING_ALL : Url.CARTABLE_FIND_PAGING;

    return Url.CARTABLE_FIND_PAGING;

  }

  private static getMyBasketFilter(): SearchCriteria {
    return {
      key: 'contract.contractStatus', value: ContractStatus.AVAILABLE.valueOf(),
      operation: SearchOperation.EQUAL
    };
  }

  tableColumns: TableColumn[] = [
    {fieldName: "contract.customers[0].person.fullName", title: this.documentLang.customerName},
    {
      fieldName: 'contract.contractNumber',
      colName: 'contract.contractNumber',
      sortable: true,
      title: this.documentLang.facilityNumber,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.contractStatus',
      colName: 'contract.contractStatus',
      sortable: true,
      title: this.documentLang.status,
      pipNames: DocumentComponent.getContractStatusPip()
    },
    {
      fieldName: 'contract.lending.lateFees',
      colName: 'contract.lending.lateFees',
      sortable: true,
      title: this.documentLang.lateFees,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.lending.defferedAmount',
      colName: 'contract.lending.defferedAmount',
      sortable: true,
      title: this.documentLang.deferredAmount,
      pipNames: DocumentComponent.getThousandPip()
    },
    {
      fieldName: 'contract.lending.defferedCount',
      colName: 'contract.lending.defferedCount',
      sortable: true,
      title: this.documentLang.deferredCount,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.lending.masterAmount',
      colName: 'contract.lending.masterAmount',
      sortable: true,
      title: this.documentLang.totalAmount,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.submitDate',
      colName: 'contract.submitDate',
      sortable: true,
      title: this.documentLang.registrationDate,
      pipNames: DocumentComponent.getDatePip()
    },
    {
      fieldName: 'receiver.firstName+receiver.lastName',
      title: this.documentLang.expert,
      hidden: !this.authService.hasAnyAuthority(AuthorityConstantEnum.VIEW_DOCUMENT_ENTRY, AuthorityConstantEnum.SEARCH_ALL)
    },

    {
      fieldName: 'contract.lending.ideaIssueDate',
      colName: 'contract.lending.ideaIssueDate',
      sortable: true,
      title: this.documentLang.ideaIssueDate,
      pipNames: DocumentComponent.getDatePip()
    },
    {
      fieldName: 'contract.lending.receiveLendingDate',
      colName: 'contract.lending.receiveLendingDate',
      sortable: true,
      title: this.documentLang.receiveLendingDate,
      pipNames: DocumentComponent.getDatePip()
    },
    {
      fieldName: 'contract.lending.branchBank.code',
      colName: 'contract.lending.branchBank.code',
      sortable: true,
      title: this.documentLang.branch,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.lending.branchBank.name',
      colName: 'contract.lending.branchBank.name',
      sortable: true,
      title: this.documentLang.bank,
      pipNames: DocumentComponent.getSimplePip()
    },

    {
      fieldName: 'contract.product.productPlate',
      colName: 'contract.product.productPlate',
      sortable: false,
      title: this.documentLang.plateNumber,
      pipNames: DocumentComponent.getSimplePip()
    },
    {
      fieldName: 'contract.product.productName',
      colName: 'contract.product.productName',
      sortable: true,
      title: this.documentLang.vehicleType,
      pipNames: DocumentComponent.getSimplePip()
    },
  ];

  private static getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  private static getSimplePip() {
    return [{pip: new BlankToDashPipe()}];

  }

  private static getThousandPip() {
    return [{pip: new ThousandPip()}, {pip: new BlankToDashPipe()}];
  }

  private static getContractStatusPip() {
    return [{pip: new ContractStatusPip()}, {pip: new BlankToDashPipe()}];
  }

  onSelectedValueChange(selectedValue: CartableDto) {
    this.contractService.currentIdSubject.next(selectedValue?.contract?.id);
    this.router.navigate(['../following', this.contractService.currentId], {relativeTo: this.route});
  }

  private isMyBaskUrl(): boolean {
    return !!this.router.url.match('my-basket');
  }
}
