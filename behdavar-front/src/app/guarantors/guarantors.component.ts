import {Component, OnInit} from '@angular/core';
import {PersonLang} from '../model/lang';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MyErrorStateMatcher} from '../model/MyErrorStateMatcher';

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit {

  guarantorsForm:FormGroup;
  lang = new PersonLang();

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  ngOnInit(): void {
  }

  powers = ['Really Smart', 'Super Flexible',
    'Super Hot', 'Weather Changer'];


  submitted = false;

  onSubmit() {
    this.submitted = true;
  }
}

