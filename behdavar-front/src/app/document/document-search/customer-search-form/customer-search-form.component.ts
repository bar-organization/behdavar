import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {CustomerSearchFormLang, DocumentSearchLang} from "../../../model/lang";

@Component({
  selector: 'app-customer-search-form',
  templateUrl: './customer-search-form.component.html',
  styleUrls: ['./customer-search-form.component.css']
})
export class CustomerSearchFormComponent implements OnInit {

  @Input()
  customerSearchForm: FormGroup;

  customerSearchFormLang: CustomerSearchFormLang = new CustomerSearchFormLang();
  constructor() { }

  ngOnInit(): void {
  }

}
