import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {RoleRegistrationLang} from "../../model/lang";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PrivilegeDto, RoleDto} from "../../model/model";
import Url from "../../model/url";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../../service/message.service";
import {PagingRequest, PagingResponse} from "../../_custom-component/data-table/PaginationModel";
import {MatSelectionList, MatSelectionListChange} from "@angular/material/list";

@Component({
  selector: 'add-role',
  templateUrl: './addRole.component.html',
  styleUrls: ['./addRole.component.css']
})
export class AddRoleComponent implements OnInit,AfterViewInit {
  lang = new RoleRegistrationLang();

  @ViewChild("currentPrvSelect")
  currentPrvSelect: MatSelectionList;

  constructor(private httpClient: HttpClient, private messageService: MessageService, private fb: FormBuilder) {
  }

  @Input()
  role: RoleDto;

  @Output()
  roleChange = new EventEmitter<RoleDto>();

  @Output()
  onBack = new EventEmitter<boolean>();


  isInvalidRoleName: boolean;
  isInvalidRoleTitle: boolean;
  roleForm: FormGroup;

  privilegeWrapperList: PrivilegeDtoWrapper[];
  isIndeterminate: boolean = false;
  selectAll: boolean = false;


  ngOnInit(): void {
    this.roleForm = this.fb.group({
      roleName: ['', Validators.required],
      roleTitle: ['', Validators.required]
    });

  }
  ngAfterViewInit(): void {
    this.loadAllPrivileges();
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

  saveOrUpdateRole() {

    const roleDto: RoleDto = new RoleDto();
    if (this.isEditMode()) {
      roleDto.id = this.role.id;
    }

    if (!this.isFormValid()) {
      return;
    }

    this.extractDataFromForm(roleDto);

    //check privilegeList
    if (!this.getSelectedPrivilegeId() || this.getSelectedPrivilegeId().length === 0) {
      this.messageService.showGeneralError(this.lang.selectAPrivilege)
      return;
    }

    roleDto.privilegeDtos = this.getSelectedPrivilegeId().map(prvId => this.selectedPrivilegeIdToPrivilege(prvId));

    // perform save request
    this.httpClient
      .post<number>(this.isEditMode() ? Url.ROLE_UPDATE : Url.ROLE_SAVE, roleDto)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.back();
        });

  }

  private selectedPrivilegeIdToPrivilege(prvId: string): PrivilegeDto {
    const privilegeDto = new PrivilegeDto();
    privilegeDto.id = Number(prvId);
    return privilegeDto;
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


  private loadAllPrivileges() {
    const request: PagingRequest = new PagingRequest();
    request.start = 0;
    // TODO must add feature to load all page
    request.max = 999999999;


    this.httpClient.post<PagingResponse<PrivilegeDto>>(Url.PRIVILEGE_FIND_PAGING, request)
      .subscribe(allPrivilegeRes => {
        this.privilegeWrapperList = allPrivilegeRes.data.map(prv => {
          return {privilege: prv, selected: false};
        });
        this.checkSelectedPrivileges();
      })
  }

  private checkSelectedPrivileges() {
    if (this.isEditMode()) {
      this.httpClient.post<RoleDto>(Url.ROLE_FIND_BY_ID, this.role.id)
        .subscribe(selectedRole => {
          this.roleForm.patchValue({'roleName': selectedRole.roleName, 'roleTitle': selectedRole.title});
          this.privilegeWrapperList.forEach(prvWrapper => {
            for (let privilegeDto of selectedRole.privilegeDtos) {
              if (privilegeDto && privilegeDto.id == prvWrapper.privilege.id) {
                prvWrapper.selected = true;
              }
            }
          });

          // TODO must refactor
          this.isIndeterminate = false;
          const selectedLength = this.privilegeWrapperList.filter(prv => prv.selected).length;
          const totalLength = this.privilegeWrapperList.length;
          if (totalLength === selectedLength) {
            this.selectAll = true;
            return;
          }
          if (0 < selectedLength && selectedLength < totalLength) {
            this.isIndeterminate = true;
          }

        });
    }
  }

  private isEditMode() {
    return this.role && this.role.id;
  }


  onPrivilegeSelectChange() {
    this.isIndeterminate = false;
    if (this.getSelectedPrivilegeId().length === 0) {
      this.selectAll = false;
      return;
    }

    if (this.getSelectedPrivilegeId().length === this.privilegeWrapperList.length) {
      this.selectAll = true;
      return;
    }

    this.isIndeterminate = true;

  }

  private getSelectedPrivilegeId() {
    return this.currentPrvSelect._value ;
  }

  selectAllChange(event:any) {
    if (event.checked) {
      this.currentPrvSelect.selectAll();
    } else {
      this.currentPrvSelect.selectedOptions.clear();
    }
  }
}

interface PrivilegeDtoWrapper {
  privilege: PrivilegeDto;
  selected: boolean;
}
