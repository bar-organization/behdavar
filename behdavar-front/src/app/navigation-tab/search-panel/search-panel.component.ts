import { Component, OnInit } from '@angular/core';
import {Lang} from "../../model/lang";
import {Router} from "@angular/router";
import {ContractService} from "../../service/contract-service";

@Component({
  selector: 'search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {
  private lang = new Lang();

  constructor(public route: Router,public contractService:ContractService) {
  }

  ngOnInit(): void {
    this.contractService.clearCurrentId();
  }

}
