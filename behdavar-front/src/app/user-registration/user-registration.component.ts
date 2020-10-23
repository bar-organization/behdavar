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
export class UserRegistrationComponent implements OnInit, AfterViewInit {

  private static readonly SUPERVISOR_FILTER: SearchCriteria = {
    key: 'username',
    value: 'SUPERVISOR_USER',
    operation: SearchOperation.NOT_EQUAL
  };

  lang: UserRegistrationLang = new UserRegistrationLang();
  userSearchForm: FormGroup;
  userList: UserDto[];
  selectedUser: UserDto;
  hide: boolean = true;


  roleList: RoleWrapper[] = [];
  userTableColumns: TableColumn[];

  userHttpDatasource: HttpDataSource<UserDto>;
  @ViewChild("auto")
  roleAutocomplete: MatAutocomplete;

  @ViewChild("personAuto")
  personAutocomplete: MatAutocomplete;
  personDto: PersonDto;
  personOptions: Subject<PersonDto[]> = new Subject<PersonDto[]>();

  roleOptions: Subject<RoleDto[]> = new Subject<RoleDto[]>();

  newUserFormGroup: FormGroup;
  personAutocompleteControl = new FormControl();

  roleControl = new FormControl();
  isInvalidUsername: boolean;
  isInvalidPassword: boolean;

  isInvalidCode: boolean;

  constructor(private fb: FormBuilder, private httpClient: HttpClient, private messageService: MessageService) {

  }

  ngOnInit(): void {
    this.userSearchForm = this.fb.group({
      username: [''],
      isActive: [true]
    });

    this.newUserFormGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      code: ['', Validators.required],
      isActive: [true]
    });

    this.configUserDataTable();

    this.personAutocompleteControl.valueChanges.subscribe(value => this.personAutocompleteFilter(value));
    this.roleControl.valueChanges.subscribe(value => this._filter(value));
  }


  ngAfterViewInit(): void {
    this.personAutocomplete.optionSelected.subscribe(v => {
      if (v && v.option) {
        this.personDto = new PersonDto();
        this.personDto.id = v.option.id;
      }
    });

  }


  private configUserDataTable(username?: string) {
    this.userTableColumns = [
      {fieldName: 'firstName', title: this.lang.firstName},
      {fieldName: 'lastName', title: this.lang.lastName},
      {fieldName: 'username', title: this.lang.username},
    ];
    this.userHttpDatasource = new HttpDataSource<UserDto>(Url.USER_FIND_PAGING, this.httpClient, [UserRegistrationComponent.SUPERVISOR_FILTER]);

  }

  private _filter(filterValue: string) {
    if (!filterValue) {
      return;
    }
    if (filterValue.length < 3) {
      return;
    }
    this.httpClient.post<RoleDto[]>(Url.ROLE_FIND_SUGGESTION, filterValue).subscribe(role => {
      this.roleOptions.next(role);
    }, e => this.messageService.showGeneralError(this.lang.errorOnFindRole, e));
  }

  private personAutocompleteFilter(filterValue: string) {
    if (!filterValue) {
      return;
    }
    if (filterValue.length < 3) {
      return;
    }
    this.httpClient.post<PersonDto[]>(Url.PERSON_FIND_SUGGESTION, filterValue).subscribe(person => {
      this.personOptions.next(person);
    }, e => this.messageService.showGeneralError(this.lang.errorOnSearchPerson, e));
  }

  onUserRoleDelete(role: RoleWrapper) {
    if (role.isNew) {
      this.roleList = this.roleList.filter(value => value !== role);
    } else {
      role.active = !role.active;

    }
  }

  // TODO must refactor

  addRole() {
    // if no item selected in autocompleter
    if (!this.roleAutocomplete || !this.roleAutocomplete.options || !this.roleAutocomplete.options.first)
      return;

    // extract id , and title
    const selectedRoleId = this.roleAutocomplete?.options?.first.id;
    const selectedRoleTitle = this.roleAutocomplete?.options?.first.value;

    // make roleDto model from id and title
    const role = new RoleDto();
    role.id = Number(selectedRoleId);
    role.title = selectedRoleTitle;

    // find is selected role exist in roleList or not ?
    let isDuplicate = false;
    for (let roleWrapper of this.roleList) {
      if (roleWrapper.role.id === role.id) {
        isDuplicate = true;
        break;
      }
    }

    // if role not exist in roleList then added to list and new Role
    if (!isDuplicate)
      this.roleList.push({role: role, active: true, isNew: true});

  }

  onUserSelectChange(selectedUser: UserDto) {

    // is any user not selected , nothing happen
    if (!selectedUser) {
      this.selectedUser = null;
      return;
    }
    this.selectedUser = selectedUser;

    // clear roleList
    this.roleList = [];

    // patch only username to form
    this.newUserFormGroup.patchValue({'username': this.selectedUser.username, 'code': this.selectedUser.code, 'isActive': this.selectedUser.enabled});

    // TODO must optimize service call
    // for each role of selected user , load related role and added to roleList
    this.selectedUser.roles.forEach(value => {
        this.httpClient
          .post<RoleDto>(Url.ROLE_FIND_BY_ID, value.id)
          .subscribe(value => this.roleList.push({role: value, active: true, isNew: false}),
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
    if (!this.personDto || !this.personDto.id) {
      this.messageService.showGeneralError(this.lang.personNotNull)
      return;
    }

    newUser.person = this.personDto;

    //check roleList
    if (this.roleList.length === 0) {
      this.messageService.showGeneralError(this.lang.userRoleNotEmpty)
      return;
    }

    //set active item in roleList to newUser
    newUser.roles = this.roleList.filter(value => value.active).map(value => value.role);

    // perform save request
    this.httpClient
      .post<number>(Url.USER_SAVE, newUser)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.userHttpDatasource.reload([UserRegistrationComponent.SUPERVISOR_FILTER]);
          this.newUserFormGroup.reset();
          this.personAutocompleteControl.reset();
          this.roleControl.reset();
          this.roleList = [];
        },
        e => this.messageService.showGeneralError(this.lang.error, e))

  }

  onSearchUser() {
    const username = this.userSearchForm.value.username;
    const isActive = this.userSearchForm.value.isActive;

    const searchCriteriaList: SearchCriteria[] = [UserRegistrationComponent.SUPERVISOR_FILTER,
      {
        key: 'username',
        value: username,
        operation: SearchOperation.MATCH
      },
      {
        key: 'enabled',
        value: isActive,
        operation: SearchOperation.EQUAL
      }];

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
    if (!this.personDto || !this.personDto.id) {
      this.messageService.showGeneralError(this.lang.personNotNull)
      return;
    }

    this.selectedUser.person = this.personDto;

    //check roleList
    if (this.roleList.length === 0) {
      this.messageService.showGeneralError(this.lang.userRoleNotEmpty)
      return;
    }

    //set active item in roleList to newUser
    this.selectedUser.roles = this.roleList.filter(value => value.active).map(value => value.role);
    //
    this.selectedUser.roles.forEach(value => {
      value.lastModifiedDate = null;
      value.createdDate = null;
      value.privilegeDtos.forEach(value1 => {
        value1.lastModifiedDate = null;
        value1.createdDate = null;
      })
    });
    // perform save request
    this.httpClient
      .post<number>(Url.USER_UPDATE, this.selectedUser)
      .subscribe(value => {
          this.messageService.showGeneralSuccess(this.lang.successSave);
          this.userHttpDatasource.reload([UserRegistrationComponent.SUPERVISOR_FILTER]);
          this.newUserFormGroup.reset();
          this.personAutocompleteControl.reset();
          this.roleControl.reset();
          this.roleList = [];
        },
        e => this.messageService.showGeneralError(this.lang.error, e))
  }
}

interface RoleWrapper {
  role: RoleDto;
  active: boolean;
  isNew?: boolean;
}
