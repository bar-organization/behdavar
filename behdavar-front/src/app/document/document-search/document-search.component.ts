import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DocumentLang} from '../../model/lang';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {DataTableComponent} from "../../_custom-component/data-table/data-table.component";
import {SearchCriteria, SearchOperation} from "../../_custom-component/data-table/PaginationModel";
import {EnumValueTitle} from "../../model/enum/EnumValueTitle";
import {CONTRACT_STATUS_TITLE, ContractStatus} from "../../model/enum/ContractStatus";
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

    parentForm: FormGroup;
    bankMachineSearchFormGroup: FormGroup;
    customerSearchFormGroup: FormGroup;
    documentSearchFormGroup: FormGroup;

    documentSearchLang: DocumentLang = new DocumentLang();

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
            fatherName: [''],
            birthDate: [''],
            nationalNumber: [''],
            postalCode: [''],
            mobile: [''],
            telephone: [''],
            birthPlace: [''],
            workPlacePhone: [''],
        });

        this.documentSearchFormGroup = fb.group({facilityNumber: [''], status: [null], registrationDate: [null]});

        this.parentForm = fb.group({
            bankMachineSearchFormGroup: this.bankMachineSearchFormGroup,
            documentSearchFormGroup: this.documentSearchFormGroup,
            customerSearchFormGroup: this.customerSearchFormGroup,
        });

    }

    ngOnInit(): void {
    }

    onSearch() {
        console.log(this.parentForm.value);

        const filter: SearchCriteria[] = [];

        const facilityNumber = this.parentForm.value?.documentSearchFormGroup?.facilityNumber;
        const registrationDate = this.parentForm.value?.documentSearchFormGroup?.registrationDate;
        const status: ContractStatus = this.parentForm.value?.documentSearchFormGroup?.status;

        if (facilityNumber)
            filter.push({key: 'contract.contractNumber', value: facilityNumber, operation: SearchOperation.EQUAL})

        if (status) {
            filter.push({
                key: 'contract.contractStatus',
                value: new ContractStatusPip().transform(status, 'n'),
                operation: SearchOperation.EQUAL
            })
        }


        //if (registrationDate)
        //  filter.push({key: 'contract.submitDate', value: moment(registrationDate).format('yyyy-MM-DD'), operation: SearchOperation.EQUAL});

        this.documentSearchDataTable.httpDataSource.reload(filter);
    }


    onResetForm() {
        this.parentForm.reset();
        this.onSearch();
    }
}


