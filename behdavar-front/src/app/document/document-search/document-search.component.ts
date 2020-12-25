import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DocumentLang} from '../../model/lang';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {DataTableComponent} from "../../_custom-component/data-table/data-table.component";
import {SearchCriteria, SearchOperation} from "../../_custom-component/data-table/PaginationModel";
import {EnumValueTitle} from "../../model/enum/EnumValueTitle";
import {CONTRACT_STATUS_TITLE, ContractStatus} from "../../model/enum/ContractStatus";
import {Router} from "@angular/router";
import {ContractStatusPip} from "../../_pip/ContractStatusPip";

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
  contractStatusList: EnumValueTitle<string>[] = CONTRACT_STATUS_TITLE;

  @Input()
  documentSearchDataTable: DataTableComponent;
  @Input() onFormUpdate: (parentFormValue: any) => void;
  parentForm: FormGroup;
  bankMachineSearchFormGroup: FormGroup;
  customerSearchFormGroup: FormGroup;
  guarantorSearchFormGroup: FormGroup;
  documentSearchFormGroup: FormGroup;

  documentSearchLang: DocumentLang = new DocumentLang();

  constructor(private fb: FormBuilder, private router: Router) {
    this.bankMachineSearchFormGroup = fb.group({
      bank: [''],
      branch: [''],
      facilityReceivingDate: [''],
      plateNumber: [''],
      vehicleType: [''],
    });

    this.customerSearchFormGroup = this.fb.group({
      name: [''],
      fatherName: [''],
      birthDate: [''],
      nationalNumber: [''],
      postalCode: [''],
      mobile: [''],
      telephone: [''],
      birthPlace: [''],
      workPlacePhone: [''],
    });

    this.guarantorSearchFormGroup = this.fb.group({
      name: [''],
      fatherName: [''],
      birthDate: [''],
      nationalNumber: [''],
      postalCode: [''],
      mobile: [''],
      telephone: [''],
      birthPlace: [''],
      workPlacePhone: [''],
    });

    this.documentSearchFormGroup = fb.group({facilityNumber: [''], status: [this.isMyBaskUrl() ? 'RAW' : null], registrationDate: [null]});

    this.parentForm = fb.group({
      bankMachineSearchFormGroup: this.bankMachineSearchFormGroup,
      documentSearchFormGroup: this.documentSearchFormGroup,
      customerSearchFormGroup: this.customerSearchFormGroup,
      guarantorSearchFormGroup: this.guarantorSearchFormGroup,
    });

  }

  ngOnInit(): void {
  }

  private isMyBaskUrl(): boolean {
    return !!this.router.url.match('my-basket');
  }

  onSearch() {
    if (this.onFormUpdate) {
      this.onFormUpdate(this?.parentForm?.value);
    }
    const filter: SearchCriteria[] = [];

    const cFullName = this.parentForm.value?.customerSearchFormGroup?.name;
    const gFullName = this.parentForm.value?.guarantorSearchFormGroup?.name;

    const cFatherName = this.parentForm.value?.customerSearchFormGroup?.fatherName;
    const gFatherName = this.parentForm.value?.guarantorSearchFormGroup?.fatherName;

    const cNationalNumber = this.parentForm.value?.customerSearchFormGroup?.nationalNumber;
    const gNationalNumber = this.parentForm.value?.guarantorSearchFormGroup?.nationalNumber;

    const facilityNumber = this.parentForm.value?.documentSearchFormGroup?.facilityNumber;
    //const registrationDate = this.parentForm.value?.documentSearchFormGroup?.registrationDate;
    const status: ContractStatus = this.parentForm.value?.documentSearchFormGroup?.status;
    const plateNumber: string = this.parentForm.value?.bankMachineSearchFormGroup?.plateNumber;

    if (facilityNumber)
      filter.push({key: 'contract.contractNumber', value: facilityNumber, operation: SearchOperation.MATCH})

    DocumentSearchComponent.applyNameFilter(filter, cFullName, SearchType.CUSTOMER);
    DocumentSearchComponent.applyNameFilter(filter, gFullName, SearchType.GUARANTOR);

    DocumentSearchComponent.applyFatherNameFilter(filter, cFatherName, SearchType.CUSTOMER);
    DocumentSearchComponent.applyFatherNameFilter(filter, gFatherName, SearchType.GUARANTOR);

    DocumentSearchComponent.applyNationalNumberFilter(filter, cNationalNumber, SearchType.CUSTOMER);
    DocumentSearchComponent.applyNationalNumberFilter(filter, gNationalNumber, SearchType.GUARANTOR);

    if (status) {
      filter.push({
        key: 'contract.contractStatus',
        value: status,
        operation: SearchOperation.EQUAL
      })
    }
    if (plateNumber) {
      filter.push({
        key: 'contract.product.productPlate',
        value: plateNumber,
        operation: SearchOperation.EQUAL
      })

    }


    //if (registrationDate)
    //  filter.push({key: 'contract.submitDate', value: moment(registrationDate).format('yyyy-MM-DD'), operation: SearchOperation.EQUAL});

    this.documentSearchDataTable.httpDataSource.reload(filter);
  }

  private static applyNameFilter(filter: SearchCriteria[], name: string, type: SearchType) {
    if (!name)
      return;

    const key = type === SearchType.CUSTOMER ? "contract.customers.person.fullName" : "contract.guarantors.person.fullName";
    filter.push({
      key: key,
      value: name,
      operation: SearchOperation.MATCH
    });

  }

  private static applyFatherNameFilter(filter: SearchCriteria[], fatherName: string, type: SearchType) {
    if (!fatherName)
      return;

    const key = type === SearchType.CUSTOMER ? "contract.customers.person.fatherName" : "contract.guarantors.person.fatherName";
    filter.push({
      key: key,
      value: fatherName,
      operation: SearchOperation.MATCH
    });
  }

  private static applyNationalNumberFilter(filter: SearchCriteria[], nationalNumber: string, type: SearchType) {
    if (!nationalNumber)
      return;

    const key = type === SearchType.CUSTOMER ? "contract.customers.person.nationalCode" : "contract.guarantors.person.nationalCode";
    filter.push({
      key: key,
      value: nationalNumber,
      operation: SearchOperation.MATCH
    });
  }

  onResetForm() {
    this.parentForm.reset();
    this.onSearch();
  }

  public getParentFormValue(): any {
    return this?.parentForm?.value;
  }

  public setParentFormValue(formValue: any) {
    if (!this.parentForm)
      return;
    this.parentForm.setValue(formValue);
  }
}

enum SearchType {
  CUSTOMER, GUARANTOR
}


