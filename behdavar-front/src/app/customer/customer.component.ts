import {AfterViewInit, Component, OnInit} from '@angular/core';
import {CustomerLang} from '../model/lang';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {CustomerDto} from "../model/model";
import Url from "../model/url";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit , AfterViewInit{

  lang = new CustomerLang();
  customerForm: FormGroup;
  matcher = new MyErrorStateMatcher();


  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute,private router:Router,private _snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      person: this.fb.group({
        id:[''],
        firstName: [''],
        lastName: [''],
        fullName:[''],
        email: [''],
        description: [''],
        fatherName: [''],
        birthDate: [''],
        nationalCode: [''],
        postalCode: [''],
        phoneNumber: [''],
        telephone: [''],
        birthPlace: [''],
        workPlacePhone: [''],
      })
    });

    this.httpClient.post<CustomerDto>(Url.CUSTOMER_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => {this.customerForm.patchValue(value[0]);console.log(value)});

  }
  ngAfterViewInit(): void {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;

      this.httpClient.post<unknown>(Url.PERSON_UPDATE, this.customerForm.value['person'])
        .subscribe(value => {
            this._snackBar.open(this.lang.successSave, 'X', {
              duration: 5000, panelClass: ['bg-success', 'text-white']
            });
            this.router.navigate(['../']);
          },
          error => this._snackBar.open(`${this.lang.error} [${error}] `, 'X', {
            duration: 5000, panelClass: ['bg-danger', 'text-white']
          })
        );


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
