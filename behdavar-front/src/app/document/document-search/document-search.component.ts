import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DocumentSearchLang} from '../../model/lang';
import {group} from "@angular/animations";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'app-document-search',
  templateUrl: './document-search.component.html',
  styleUrls: ['./document-search.component.css']
})
export class DocumentSearchComponent implements OnInit {
  documentSearchFormParent: FormGroup;
  bankMachineSearchFormGroup: FormGroup;
  customerSearchFormGroup: FormGroup;
  documentSearchFormGroup: FormGroup;

  documentSearchLang: DocumentSearchLang = new DocumentSearchLang();
  showToolbar = false;

  // TODO temporary must remove
  displayedColumns: string[] = ['select', 'position', 'name', 'weight', 'symbol'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  selection = new SelectionModel<PeriodicElement>(true, []);


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

    this.documentSearchFormParent = fb.group({
      bankMachineSearchFormGroup: this.bankMachineSearchFormGroup,
      documentSearchFormGroup: this.documentSearchFormGroup,
      customerSearchFormGroup: this.customerSearchFormGroup,
    });

  }

  ngOnInit(): void {
  }

  onSub() {
    console.log(this.documentSearchFormParent);
  }

  onChecked(event: any) {
    console.log(event);
    this.showToolbar = event.checked;

  }
  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: PeriodicElement): string {
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }
}

// TODO this is temporary must remove
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];
