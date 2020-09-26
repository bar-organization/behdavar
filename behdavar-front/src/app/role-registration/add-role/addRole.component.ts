import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {RoleRegistrationLang} from "../../model/lang";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PrivilegeDto, RoleDto} from "../../model/model";
import Url from "../../model/url";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../../service/message.service";
import {PagingRequest, PagingResponse} from "../../_custom-component/data-table/PaginationModel";
import {MatSelectionList} from "@angular/material/list";

@Component({
  selector: 'add-role',
  templateUrl: './addRole.component.html',
  styleUrls: ['./addRole.component.css']
})
export class AddRoleComponent implements OnInit {
  lang = new RoleRegistrationLang();

  @ViewChild("currentPrvSelect")
  currentPrvSelect: MatSelectionList;

  @ViewChild("systemPrvSelect")
  systemPrvSelect: MatSelectionList;

  constructor(private httpClient: HttpClient, private messageService: MessageService, private fb: FormBuilder) {
  }

  @Input()
  roleId: number;
  @Output()
  roleIdChange = new EventEmitter<number>();

  @Output()
  onBack = new EventEmitter<boolean>();


  isInvalidRoleName: boolean;
  isInvalidRoleTitle: boolean;
  roleForm: FormGroup;

  currentPrivilegeList: PrivilegeDto[];
  allSystemPrivilegeList: PrivilegeDto[];


  ngOnInit(): void {
    this.roleForm = this.fb.group({
      roleName: ['', Validators.required],
      roleTitle: ['', Validators.required]
    });

    this.loadAllPrivileges();
  }


  // TODO must refactor

  addPrivilege() {
    if (!this.allSystemPrivilegeList || this.allSystemPrivilegeList.length === 0) {
      return;
    }

    if (!this.systemPrvSelect.selectedOptions.selected || this.systemPrvSelect.selectedOptions.selected.length === 0) {
      this.messageService.showGeneralError(this.lang.selectSystemPrivilege);
      return;
    }
    //
    const selectedSystemPrivilege: PrivilegeDto[] = this.systemPrvSelect.selectedOptions.selected.map(value => value.value);

    // add selectedSystemPrivilege to currentPrivilegeList
    if (!this.currentPrivilegeList) {
      this.currentPrivilegeList = [];
    }
    selectedSystemPrivilege.forEach(value => {
      if (!this.currentPrivilegeList.find(v => v.id === value.id)) {
        this.currentPrivilegeList.push(value);
      }
    });

    this.allSystemPrivilegeList = this.allSystemPrivilegeList.filter(value => !selectedSystemPrivilege.find(v => v.id === value.id));

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
    if (!this.currentPrivilegeList || this.currentPrivilegeList.length === 0) {
      this.messageService.showGeneralError(this.lang.rolePrivilegesNotBeEmpty)
      return;
    }

    roleDto.privilegeDtos = this.currentPrivilegeList;

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
    if (!baseAuditors || baseAuditors.length === 0)
      return;

    for (let baseAuditor of baseAuditors) {
      if (baseAuditor) {
        if (baseAuditor.createdDate)
          baseAuditor.createdDate = null;
        if (baseAuditor.lastModifiedDate)
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
    if (!this.currentPrivilegeList || this.currentPrivilegeList.length === 0) {
      this.messageService.showGeneralError(this.lang.rolePrivilegesNotBeEmpty)
      return;
    }

    //set active item in privilegeList to newRole
    roleDto.privilegeDtos = this.currentPrivilegeList;

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


  private extractDataFromForm(roleDto: RoleDto) {
    roleDto.roleName = this.roleForm.value.roleName;
    roleDto.title = this.roleForm.value.roleTitle;
    roleDto.createdDate = null;
    roleDto.lastModifiedDate = null;
  }

  back() {
    this.onBack.emit(true);
  }

  subtractPrivilege() {
    if (!this.currentPrivilegeList || this.currentPrivilegeList.length === 0) {
      return;
    }
    if (!this.currentPrvSelect.selectedOptions.selected || this.currentPrvSelect.selectedOptions.selected.length === 0) {
      this.messageService.showGeneralError(this.lang.selectCurrentPrivilege);
      return;
    }

    //
    const selectedCurrentPrivilege: PrivilegeDto[] = this.currentPrvSelect.selectedOptions.selected.map(value => value.value);

    // add selectedCurrentPrivilege to systemPrivilegeList
    if (!this.allSystemPrivilegeList) {
      this.allSystemPrivilegeList = [];
    }
    selectedCurrentPrivilege.forEach(value => {
      if (!this.allSystemPrivilegeList.find(v => v.id === value.id)) {
        this.allSystemPrivilegeList.push(value);
      }
    });

    this.currentPrivilegeList = this.currentPrivilegeList.filter(value => !selectedCurrentPrivilege.find(v => v.id === value.id));


  }

  private loadAllPrivileges() {
    const request: PagingRequest = new PagingRequest();
    request.start = 0;
    // TODO must add feature to load all page
    request.max = 999999999;


    this.httpClient.post<PagingResponse<PrivilegeDto>>(Url.PRIVILEGE_FIND_PAGING, request)
      .subscribe(allPrivilegeRes => {
        if (this.roleId) {
          this.httpClient.post<RoleDto>(Url.ROLE_FIND_BY_ID, this.roleId)
            .subscribe(value => {
              this.roleForm.patchValue({'roleName': value.roleName, 'roleTitle': value.title});
              if (value.privilegeDtos) {
                this.currentPrivilegeList = value.privilegeDtos;
                this.subtractCurrentPrivilegeIfExist(allPrivilegeRes.data);
              }
            });
        } else {
          this.allSystemPrivilegeList = allPrivilegeRes.data;
        }
      })
  }

  private subtractCurrentPrivilegeIfExist(loadedPrivilege: PrivilegeDto[]) {
    if (this.currentPrivilegeList && this.currentPrivilegeList.length > 0) {
      if (loadedPrivilege) {
        this.allSystemPrivilegeList = [];
        loadedPrivilege.forEach((value, index) => {
          if (!this.currentPrivilegeList.find(v => v.id === value.id)) {
            this.allSystemPrivilegeList.push(value);
          }
        });
      }
    }
  }
}
