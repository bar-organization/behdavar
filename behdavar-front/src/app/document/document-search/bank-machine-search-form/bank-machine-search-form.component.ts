import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {BankMachineSearchFormLang} from "../../../model/lang";

@Component({
  selector: 'app-bank-machine-search-form',
  templateUrl: './bank-machine-search-form.component.html',
  styleUrls: ['./bank-machine-search-form.component.css']
})
export class BankMachineSearchFormComponent implements OnInit {

  @Input()
  backMachineSearchForm: FormGroup;

  bankMachineSearchFormLang: BankMachineSearchFormLang = new BankMachineSearchFormLang();

  constructor() {
  }

  ngOnInit(): void {
  }

}
