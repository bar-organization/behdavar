import {Component, OnInit} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {ContractDto, PursuitDto} from "../model/model";
import {ActivatedRoute, Router} from "@angular/router";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {PURSUIT_TYPE_TITLE, PursuitType} from "../model/enum/PursuitType";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {RESULT_TYPE_TITLE, ResultType} from "../model/enum/ResultType";
import * as moment from 'moment';
import {MatSnackBar} from "@angular/material/snack-bar";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {PursuitTypePip} from "../_pip/PursuitTypePip";
import {MatCheckboxChange} from "@angular/material/checkbox";

interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {


  lang = new FollowingLang();
  followingForm: FormGroup;
  depostidAmountDisable: boolean;
  pursuit: PursuitDto;
  pursuitTypeList: EnumValueTitle<PursuitType>[] = PURSUIT_TYPE_TITLE;
  resultTypeList: EnumValueTitle<ResultType>[] = RESULT_TYPE_TITLE;
  followingHttpDataSource: HttpDataSource<PursuitDto>;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private fb: FormBuilder, private _snackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit(): void {
    this.followingForm = this.fb.group({
      description: [''],
      coordinateAppointment: [''],
      depositAppointment: [''],
      submitAccordingFinal: [''],
      nextPursuitDate: [''],
      customerDeposit: [''],
      pursuitType: [''],
      resultType: [''],
      depostidAmount: [{value: null, disabled: true}],
    });

    const filter: SearchCriteria[] = [{key: 'contract.id', value: this.getIdParam(), operation: SearchOperation.EQUAL}];
    this.followingHttpDataSource = new HttpDataSource<PursuitDto>(Url.PURSUIT_FIND_PAGING, this.httpClient, filter);

  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }
  }

  submitted = false;
  followingTableColumn: TableColumn[] = [
    {fieldName: 'pursuitType', title: this.lang.followingType, pipNames: this.getPursuitTypePip()},
    {
      fieldName: 'createdDate',
      title: this.lang.date,
      pipNames: [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}]
    },
    {
      fieldName: 'createdDate',
      title: this.lang.time,
      pipNames: [{pip: new JalaliPipe(), args: ['HH:mm']}, {pip: new BlankToDashPipe()}]
    },
    {fieldName: 'user.firstName+user.lastName', title: this.lang.expertName},
  ];

  private getPursuitTypePip() {
    return [{pip: new PursuitTypePip()}, {pip: new BlankToDashPipe()}];
  }

  onSubmit() {
    this.submitted = true;
    const saveModel: PursuitDto = this.followingForm.value;

    this.completeModel(saveModel);

    this.httpClient.post<PursuitDto>(Url.PURSUIT_SAVE, saveModel)
      .subscribe(value => {
          this._snackBar.open(this.lang.successSave, 'X', {
            duration: 5000, panelClass: ['bg-success', 'text-white']
          });
          this.followingHttpDataSource.reload();
          this.router.navigate(['../']);
        },


        error => this._snackBar.open(`${this.lang.error} [${error}] `, 'X', {
          duration: 5000, panelClass: ['bg-danger', 'text-white']
        })
      );
  }

  completeModel(model: PursuitDto) {

    const contractDto: ContractDto = new ContractDto();
    contractDto.id = this.getIdParam();
    model.contract = contractDto;

    if (model.nextPursuitDate)
      model.nextPursuitDate = moment(model.nextPursuitDate).format('yyyy-MM-DD');
    else
      model.nextPursuitDate = null;

    if (!model.pursuitType) {
      model.pursuitType = null;
    }

    if (!model.resultType) {
      model.resultType = null
    }

  }

  onCustomerDepositChange(event: MatCheckboxChange) {
    if (event.checked) {
      this.followingForm.controls['depostidAmount'].enable();
    } else {
      this.followingForm.patchValue({depostidAmount: null});
      this.followingForm.controls['depostidAmount'].disable();
    }
  }
}
