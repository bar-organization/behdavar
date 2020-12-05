import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {AttachmentDto, ContractDto, PaymentDto, PursuitDto} from "../model/model";
import {ActivatedRoute, Router} from "@angular/router";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import {PURSUIT_TYPE_TITLE, PursuitType} from "../model/enum/PursuitType";
import {EnumValueTitle} from "../model/enum/EnumValueTitle";
import {RESULT_TYPE_TITLE, ResultType} from "../model/enum/ResultType";
import * as moment from 'moment';
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {
  SearchCriteria,
  SearchOperation,
  SortDirectionEnum,
  SortOperation
} from "../_custom-component/data-table/PaginationModel";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {BlankToDashPipe} from "../_pip/blank-to-dash.pipe";
import {PursuitTypePip} from "../_pip/PursuitTypePip";
import {MessageService} from "../service/message.service";
import {ContractService} from "../service/contract-service";
import {ResultTypePip} from "../_pip/ResultTypePip";
import {PaymentType} from "../model/enum/PaymentType";
import {ThousandPip} from "../_pip/ThousandPip";

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
  contractDto: ContractDto;

  pursuitTypeList: EnumValueTitle<PursuitType>[] = PURSUIT_TYPE_TITLE;
  resultTypeList: EnumValueTitle<ResultType>[] = RESULT_TYPE_TITLE;

  followingHttpDataSource: HttpDataSource<PursuitDto>;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private fb: FormBuilder, private messageService: MessageService, private pursuitTypePip: PursuitTypePip, private resultTypePip: ResultTypePip, private router: Router, private contractService: ContractService) {
  }

  ngOnInit(): void {
    this.updateContractId();
    this.contractService.getById(this.contractService.currentId, result => this.contractDto = result);

    this.followingForm = this.fb.group({
      description: [''],
      coordinateAppointment: [''],
      depositAppointment: [''],
      submitAccordingFinal: [''],
      nextPursuitDate: [''],
      customerDeposit: [''],
      pursuitType: [PursuitType.PHONE_CALL.toString()],
      resultType: [''],
      depostidAmount: [''],
    });

    const filter: SearchCriteria[] = [{
      key: 'contract.id',
      value: this.contractService.currentId,
      operation: SearchOperation.EQUAL
    }];
    const sort: SortOperation = {sortBy: 'createdDate', direction: SortDirectionEnum.DESC}
    this.followingHttpDataSource = new HttpDataSource<PursuitDto>(Url.PURSUIT_FIND_PAGING, this.httpClient, filter, sort);

  }

  submitted = false;
  followingTableColumn: TableColumn[] = [
    {fieldName: 'pursuitType', title: this.lang.followingType, pipNames: FollowingComponent.getPursuitTypePip()},
    {fieldName: 'resultType', title: this.lang.followingResult, pipNames: FollowingComponent.getResultTypePip()},
    {
      fieldName: 'createdDate',
      colName: 'createdDate',
      sortable: true,
      title: this.lang.date,
      pipNames: [{pip: new JalaliPipe()}, {pip: new BlankToDashPipe()}]
    },
    {
      fieldName: 'createdDate',
      title: this.lang.time,
      pipNames: [{pip: new JalaliPipe(), args: ['HH:mm']}, {pip: new BlankToDashPipe()}]
    },
    {fieldName: 'user.firstName+user.lastName', title: this.lang.expertName},
    {
      fieldName: 'payment.amount',
      colName: 'payment.amount',
      sortable: true,
      title: this.lang.depostidAmount + ' (ریال)',
      pipNames: FollowingComponent.getThousandPip()
    },
    {fieldName: 'description', title: this.lang.description},
  ];

  private static getPursuitTypePip() {
    return [{pip: new PursuitTypePip()}, {pip: new BlankToDashPipe()}];
  }

  private static getResultTypePip() {
    return [{pip: new ResultTypePip()}, {pip: new BlankToDashPipe()}];
  }

  private static getThousandPip() {
    return [{pip: new ThousandPip()}, {pip: new BlankToDashPipe()}];
  }

  onSubmit() {
    this.submitted = true;
    const saveModel: PursuitDto = this.followingForm.value;

    this.completeModel(saveModel);

    this.httpClient.post<PursuitDto>(Url.PURSUIT_SAVE, saveModel)
      .subscribe(() => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.resetForm();
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
    const paymentAmount = this.followingForm?.value?.depostidAmount;

    if (paymentAmount) {
      const payment = new PaymentDto();
      payment.amount = paymentAmount;
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

  private updateContractId() {
    let id = this.route.snapshot.params['id'];
    try {
      this.contractService.updateCurrentId(Number(id));
    } catch (e) {
    }
  }

  onSelectedValueChange(selectedValue: PursuitDto) {
    if (!this.followingForm) {
      return;
    }
    this.followingForm.reset({
      description: selectedValue.description,
      coordinateAppointment: selectedValue.coordinateAppointment,
      depositAppointment: selectedValue.depositAppointment,
      submitAccordingFinal: selectedValue.submitAccordingFinal,
      nextPursuitDate: [''],
      customerDeposit: selectedValue.customerDeposit,
      pursuitType: selectedValue?.pursuitType?.toString(),
      resultType: selectedValue?.resultType?.toString(),
      depostidAmount: [{value: null, disabled: true}]
    });
    if (selectedValue.nextPursuitDate)
      this.followingForm.patchValue({nextPursuitDate: moment(selectedValue.nextPursuitDate).format('yyyy-MM-DD')});

  }

  private resetForm() {
    this.followingForm.reset({
      description: '',
      coordinateAppointment: false,
      depositAppointment: false,
      submitAccordingFinal: false,
      nextPursuitDate: undefined,
      customerDeposit: false,
      pursuitType: PursuitType.PHONE_CALL.toString(),
      resultType: null,
      depostidAmount: 0,
    });
  }
}
