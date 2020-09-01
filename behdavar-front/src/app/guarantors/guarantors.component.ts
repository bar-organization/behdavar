import {Component, OnInit, ViewChild} from '@angular/core';
import {GuarantorsLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {ActivatedRoute} from "@angular/router";
import {GuarantorDto} from "../model/model";
import {MatSelectionList} from "@angular/material/list";
import {MyErrorStateMatcher} from "../model/MyErrorStateMatcher";

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


  constructor(public fb: FormBuilder, private httpClient: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.guarantorsForm = this.fb.group({
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

  }

  guarantorSelectChange() {
    this.guarantorsForm.patchValue(this.glSelect.selectedOptions.selected[0]?.value);
  }
}

