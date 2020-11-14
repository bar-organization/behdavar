import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ContactLang, GuarantorsLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {ActivatedRoute} from "@angular/router";
import {ContactDto, GuarantorDto, PersonDto} from "../model/model";
import {MatSelectionList} from "@angular/material/list";
import {MyErrorStateMatcher} from "../model/MyErrorStateMatcher";
import {MatSnackBar} from "@angular/material/snack-bar";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {PHONE_TYPE_TITLE, PhoneType} from "../model/enum/PhoneType";
import {MessageService} from "../service/message.service";
import {ContractService} from "../service/contract-service";

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit, AfterViewInit {

  @ViewChild("GLSelect")
  glSelect: MatSelectionList;

  contactList: ContactDto[];
  contactWrapperList: ContactWrapper[];
  contactForm: FormGroup;
  contactLang = new ContactLang();
  phoneTypeList: EnumValueTitle<PhoneType>[] = PHONE_TYPE_TITLE;

  @ViewChild("contactSelect")
  contactSelect: MatSelectionList;

  lang = new GuarantorsLang();
  guarantorsForm: FormGroup;

  matcher = new MyErrorStateMatcher();

  guarantorDtoList: GuarantorDto[];


  constructor(private messageService: MessageService, public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute, private contractService: ContractService, private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.guarantorsForm = this.fb.group({
      person: this.fb.group({
        id: [''],
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

    this.updateGuarantorList();

  }

  ngAfterViewInit(): void {

  }

  private getPerson(): PersonDto {
    const person = new PersonDto();
    person.id = this.guarantorsForm?.value?.person?.id;
    return person;
  }

  private updateGuarantorList() {
    this.httpClient.post<GuarantorDto[]>(Url.GUARANTOR_FIND_BY_CONTRACT, this.contractService.currentId)
      .subscribe(value => this.guarantorDtoList = value);
  }

  private static removeTraceableField(newPerson: PersonDto) {
    newPerson.createdDate = null;
    newPerson.lastModifiedDate = null;
    if (newPerson.contacts) {
      for (let contact of newPerson.contacts) {
        contact.createdDate = null;
        contact.lastModifiedDate = null;
      }
    }
  }

  onSubmit() {
    if (!this.glSelect._value) {
      this.messageService.showGeneralError(this.lang.selectAGuarantor);
      return;
    }
    const newPerson: PersonDto = this.guarantorsForm.value['person'];
    newPerson.contacts = this.contactWrapperList.filter(value => value.active).map(value => value.contact);
    GuarantorsComponent.removeTraceableField(newPerson);
    this.httpClient.post<unknown>(Url.PERSON_UPDATE, newPerson)
      .subscribe(() => {
          this._snackBar.open(this.lang.successSave, 'X', {
            duration: 5000, panelClass: ['bg-success', 'text-white']
          });
          this.updateGuarantorList();
        },
        error => this._snackBar.open(`${this.lang.error} [${error}] `, 'X', {
          duration: 5000, panelClass: ['bg-danger', 'text-white']
        })
      );
  }

  guarantorSelectChange() {

    const guarantorDto: GuarantorDto = this.glSelect.selectedOptions.selected[0]?.value;
    this.guarantorsForm.patchValue(guarantorDto);
    const contactList: ContactDto[] = guarantorDto?.person?.contacts;
    if (contactList) {
      this.contactWrapperList = contactList.map(value => <ContactWrapper>{
        contact: value,
        active: true,
        isNew: false
      });
    }
  }

  contactSelectChange() {
    const contactDto: ContactDto = this.contactSelect.selectedOptions.selected[0]?.value;
    this.contactForm.reset({phoneType: PhoneType.MOBILE});
    this.contactForm.patchValue(contactDto);
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
        description: this.contactForm.value.description,
        person: this.getPerson()
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
          version: value.contact.version,
          number: this.contactForm.value.number,
          confirmed: this.contactForm.value.confirmed,
          phoneType: this.contactForm.value.phoneType,
          description: this.contactForm.value.description,
          person: this.getPerson()
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


