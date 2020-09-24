import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ContactLang, CustomerLang} from '../model/lang';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {ContactDto, CustomerDto, PersonDto} from "../model/model";
import Url from "../model/url";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatTableDataSource} from "@angular/material/table";
import {TableColumn} from "../_custom-component/data-table/data-table.component";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit , AfterViewInit{

  lang = new CustomerLang();
  contactLang = new ContactLang();
  customerForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  contactList:ContactDto[];

  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute,private router:Router,private _snackBar:MatSnackBar) {
  }

  submitted = false;
  contactDataSource: MatTableDataSource<ContactDto>;
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
      .subscribe(value => {
        const customerDto:CustomerDto = value[0];
        this.customerForm.patchValue(customerDto);
        this.contactList = customerDto?.person?.contacts;
      });

  }

  ngAfterViewInit(): void {
  }

  onSubmit() {
    this.submitted = true;
    const newPerson:PersonDto = this.customerForm.value['person'];
    newPerson.contacts = this.contactList;
    this.removeTraceableField(newPerson);
    this.httpClient.post<unknown>(Url.PERSON_UPDATE, newPerson)
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

  private removeTraceableField(newPerson: PersonDto) {
    newPerson.createdDate = null;
    newPerson.lastModifiedDate = null;
    if (newPerson.contacts) {
      for (let contact of newPerson.contacts) {
        contact.createdDate = null;
        contact.lastModifiedDate = null;
      }
    }
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
