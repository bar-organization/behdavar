import {Component} from "@angular/core";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {CartableDto} from "../model/model";
import Url from "../model/url";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {DocumentLang} from "../model/lang";
import {HttpClient} from "@angular/common/http";

@Component({
  selector:'app-document',
  templateUrl:'./document.component.html'
})
export class DocumentComponent {
  documentId: number = 1;

  // onChangge(event: number) {
  //   this.documentId = event;
  //   console.log(event);
  // }
  documentLang = new DocumentLang();

  constructor(private httpClient: HttpClient) {
  }
  catalogHttpDataSource: HttpDataSource<CartableDto> = new HttpDataSource<CartableDto>(Url.CARTABLE_FIND_PAGING, this.httpClient);

  tableColumns: TableColumn[] = [
    {fieldName: "receiver.firstName", title: this.documentLang.customerName},
    {fieldName: 'contract.lendingNumber', title: this.documentLang.facilityNumber},
    {fieldName: 'contract.contractStatus', title: this.documentLang.status},
    {fieldName: 'contract.lateFees', title: this.documentLang.lateFees},
    {fieldName: 'contract.defferedAmount', title: this.documentLang.deferredAmount},
    {fieldName: 'contract.defferedCount', title: this.documentLang.deferredCount},
    {fieldName: 'contract.masterAmount', title: this.documentLang.totalAmount},
    {fieldName: 'contract.submitDate', title: this.documentLang.registrationDate},
  ]
}
