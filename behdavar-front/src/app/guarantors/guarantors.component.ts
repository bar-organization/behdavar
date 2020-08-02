import {Component, OnInit} from '@angular/core';
import {GuarantorsLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit {

  lang = new GuarantorsLang();
  guarantorsForm: FormGroup;
  gurFormControls: any;
  matcher = new MyErrorStateMatcher();

  constructor(public fb: FormBuilder) {
  }


  ngOnInit(): void {
    this.guarantorsForm = this.fb.group({
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
    this.gurFormControls = this.guarantorsForm.controls;
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }
}

