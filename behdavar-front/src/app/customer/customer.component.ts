import {Component, OnInit} from '@angular/core';
import {CustomerLang} from '../model/lang';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  lang = new CustomerLang();
  customerForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  customerFormControls: any;

  constructor(public fb: FormBuilder) {
  }
  ngOnInit(): void {
    this.customerForm = this.fb.group({
      name: [''],
      family: [''],
      fatherName: [''],
      birthDate: [''],
      nationalNumber: [''],
      postalCode: [''],
      mobile: [''],
      telephone: [''],
      birthPlace: [''],
      workPlacePhone: [''],
    });
    this.customerFormControls = this.customerForm.controls;
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }
}
