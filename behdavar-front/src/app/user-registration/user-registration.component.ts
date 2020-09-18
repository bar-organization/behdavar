import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {UserRegistrationLang} from "../model/lang";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonDto, RoleDto, UserDto} from "../model/model";
import {Subject} from "rxjs";
import {MatSelectionList} from "@angular/material/list";
import {HttpClient} from "@angular/common/http";
import {PagingRequest, PagingResponse, SearchOperation} from "../_custom-component/data-table/PaginationModel";
import Url from "../model/url";
import {MessageService} from "../service/message.service";
import {MatAutocomplete} from "@angular/material/autocomplete";

@Component({
  selector: 'user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit,AfterViewInit {

  lang: UserRegistrationLang = new UserRegistrationLang();
  userSearchForm: FormGroup;
  userList: UserDto[];
  roleList: RoleWrapper[] = [];

  @ViewChild("matUserList")
  matUserList: MatSelectionList;
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

  constructor(private fb: FormBuilder, private httpClient: HttpClient, private messageService: MessageService) {

  }

  ngOnInit(): void {
    this.userSearchForm = this.fb.group({
      username: ['']
    });

    this.newUserFormGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      isActive: [true]
    });

    this.requestSearchUser();

    this.personAutocompleteControl.valueChanges.subscribe(value => this.personAutocompleteFilter(value));
    this.roleControl.valueChanges.subscribe(value => this._filter(value));
  }

  ngAfterViewInit(): void{
    this.personAutocomplete.optionSelected.subscribe(v => {
      console.log('eeeee', v);
      if(v && v.option){
        this.personDto = new PersonDto();
        this.personDto.id = v.option.id;
      }
    });

  }


  private requestSearchUser(username?: string) {
    const request = new PagingRequest();
    request.start = 0;
    request.max = 10;

    if (username) {
      request.filters = [{key: 'username', value: username, operation: SearchOperation.MATCH}];
    }

    this.httpClient.post<PagingResponse<UserDto>>(Url.USER_FIND_PAGING, request).subscribe(value => {
        this.userList = value.data;
      },
      e => this.messageService.showGeneralError(this.lang.errorOnSearchUser, e)
    );
  }


  private _filter(filterValue: string) {
    if (filterValue.length < 3) {
      return;
    }
    this.httpClient.post<RoleDto[]>(Url.ROLE_FIND_SUGGESTION, filterValue).subscribe(role => {
      this.roleOptions.next(role);
    }, e => this.messageService.showGeneralError(this.lang.errorOnFindRole, e));
  }

  private personAutocompleteFilter(filterValue: string) {
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

  test() {

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

  onUserSelectChange() {
    const selectedUser = this.matUserList.selectedOptions.selected[0];
    // is any user not selected , nothing happen
    if (!selectedUser) {
      return;
    }

    // clear roleList
    this.roleList = [];

    // patch only username to form
    const userDto: UserDto = selectedUser.value;
    this.newUserFormGroup.patchValue({'username': userDto.username});

    // TODO must optimize service call
    // for each role of selected user , load related role and added to roleList
    userDto.roles.forEach(value => {
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


    const newUser: UserDto = new UserDto();
    // validation
    if (!this.newUserFormGroup.valid) {
      this.isInvalidUsername = true;
      this.isInvalidPassword = true;
      this.newUserFormGroup.markAllAsTouched();
      return;
    }
    // extract date form form
    newUser.id = null;
    newUser.username = this.newUserFormGroup.value.username;
    newUser.password = this.newUserFormGroup.value.password;
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
      .subscribe(value => this.messageService.showGeneralSuccess(this.lang.successSave),
        e => this.messageService.showGeneralError(e, this.lang.error))

  }

  onSearchUser() {
    this.requestSearchUser(this.userSearchForm.value.username);
  }

}

interface RoleWrapper {
  role: RoleDto;
  active: boolean;
  isNew?: boolean;
}
