import {AfterViewInit, Component, OnInit} from '@angular/core';
import {CustomerLang} from '../model/lang';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {CustomerDto} from "../model/model";
import Url from "../model/url";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit , AfterViewInit{

  lang = new CustomerLang();
  customerForm: FormGroup;
  matcher = new MyErrorStateMatcher();


  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      person: this.fb.group({
        firstName: [''],
        lastName: [''],
        email: [''],
        description: [''],
        fatherName: [''],
        birthDate: [''],
        nationalNumber: [''],
        postalCode: [''],
        mobile: [''],
        telephone: [''],
        birthPlace: [''],
        workPlacePhone: [''],
      })
    });

    this.httpClient.post<CustomerDto>(Url.CUSTOMER_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => this.customerForm.patchValue(value));

  }
  ngAfterViewInit(): void {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
    this.httpClient.post<CustomerDto>(Url.CUSTOMER_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => this.customerForm.patchValue(value));
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

  }

}
