import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ContactLang, CustomerLang} from '../model/lang';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactDto, CustomerDto, PersonDto} from "../model/model";
import Url from "../model/url";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatTableDataSource} from "@angular/material/table";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {PHONE_TYPE_TITLE, PhoneType} from "../model/enum/PhoneType";
import {MatSelectionList} from "@angular/material/list";
import {MessageService} from "../service/message.service";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit, AfterViewInit {

  lang = new CustomerLang();
  contactLang = new ContactLang();
  customerForm: FormGroup;
  contactForm: FormGroup;
  matcher = new MyErrorStateMatcher();
  contactWrapperList: ContactWrapper[];

  @ViewChild("contactSelect")
  contactSelect: MatSelectionList;

  constructor(public messageService: MessageService, public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute, private router: Router, private _snackBar: MatSnackBar) {
  }

  submitted = false;
  contactDataSource: MatTableDataSource<ContactDto>;
  phoneTypeList: EnumValueTitle<PhoneType>[] = PHONE_TYPE_TITLE;

  ngOnInit(): void {

    this.customerForm = this.fb.group({
      person: this.fb.group({
        id: [''],
        firstName: [''],
        lastName: [''],
        fullName: [''],
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

    this.contactForm = this.fb.group({
      number: [''],
      phoneType: [PhoneType.MOBILE],
      confirmed: [false],
      description: [''],
    });
    this.httpClient.post<CustomerDto>(Url.CUSTOMER_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => {
        const customerDto: CustomerDto = value[0];
        this.customerForm.patchValue(customerDto);
        const contactList: ContactDto[] = customerDto?.person?.contacts;
        if (contactList) {
          this.contactWrapperList = contactList.map(value => <ContactWrapper>{
            contact: value,
            active: true,
            isNew: false
          });
        }
      });

  }

  ngAfterViewInit(): void {
  }

  onSubmit() {
    this.submitted = true;
    const newPerson: PersonDto = this.customerForm.value['person'];
    newPerson.contacts = this.contactWrapperList.filter(value => value.active).map(value => value.contact);
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

  contactSelectChange() {
    const contactDto: ContactDto = this.contactSelect.selectedOptions.selected[0]?.value;
    this.contactForm.reset({phoneType: PhoneType.MOBILE});
    this.contactForm.patchValue(contactDto);
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

  }


  onContactDelete(contact: ContactWrapper) {
    if (contact.isNew) {
      this.contactWrapperList = this.contactWrapperList.filter(value => value !== contact);
    } else {
      contact.active = !contact.active;
    }
  }

  addNewContact() {
    if (this.contactWrapperList) {
      const contact: ContactDto = {
        number: this.contactForm.value.number,
        confirmed: this.contactForm.value.confirmed,
        phoneType: this.contactForm.value.phoneType,
        description: this.contactForm.value.description
      }
      this.contactWrapperList.push(<ContactWrapper>{contact: contact, active: true, isNew: true})
    }
  }

  editContact(contactSelect: MatSelectionList) {

    if (!contactSelect || !contactSelect.selectedOptions.selected[0]?.value) {
      this.messageService.showGeneralError(this.contactLang.selectOneItem)
      return;
    }


    this.contactWrapperList.forEach(value => {
      const selectedContact: ContactDto = contactSelect.selectedOptions.selected[0]?.value;
      if (value.contact === selectedContact) {
        value.contact = {
          id: value.contact.id,
          number: this.contactForm.value.number,
          confirmed: this.contactForm.value.confirmed,
          phoneType: this.contactForm.value.phoneType,
          description: this.contactForm.value.description
        }
      }
    });
  }
}

interface ContactWrapper {
  contact: ContactDto;
  active: boolean;
  isNew?: boolean;
}
