import {Component, OnInit} from '@angular/core';
import {PersonLang} from "../model/lang";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-guarantors',
  templateUrl: './guarantors.component.html',
  styleUrls: ['./guarantors.component.css']
})
export class GuarantorsComponent implements OnInit {

  guarantorsForm:FormGroup;
  lang = new PersonLang();

  ngOnInit(): void {
  }

  powers = ['Really Smart', 'Super Flexible',
    'Super Hot', 'Weather Changer'];


  submitted = false;

  onSubmit() {
    this.submitted = true;
  }
}

