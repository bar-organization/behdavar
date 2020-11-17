import {Component, OnInit} from "@angular/core";
import {DocumentFlowLang} from "../model/lang";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {ActivatedRoute} from "@angular/router";
import {ContractService} from "../service/contract-service";

@Component({
  selector: 'document-flow',
  templateUrl: './document-flow.component.html',
  styleUrls: ['./document-flow.component.css']
})
export class DocumentFlowComponent implements OnInit {
  lang: DocumentFlowLang = new DocumentFlowLang();
  contractNumber: number;
  tableColumns: TableColumn[];
  documentFlowHttpDataSource: HttpDataSource<unknown>;

  constructor(private route: ActivatedRoute, private contractService: ContractService) {
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

    this.tableColumns = [
      {fieldName: 'previousExpert', title: this.lang.previousExpert},
      {fieldName: 'newExpert', title: this.lang.newExpert},
      {fieldName: 'referralUnit', title: this.lang.referralUnit},
      {fieldName: 'date', title: this.lang.date},
      {fieldName: 'time', title: this.lang.time}
    ]
  }

}
