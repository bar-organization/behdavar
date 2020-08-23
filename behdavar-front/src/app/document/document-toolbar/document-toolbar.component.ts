import {Component, Input, OnInit, Output, ViewChild} from '@angular/core';
import {DocumentToolbarLang} from "../../model/lang";
import {DocumentToolbarService} from "../../service/document-toolbar.service";

@Component({
  selector: 'app-document-toolbar',
  templateUrl: './document-toolbar.component.html',
  styleUrls: ['./document-toolbar.component.css']
})
export class DocumentToolbarComponent implements OnInit {

  documentToolbarLang: DocumentToolbarLang = new DocumentToolbarLang();

  @Input()
  data: any;

  constructor(private s: DocumentToolbarService) {
  }

  ngOnInit(): void {
  }

  onClick(number: number) {
    this.s.onClickHappen.emit({no: number, value: this.data});

  }
}

export interface SelectedToolbar {
  no: number;
  value: any;
}
