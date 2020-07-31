import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {DocumentSearchFormLang} from "../../../model/lang";

@Component({
  selector: 'app-document-search-form',
  templateUrl: './document-search-form.component.html',
  styleUrls: ['./document-search-form.component.css']
})
export class DocumentSearchFormComponent implements OnInit {

  @Input()
  documentSearchForm: FormGroup;

  documentSearchFormLang: DocumentSearchFormLang = new DocumentSearchFormLang();

  constructor() {
  }

  ngOnInit(): void {
  }

}
