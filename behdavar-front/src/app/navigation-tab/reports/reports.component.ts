import {Component, OnInit} from '@angular/core';
import {ReportsLang} from "../../model/lang";
import {FormBuilder, FormGroup} from "@angular/forms";

interface TitleValue {
  value: any,
  title: string
}

interface PeriodType extends TitleValue {
}

interface DocumentType extends TitleValue {
}

interface DocumentStatus extends TitleValue {
}
interface GroupByItem extends TitleValue {
}

@Component({
  selector: 'reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  lang = new ReportsLang();
  reportForm: FormGroup;
  periodItems: PeriodType[];
  documentTypeItems: DocumentType[];
  documentStatusItems: DocumentStatus[];
  groupByItems: GroupByItem[];

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initialItems();

    this.reportForm = this.fb.group({
      countOfFollowing: [false],
      amountReceived: [false],
      countOfDocument: [false],
      fromDate: [null],
      toDate: [null],
      groupBy: ['1'],
      period: [1],
      documentType: ['bank'],
      documentStatus: ['available'],


    });
  }

  private initialItems() {
    this.groupByItems = [
      {value: '1', title: this.lang.groupByPeriod},
      {value: '2', title: this.lang.groupByDocumentType},
      {value: '3', title: this.lang.groupByDocumentStatus},
    ];

    this.periodItems = [
      {value: 1, title: this.lang.oneMonth},
      {value: 3, title: this.lang.trimester},
      {value: 4, title: this.lang.sixMonth},
      {value: 365, title: this.lang.yearly},
    ];

    this.documentTypeItems = [{value: 'bank', title: this.lang.bank}, {value: 'machine', title: this.lang.machine}];

    this.documentStatusItems = [
      {value: 'available', title: this.lang.available},
      {value: 'clearing', title: this.lang.clearing},
      {value: 'raw', title: this.lang.raw},
      {value: 'legal', title: this.lang.legal},
      {value: 'parking', title: this.lang.parking},
    ];
  }

  takeReport() {
    console.log(this.reportForm.value);
  }
}
