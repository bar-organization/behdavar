import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DocumentSearchLang} from '../../model/lang';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {TableColumn} from "../../_custom-component/data-table/data-table.component";
import {CartableDto} from "../../model/model";
import HttpDataSource from "../../_custom-component/data-table/HttpDataSource";
import Url from "../../model/url";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-document-search',
  templateUrl: './document-search.component.html',
  styleUrls: ['./document-search.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class DocumentSearchComponent implements OnInit {
  panelOpenState = false;

  parentForm: FormGroup;
  bankMachineSearchFormGroup: FormGroup;
  customerSearchFormGroup: FormGroup;
  documentSearchFormGroup: FormGroup;

  documentSearchLang: DocumentSearchLang = new DocumentSearchLang();

  constructor(private fb: FormBuilder, private httpClient: HttpClient) {
    this.bankMachineSearchFormGroup = fb.group({
      bank: [''],
      branch: [''],
      facilityReceivingDate: [''],
      plateNumber: [''],
      vehicleType: [''],
    });

    this.customerSearchFormGroup = this.fb.group({
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

    this.documentSearchFormGroup = fb.group({facilityNumber: [''], status: [''], registrationDate: ['']});

    this.parentForm = fb.group({
      bankMachineSearchFormGroup: this.bankMachineSearchFormGroup,
      documentSearchFormGroup: this.documentSearchFormGroup,
      customerSearchFormGroup: this.customerSearchFormGroup,
    });

  }

  ngOnInit(): void {
  }

  onSub() {
    console.log(this.parentForm);
  }

  // TODO httpClint not be required for constructor
  catalogHttpDataSource: HttpDataSource<CartableDto> = new HttpDataSource<CartableDto>(Url.CARTABLE_FIND_PAGING, this.httpClient);

  tableColumns: TableColumn[] = [
    {fieldName: "id", title: 'شناسه'},
    {fieldName: "receiver.firstName", title: 'نام مشتری'},
    {fieldName: 'englishTitle', title: 'عنوان انگلیسی'},
    {fieldName: 'title', title: 'عنوان'},
    {fieldName: 'active', title: 'فعال'},
  ]
}
