import {Component, OnInit} from "@angular/core";
import {DocumentFlowLang} from "../model/lang";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {ActivatedRoute, Router} from "@angular/router";
import {ContractService} from "../service/contract-service";
import {CartableDto} from "../model/model";
import Url from "../model/url";
import {AuthorityConstantEnum} from "../model/enum/AuthorityConstantEnum";
import {AuthService} from "../service/auth/auth.service";
import {HttpClient} from "@angular/common/http";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";

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
    this.documentFlowHttpDataSource = new HttpDataSource<CartableDto>(this.getUrl(), this.httpClient, [defaultFilter]);

    this.tableColumns = [
      {fieldName: 'previousExpert', title: this.lang.previousExpert},
      {fieldName: 'newExpert', title: this.lang.newExpert},
      {fieldName: 'referralUnit', title: this.lang.referralUnit},
      {fieldName: 'date', title: this.lang.date},
      {fieldName: 'time', title: this.lang.time}
    ]
  }

}
