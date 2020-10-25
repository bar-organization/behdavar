import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {UserRegistrationLang} from "../model/lang";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonDto, RoleDto, UserDto} from "../model/model";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {SearchCriteria, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import Url from "../model/url";
import {MessageService} from "../service/message.service";
import {MatAutocomplete} from "@angular/material/autocomplete";
import {TableColumn} from "../_custom-component/data-table/data-table.component";
import HttpDataSource from "../_custom-component/data-table/HttpDataSource";

@Component({
  selector: 'user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  lang: UserRegistrationLang = new UserRegistrationLang();
  userSearchForm: FormGroup;
  userList: UserDto[];
  selectedUser: UserDto;
  hide: boolean = true;


  userTableColumns: TableColumn[];

  userHttpDatasource: HttpDataSource<UserDto>;



  newUserFormGroup: FormGroup;

  isInvalidUsername: boolean;
  isInvalidPassword: boolean;
  isInvalidCode: boolean;

  selectedPerson: PersonDto[] = [];
  personSuggestionUrl = Url.PERSON_FIND_SUGGESTION;

  selectedRoles: RoleDto[] = [];
  roleSuggestionUrl = Url.ROLE_FIND_SUGGESTION;

  constructor(private fb: FormBuilder, private httpClient: HttpClient, private messageService: MessageService) {

  }


  ngOnInit(): void {
    this.userSearchForm = this.fb.group({
      username: [''],
      isActive: ['none']
    });

    this.newUserFormGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      code: ['', Validators.required],
      isActive: [true]
    });

    this.configUserDataTable();

  }


  private configUserDataTable(username?: string) {
    this.userTableColumns = [
      {fieldName: 'firstName', title: this.lang.firstName},
      {fieldName: 'lastName', title: this.lang.lastName},
      {fieldName: 'username', title: this.lang.username},
    ];
    this.userHttpDatasource = new HttpDataSource<UserDto>(Url.USER_FIND_PAGING, this.httpClient);

  }

  onUserSelectChange(selectedUser: UserDto) {

    // is any user not selected , nothing happen
    if (!selectedUser) {
      this.selectedUser = null;
      return;
    }
    this.selectedUser = selectedUser;

    const person = new PersonDto();
    person.firstName = selectedUser.firstName;
    person.lastName = selectedUser.lastName
    this.selectedPerson = [person];

    // clear roleList
    this.selectedRoles = [];

    // patch only username to form
    this.newUserFormGroup.patchValue({'username': this.selectedUser.username, 'code': this.selectedUser.code, 'isActive': this.selectedUser.enabled});

    // TODO must optimize service call
    // for each role of selected user , load related role and added to roleList
    this.selectedUser.roles.forEach(value => {
        this.httpClient
          .post<RoleDto>(Url.ROLE_FIND_BY_ID, value.id)
          .subscribe(value => this.selectedRoles.push(value),
            e => this.messageService.showGeneralError(this.lang.errorOnSearchUser, e)
          );
      }
    );


  }

  newUser() {
    this.isInvalidUsername = false;
    this.isInvalidPassword = false;
    this.isInvalidCode = false;


    const newUser: UserDto = new UserDto();
    // validation
    if (!this.newUserFormGroup.valid) {
      this.isInvalidUsername = true;
      this.isInvalidPassword = true;
      this.isInvalidCode = true;
      this.newUserFormGroup.markAllAsTouched();
      return;
    }
    // extract date form form
    newUser.id = null;
    newUser.username = this.newUserFormGroup.value.username;
    newUser.password = this.newUserFormGroup.value.password;
    newUser.code = this.newUserFormGroup.value.code;
    newUser.enabled = this.newUserFormGroup.value.isActive;
    newUser.tokenExpired = false;
    newUser.isAccountNonExpired = true;
    newUser.isAccountNonLocked = true;
    newUser.isCredentialsNonExpired = true;
    newUser.createdDate = null;
    newUser.lastModifiedDate = null;

    //extract selected person id
    if (!this.selectedPerson || this.selectedPerson.length === 0) {
      this.messageService.showGeneralError(this.lang.personNotNull)
      return;
    }

    newUser.person = this.selectedPerson.map(p => {
      const person = new PersonDto();
      person.id = p.id;
      return person;
    })[0];

    //check roleList
    if (this.selectedRoles.length === 0) {
      this.messageService.showGeneralError(this.lang.userRoleNotEmpty)
      return;
    }

    //set active item in roleList to newUser
    newUser.roles = this.selectedRoles.map(r => {
      const role = new RoleDto();
      role.id = r.id;
      return role;
    });

    // perform save request
    this.httpClient
      .post<number>(Url.USER_SAVE, newUser)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.userHttpDatasource.reload();
          this.newUserFormGroup.reset({isActive:true});
          this.selectedPerson = [];
          this.selectedRoles = [];
        },
        e => this.messageService.showGeneralError(this.lang.error, e))

  }

  onSearchUser() {
    const username = this.userSearchForm.value.username;
    const isActive = this.userSearchForm.value.isActive;

    const searchCriteriaList: SearchCriteria[] = [
      {
        key: 'username',
        value: username,
        operation: SearchOperation.MATCH
      }];
    if (isActive !== 'none') {
      searchCriteriaList.push(
        {
          key: 'enabled',
          value: isActive === 'true',
          operation: SearchOperation.EQUAL
        })
    }

    this.userHttpDatasource.reload(searchCriteriaList);
  }

  updateUser() {
    if (!this.selectedUser) {
      this.messageService.showGeneralError(this.lang.selectAUserFromList);
      return;
    }
    this.isInvalidUsername = false;
    this.isInvalidPassword = false;
    this.isInvalidCode = false;


    // validation
    if (!this.newUserFormGroup.valid) {
      this.isInvalidUsername = true;
      this.isInvalidPassword = true;
      this.isInvalidCode = true;
      this.newUserFormGroup.markAllAsTouched();
      return;
    }
    // extract date form form
    this.selectedUser.username = this.newUserFormGroup.value.username;
    this.selectedUser.password = this.newUserFormGroup.value.password;
    this.selectedUser.code = this.newUserFormGroup.value.code;
    this.selectedUser.enabled = this.newUserFormGroup.value.isActive;
    this.selectedUser.tokenExpired = false;
    this.selectedUser.isAccountNonExpired = true;
    this.selectedUser.isAccountNonLocked = true;
    this.selectedUser.isCredentialsNonExpired = true;
    this.selectedUser.createdDate = null;
    this.selectedUser.lastModifiedDate = null;

    //extract selected person id
    if (!this.selectedPerson || this.selectedPerson.length === 0) {
      this.messageService.showGeneralError(this.lang.personNotNull)
      return;
    }

    this.selectedUser.person = this.selectedPerson.map(p => {
      const person = new PersonDto();
      person.id = p.id;
      return person;
    })[0];

    //check roleList
    if (this.selectedRoles.length === 0) {
      this.messageService.showGeneralError(this.lang.userRoleNotEmpty)
      return;
    }

    //set active item in roleList to newUser
    this.selectedUser.roles = this.selectedRoles.map(r => {
      const role = new RoleDto();
      role.id = r.id;
      return role;
    });

    // perform save request
    this.httpClient
      .post<number>(Url.USER_UPDATE, this.selectedUser)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.userHttpDatasource.reload();
          this.newUserFormGroup.reset({isActive:true});
          this.selectedPerson = []
          this.selectedRoles = [];
        },
        e => this.messageService.showGeneralError(this.lang.error, e))
  }

  onUserSearchReset() {
    this.userSearchForm.reset({isActive: 'none'});
  }
}

interface RoleWrapper {
  role: RoleDto;
  active: boolean;
  isNew?: boolean;
}
