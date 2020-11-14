import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {ChangeExpertLang} from "../model/lang";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {AssignContractDto, CartableDto, ContractDto, UserDto} from "../model/model";
import Url from "../model/url";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";
import {MessageService} from "../service/message.service";
import {MatAutocomplete} from "@angular/material/autocomplete";
import {SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {ContractStatus} from "../model/enum/ContractStatus";
import {ContractService} from "../service/contract-service";

@Component({
  selector: 'change-expert',
  templateUrl: './change-expert.component.html'
})
export class ChangeExpertComponent implements OnInit, AfterViewInit {

  lang = new ChangeExpertLang();
  documentNumber: string = '------';
  changeExpertFormGroup: FormGroup;

  @ViewChild("newExpertAuto")
  newExpertAutocomplete: MatAutocomplete;
  userOptions: Subject<UserDto[]> = new Subject<UserDto[]>();
  userDto: UserDto;
  newExpertAutocompleteControl = new FormControl();


  constructor(private route: ActivatedRoute, private fb: FormBuilder, private httpClient: HttpClient, private messageService: MessageService, private router: Router, private contractService: ContractService) {

  }

  catalogHttpDataSource: HttpDataSource<CartableDto>;

  tableColumns: TableColumn[] = [
    {fieldName: 'receiver.firstName', title: this.lang.firstName, pipNames: ChangeExpertComponent.getSimplePip()},
    {fieldName: 'receiver.lastName', title: this.lang.lastName, pipNames: ChangeExpertComponent.getSimplePip()},
    {fieldName: 'createdDate', title: this.lang.date, pipNames: ChangeExpertComponent.getDatePip()},
  ];


  ngOnInit(): void {
    this.changeExpertFormGroup = this.fb.group({
      newExpert: [''],
    });
    const filterByContractId = [{
      key: 'contract.id',
      value: this.contractService.currentId,
      operation: SearchOperation.EQUAL
    }];
    this.catalogHttpDataSource = new HttpDataSource<CartableDto>(Url.CARTABLE_FIND_PAGING_ALL, this.httpClient, filterByContractId);
    this.newExpertAutocompleteControl.valueChanges.subscribe(value => this.newExpertAutocompleteFilter(value));
  }

  ngAfterViewInit(): void {
    this.findContractNumber();

    this.newExpertAutocomplete.optionSelected.subscribe(v => {
      if (v && v.option) {
        this.userDto = new UserDto();
        this.userDto.id = v.option.id;
      }
    });
  }


  private findContractNumber() {
    this.httpClient.post<ContractDto>(Url.CONTRACT_FIND_BY_ID, this.contractService.currentId)
      .subscribe(value => this.documentNumber = value.contractNumber);
  }

  changeExpert() {
    if (!this.userDto || !this.userDto.id) {
      this.messageService.showGeneralError(this.lang.newExpertRequired);
      return;
    }

    const changeExpert: AssignContractDto = {
      assigneeId: this.userDto.id,
      contractId: this.contractService.currentId,
      status: ContractStatus.AVAILABLE
    };

    this.httpClient.post(Url.CARTABLE_ASSIGN, changeExpert)
      .subscribe(() => {
        this.messageService.showGeneralSuccess(this.lang.successSave);
        this.router.navigate(['.'], {relativeTo: this.route.parent});
      }, () => this.messageService.showGeneralError(this.lang.error));

  }

  private newExpertAutocompleteFilter(filterValue: string) {
    if (!filterValue) {
      return;
    }
    if (filterValue.length < 3) {
      return;
    }
    this.httpClient.post<UserDto[]>(Url.USER_FIND_SUGGESTION, filterValue).subscribe(user => {
      this.userOptions.next(user);
    }, e => this.messageService.showGeneralError(this.lang.errorOnSearchExpert, e));
  }


  private static getDatePip() {
    return [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}];
  }

  private static getSimplePip() {
    return [{pip: new BlankToDashPipe()}];

  }
}
