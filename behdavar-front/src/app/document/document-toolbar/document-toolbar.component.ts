import {Component, Input, OnInit, Output} from '@angular/core';
import {DocumentToolbarLang} from "../../model/lang";

@Component({
  selector: 'app-document-toolbar',
  templateUrl: './document-toolbar.component.html',
  styleUrls: ['./document-toolbar.component.css']
})
export class DocumentToolbarComponent implements OnInit {

  documentToolbarLang: DocumentToolbarLang = new DocumentToolbarLang();

  @Input()
  id: number;

  ngOnInit(): void {
  }

}

export interface SelectedToolbar {
  no: number;
  value: any;
}
