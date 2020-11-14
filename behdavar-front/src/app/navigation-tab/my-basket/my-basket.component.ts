import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Lang} from "../../model/lang";
import {ContractService} from "../../service/contract-service";

@Component({
  selector: 'my-basket',
  templateUrl: './my-basket.component.html',
  styleUrls: ['./my-basket.component.css']
})
export class MyBasketComponent implements OnInit {
  private lang = new Lang();

  constructor(public route: Router,public contractService:ContractService) {
  }

  ngOnInit(): void {
    this.contractService.clearCurrentId();
  }

}
