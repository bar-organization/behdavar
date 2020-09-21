import {Component, OnInit, ViewChild} from '@angular/core';
import {GuarantorsLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {ActivatedRoute} from "@angular/router";
import {GuarantorDto} from "../model/model";
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

  lang = new GuarantorsLang();
  guarantorsForm: FormGroup;

  matcher = new MyErrorStateMatcher();

  guarantorDtoList: GuarantorDto[];


  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute,private _snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.guarantorsForm = this.fb.group({
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

  onSubmit() {
    console.log(this.guarantorsForm.value);

    this.httpClient.post<unknown>(Url.PERSON_UPDATE, this.guarantorsForm.value['person'])
      .subscribe(value => {
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
    this.guarantorsForm.patchValue(this.glSelect.selectedOptions.selected[0]?.value);
  }
}

