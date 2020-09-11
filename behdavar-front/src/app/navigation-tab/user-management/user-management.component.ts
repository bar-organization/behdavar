import { Component, OnInit } from '@angular/core';
import {UserManagementLang} from "../../model/lang";



@Component({
  selector: 'user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  lang: UserManagementLang = new UserManagementLang();
  constructor() { }

  ngOnInit(): void {
  }

}
