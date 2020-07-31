import {Component, OnInit} from '@angular/core';
import {DocumentToolbarLang} from "../../model/lang";

@Component({
  selector: 'app-document-toolbar',
  templateUrl: './document-toolbar.component.html',
  styleUrls: ['./document-toolbar.component.css']
})
export class DocumentToolbarComponent implements OnInit {

  documentToolbarLang: DocumentToolbarLang = new DocumentToolbarLang();

  constructor() {
  }

  ngOnInit(): void {
  }

}
