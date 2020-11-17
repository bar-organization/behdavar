import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {AttachmentDto, ContractDto, PaymentDto, PaymentType, PursuitDto} from "../model/model";
import {ActivatedRoute, Router} from "@angular/router";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {PURSUIT_TYPE_TITLE, PursuitType} from "../model/enum/PursuitType";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {RESULT_TYPE_TITLE, ResultType} from "../model/enum/ResultType";
import * as moment from 'moment';
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {PursuitTypePip} from "../_pip/PursuitTypePip";
import {MatCheckboxChange} from "@angular/material/checkbox";
import {MessageService} from "../service/message.service";
import {ContractService} from "../service/contract-service";
import {ResultTypePip} from "../_pip/ResultTypePip";

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  @ViewChild("fileInput") fileInput: ElementRef;

  lang = new FollowingLang();
  followingForm: FormGroup;
  fileName: string = null;
  fileToUpload: string = null;
  fileUploadDisable = true;

  pursuitTypeList: EnumValueTitle<PursuitType>[] = PURSUIT_TYPE_TITLE;
  resultTypeList: EnumValueTitle<ResultType>[] = RESULT_TYPE_TITLE;

  followingHttpDataSource: HttpDataSource<PursuitDto>;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private fb: FormBuilder, private messageService: MessageService, private pursuitTypePip: PursuitTypePip, private resultTypePip: ResultTypePip, private router: Router, private contractService: ContractService) {
  }

  ngOnInit(): void {
    this.updateContractId();

    this.followingForm = this.fb.group({
      description: [''],
      coordinateAppointment: [''],
      depositAppointment: [''],
      submitAccordingFinal: [''],
      nextPursuitDate: [{value: '', disabled: true}],
      customerDeposit: [''],
      pursuitType: [PursuitType.PHONE_CALL.toString()],
      resultType: [''],
      depostidAmount: [{value: null, disabled: true}],
    });

    const filter: SearchCriteria[] = [{
      key: 'contract.id',
      value: this.contractService.currentId,
      operation: SearchOperation.EQUAL
    }];
    this.followingHttpDataSource = new HttpDataSource<PursuitDto>(Url.PURSUIT_FIND_PAGING, this.httpClient, filter);

  }

  submitted = false;
  followingTableColumn: TableColumn[] = [
    {fieldName: 'pursuitType', title: this.lang.followingType, pipNames: FollowingComponent.getPursuitTypePip()},
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

  private static getPursuitTypePip() {
    return [{pip: new PursuitTypePip()}, {pip: new BlankToDashPipe()}];
  }

  onSubmit() {
    this.submitted = true;
    const saveModel: PursuitDto = this.followingForm.value;

    this.completeModel(saveModel);

    this.httpClient.post<PursuitDto>(Url.PURSUIT_SAVE, saveModel)
      .subscribe(() => {
          this.messageService.showGeneralSuccess(this.lang.successSave);

          // TODO must refactor
          const filter: SearchCriteria[] = [{
            key: 'contract.id',
            value: this.contractService.currentId,
            operation: SearchOperation.EQUAL
          }];
          this.followingHttpDataSource.reload(filter);
        },
        error => this.messageService.showGeneralError(this.lang.error, error)
      );
  }

  handleFileInput(files: FileList) {
    const userFile = files.item(0);
    this.fileName = userFile.name;
    const reader = new FileReader();
    reader.readAsDataURL(userFile);
    reader.onload = () => {
      this.fileToUpload = reader.result as string;
      this.fileToUpload = this.fileToUpload.slice(this.fileToUpload.indexOf('base64') + 7, this.fileToUpload.length);
    };
  }

  completeModel(model: PursuitDto) {

    const contractDto: ContractDto = new ContractDto();
    contractDto.id = this.contractService.currentId;
    model.contract = contractDto;
    model.pursuitType = this.pursuitTypePip.transform(this.followingForm.value.pursuitType, 'n');
    model.resultType = this.resultTypePip.transform(this.followingForm.value.resultType, 'n');

    if (!!this.followingForm.value.customerDeposit) {
      const payment = new PaymentDto();
      payment.amount = this.followingForm.value.depostidAmount;
      payment.paymentType = PaymentType.CASH;
      payment.contract = model.contract;

      if (this.fileName && this.fileToUpload) {
        const attachmentDto: AttachmentDto = new AttachmentDto();
        attachmentDto.fileName = this.fileName;
        attachmentDto.content = this.fileToUpload;
        attachmentDto.contract = model.contract;
        payment.attachment = attachmentDto;

      }

      model.payment = payment;
    }

    if (model.nextPursuitDate)
      model.nextPursuitDate = moment(model.nextPursuitDate).format('yyyy-MM-DD');
    else
      model.nextPursuitDate = null;

    if (model.pursuitType === '') {
      model.pursuitType = null;
    }

    if (model.resultType === '') {
      model.resultType = null
    }

  }

  onCustomerDepositChange(event: MatCheckboxChange) {
    if (event.checked) {
      this.followingForm.controls['depostidAmount'].enable();
      this.followingForm.controls['nextPursuitDate'].enable();
      this.fileUploadDisable = false;
    } else {
      this.followingForm.controls['depostidAmount'].disable();
      this.followingForm.patchValue({depostidAmount: null});

      this.followingForm.controls['nextPursuitDate'].disable();
      this.followingForm.patchValue({nextPursuitDate: ''});

      this.fileUploadDisable = true;
      this.fileName = '';
      this.fileToUpload = null;
      this.fileInput.nativeElement.value = null;
    }
  }

  private updateContractId() {
    let id = this.route.snapshot.params['id'];
    try {
      this.contractService.updateCurrentId(Number(id));
    } catch (e) {
    }
  }
}
