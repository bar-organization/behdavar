import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {RoleRegistrationLang} from "../model/lang";
import {FormBuilder, FormGroup} from "@angular/forms";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";
import {PrivilegeDto, RoleDto} from "../model/model";
import Url from "../model/url";
import {HttpClient} from "@angular/common/http";
import {DataTableComponent, TableColumn} from "../_custom-component/data-table/data-table.component";
import {SearchOperation} from "../_custom-component/data-table/PaginationModel";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'role-registration',
  templateUrl: './role-registration.component.html',
  styleUrls: ['./role-registration.component.css']
})
export class RoleRegistrationComponent implements OnInit {
  lang: RoleRegistrationLang = new RoleRegistrationLang();
  roleSearchForm: FormGroup;
  roleHttpDataSource: HttpDataSource<RoleDto>;
  privilegeHttpDataSource: HttpDataSource<PrivilegeDto>;
  roleTableColumns: TableColumn[];
  privilegeTableColumns: TableColumn[];
  privilegeSearchForm: FormGroup;

  @ViewChild("editRoleButton")
  editRoleButton: MatButton;
  @ViewChild("roleTable")
  roleTable: DataTableComponent;

  isAddOrEdit: boolean;
  roleId: number;

  constructor(private fb: FormBuilder, private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.isAddOrEdit = false;
    this.roleSearchForm = this.fb.group({
      role: ['']
    });

    this.privilegeSearchForm = this.fb.group({
      privilege: ['']
    });

    this.roleHttpDataSource = new HttpDataSource<RoleDto>(Url.ROLE_FIND_PAGING, this.httpClient);
    this.roleHttpDataSource.beforeCall = request => {
      if (this.editRoleButton)
        this.editRoleButton.disabled = true

    }
    this.roleTableColumns = [
      {fieldName: 'title', title: this.lang.title},
      {fieldName: 'roleName', title: this.lang.roleName}
    ];

    this.privilegeHttpDataSource = new HttpDataSource<PrivilegeDto>(Url.PRIVILEGE_FIND_PAGING, this.httpClient);
    this.privilegeTableColumns = [
      {fieldName: 'title', title: this.lang.title},
      {fieldName: 'name', title: this.lang.privilegeName}
    ];

  }

  onSearchRole() {
    this.roleHttpDataSource.reload([{
      key: 'name',
      value: this.roleSearchForm.value.role,
      operation: SearchOperation.MATCH
    }]);
  }

  onSearchPrivilege() {

  }

  onAddRoleBack() {
    this.ngOnInit();
  }

}
