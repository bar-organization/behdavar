import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DocumentSearchLang} from '../../model/lang';
import {animate, state, style, transition, trigger} from "@angular/animations";

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
  parentForm: FormGroup;
  bankMachineSearchFormGroup: FormGroup;
  customerSearchFormGroup: FormGroup;
  documentSearchFormGroup: FormGroup;

  documentSearchLang: DocumentSearchLang = new DocumentSearchLang();

  myColumns: string[] = ['id', 'name', 'desc'];
  expendedDocument: DocumentModel | null;

  documentList: DocumentModel[] = [
    {id: 1, name: 'doc1', desc: 'THis is test doc'},
    {id: 1, name: 'doc1', desc: 'THis is test doc'},
    {id: 1, name: 'doc1', desc: 'THis is test doc'}]

  constructor(private fb: FormBuilder) {
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

}

export interface DocumentModel {
  id: number;
  name: string;
  desc: string;
}
