import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {RoleRegistrationLang} from "../../model/lang";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BaseAuditorDto, PrivilegeDto, RoleDto} from "../../model/model";
import Url from "../../model/url";
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";
import {MessageService} from "../../service/message.service";
import {MatAutocomplete} from "@angular/material/autocomplete";

@Component({
  selector: 'add-role',
  templateUrl: './addRole.component.html',
  styleUrls: ['./addRole.component.css']
})
export class AddRoleComponent implements OnInit {
  lang = new RoleRegistrationLang();

  constructor(private httpClient: HttpClient, private messageService: MessageService, private fb: FormBuilder) {
  }

  @Input()
  roleId: number;
  @Output()
  roleIdChange = new EventEmitter<number>();

  @Output()
  onBack = new EventEmitter<boolean>();

  @ViewChild("auto")
  privilegeAutocomplete: MatAutocomplete;

  isInvalidRoleName: boolean;
  isInvalidRoleTitle: boolean;
  roleForm: FormGroup;

  privilegeList: PrivilegeWrapper[] = [];

  privilegeControl: FormControl = new FormControl();
  privilegeOptions: Subject<PrivilegeDto[]> = new Subject<PrivilegeDto[]>();

  ngOnInit(): void {
    this.roleForm = this.fb.group({
      roleName: ['', Validators.required],
      roleTitle: ['', Validators.required]
    });
    this.privilegeControl.valueChanges.subscribe(value => this._filter(value));

    this.loadById();
  }

  private _filter(filterValue: string) {
    if (filterValue.length < 3) {
      return;
    }

    this.httpClient.post<PrivilegeDto[]>(Url.PRIVILEGE_FIND_SUGGESTION, filterValue).subscribe(role => {
      this.privilegeOptions.next(role);
    }, e => this.messageService.showGeneralError(this.lang.errorOnFindPrivilege, e));
  }

  // TODO must refactor

  addPrivilege() {
    // if no item selected in autocompleter
    if (!this.privilegeAutocomplete || !this.privilegeAutocomplete.options || !this.privilegeAutocomplete.options.first)
      return;

    // extract id , and title
    const selectedPrivilegeId = this.privilegeAutocomplete?.options?.first.id;
    const selectedPrivilegeTitle = this.privilegeAutocomplete?.options?.first.value;

    // make roleDto model from id and title
    const privilege = new PrivilegeDto();
    privilege.id = Number(selectedPrivilegeId);
    privilege.title = selectedPrivilegeTitle;

    // find is selected privilege exist in privilegeList or not ?
    let isDuplicate = false;
    for (let privilegeWrapper of this.privilegeList) {
      if (privilegeWrapper.privilege.id === privilege.id) {
        isDuplicate = true;
        break;
      }
    }

    // if privilege not exist in privilegeList then added to list and new Privilege
    if (!isDuplicate)
      this.privilegeList.push({privilege: privilege, active: true, isNew: true});

    // clear autocomplete
    this.privilegeControl.setValue('');
    this.privilegeOptions.next([]);

  }

  onUserRoleDelete(prv: PrivilegeWrapper) {
    if (prv.isNew) {
      this.privilegeList = this.privilegeList.filter(value => value !== prv);
    } else {
      prv.active = !prv.active;

    }
  }

  private isFormValid(): boolean {
    this.isInvalidRoleName = false;
    this.isInvalidRoleTitle = false;

    if (!this.roleForm.valid) {
      this.isInvalidRoleName = true;
      this.isInvalidRoleTitle = true;
      this.roleForm.markAllAsTouched();
      return false;
    }
    return true;
  }

  newRole() {

    const roleDto: RoleDto = new RoleDto();

    if (!this.isFormValid()) {
      return;
    }

    this.extractDataFromForm(roleDto);

    //check privilegeList
    if (!this.isValidPrivilegeList()) {
      this.messageService.showGeneralError(this.lang.rolePrivilegesNotBeEmpty)
      return;
    }

    //set active item in privilegeList to newRole
    roleDto.privilegeDtos = this.privilegeList.filter(value => value.active).map(value => value.privilege);

    // clear privilege audit field
    this.clearAuditField(roleDto.privilegeDtos);

    // perform save request
    this.httpClient
      .post<number>(Url.ROLE_SAVE, roleDto)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.back();
        },
        e => this.messageService.showGeneralError(e, this.lang.error));

  }

  private clearAuditField(baseAuditors: any[]) {
    if(!baseAuditors || baseAuditors.length === 0)
      return;

    for (let baseAuditor of baseAuditors) {
      if(baseAuditor){
        if(baseAuditor.createdDate)
      baseAuditor.createdDate = null;
        if(baseAuditor.lastModifiedDate)
        baseAuditor.lastModifiedDate = null;

      }
    }
  }

  editRole() {

    const roleDto: RoleDto = new RoleDto();
    roleDto.id = this.roleId;

    if (!this.isFormValid()) {
      return;
    }

    this.extractDataFromForm(roleDto);

    //check privilegeList
    if (!this.isValidPrivilegeList()) {
      this.messageService.showGeneralError(this.lang.rolePrivilegesNotBeEmpty)
      return;
    }

    //set active item in privilegeList to newRole
    roleDto.privilegeDtos = this.privilegeList.filter(value => value.active).map(value => value.privilege);

    // clear privilege audit field
    this.clearAuditField(roleDto.privilegeDtos);

    // perform update request
    this.httpClient
      .post<number>(Url.ROLE_UPDATE, roleDto)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.back();
        },
        e => this.messageService.showGeneralError(e, this.lang.error));

  }

  private isValidPrivilegeList() {
    return this.privilegeList && this.privilegeList.filter(value => value.active).length > 0;
  }

  private extractDataFromForm(roleDto: RoleDto) {
    roleDto.roleName = this.roleForm.value.roleName;
    roleDto.title = this.roleForm.value.roleTitle;
    roleDto.createdDate = null;
    roleDto.lastModifiedDate = null;
  }

  private loadById() {
    if (this.roleId)
      this.httpClient.post<RoleDto>(Url.ROLE_FIND_BY_ID, this.roleId)
        .subscribe(value => {
          this.roleForm.patchValue({'roleName': value.roleName, 'roleTitle': value.title});
          if (value.privilegeDtos) {
            this.privilegeList = value.privilegeDtos.map(prv => {
              const prvWrapper: PrivilegeWrapper = {privilege: prv, active: true, isNew: false}
              return prvWrapper;
            });
          }
        })
  }

  back() {
    this.onBack.emit(true);
  }
}

interface PrivilegeWrapper {
  privilege: PrivilegeDto;
  active: boolean;
  isNew?: boolean;
}
