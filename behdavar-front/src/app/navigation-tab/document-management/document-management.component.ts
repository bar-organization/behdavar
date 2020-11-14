import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Lang} from "../../model/lang";
import {ContractService} from "../../service/contract-service";

@Component({
  selector: 'document-management',
  templateUrl: './document-management.component.html'
})
export class DocumentManagementComponent implements OnInit {
  private lang = new Lang();

  constructor(public route: Router, public contractService: ContractService) {
  }

  ngOnInit(): void {
    this.contractService.clearCurrentId();
  }
}
