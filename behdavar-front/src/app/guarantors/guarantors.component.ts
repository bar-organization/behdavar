import {Component, OnInit, ViewChild} from '@angular/core';
import {GuarantorsLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {ActivatedRoute, Router} from "@angular/router";
import {ContactDto, GuarantorDto, PersonDto} from "../model/model";
import {MatSelectionList} from "@angular/material/list";
import {MyErrorStateMatcher} from "../model/MyErrorStateMatcher";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit {

  @ViewChild("GLSelect")
  glSelect: MatSelectionList;

  contactList: ContactDto[];

  lang = new GuarantorsLang();
  guarantorsForm: FormGroup;

  matcher = new MyErrorStateMatcher();

  guarantorDtoList: GuarantorDto[];


  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute, private _snackBar: MatSnackBar,private router:Router) {
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

    this.updateGuarantorList();

  }

  private updateGuarantorList() {
    this.httpClient.post<GuarantorDto[]>(Url.GUARANTOR_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => this.guarantorDtoList = value);
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

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
  onSubmit() {
    const newPerson: PersonDto = this.guarantorsForm.value['person'];
    newPerson.contacts = this.contactList;
    this.removeTraceableField(newPerson);
    this.httpClient.post<unknown>(Url.PERSON_UPDATE, newPerson)
      .subscribe(value => {
          this._snackBar.open(this.lang.successSave, 'X', {
            duration: 5000, panelClass: ['bg-success', 'text-white']
          });
          this.updateGuarantorList();
          this.router.navigate(['../']);
        },
        error => this._snackBar.open(`${this.lang.error} [${error}] `, 'X', {
          duration: 5000, panelClass: ['bg-danger', 'text-white']
        })
      );
  }

  guarantorSelectChange() {

    const guarantorDto: GuarantorDto = this.glSelect.selectedOptions.selected[0]?.value;
    this.guarantorsForm.patchValue(guarantorDto);
    this.contactList = guarantorDto?.person?.contacts;
  }
}

