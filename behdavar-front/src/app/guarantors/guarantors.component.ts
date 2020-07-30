import {Component, OnInit} from '@angular/core';
import {PersonLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit {

  lang = new PersonLang();
  guarantorsForm: FormGroup;
  gurFormControls: any;

  constructor(public fb: FormBuilder) {
  }

  matcher = new MyErrorStateMatcher();

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

