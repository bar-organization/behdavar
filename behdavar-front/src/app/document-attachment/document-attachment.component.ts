import { Component, OnInit } from '@angular/core';
import {AttachmentLang, GuarantorsLang} from "../model/lang";
import {FormGroup} from "@angular/forms";
import {MyErrorStateMatcher} from "../model/MyErrorStateMatcher";

@Component({
  selector: 'app-document-attachment',
  templateUrl: './document-attachment.component.html',
  styleUrls: ['./document-attachment.component.css']
})
export class DocumentAttachmentComponent implements OnInit {

  lang = new AttachmentLang();
  attachmentForm: FormGroup;
  attachmentFormControls: any;
  matcher = new MyErrorStateMatcher();

  constructor() { }

  ngOnInit(): void {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }

}
