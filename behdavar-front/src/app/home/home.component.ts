import {Component, OnInit} from '@angular/core';


import {HomeLang} from "../model/lang";
import {AuthService} from "../service/auth/auth.service";
import {Router} from "@angular/router";
import {AuthorityConstantEnum} from "../model/enum/AuthorityConstantEnum";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  lang: HomeLang = new HomeLang();

  linkList: TabLink[] = [
    {title: this.lang.myBasket, iconName: "shopping_basket", routerLink: "my-basket", visible: true},
    {title: this.lang.search, iconName: "search", routerLink: "search", visible: this.authService.hasAuthority(AuthorityConstantEnum.VIEW_SEARCH_MENU)},
    {title: this.lang.reports, iconName: "assignment", routerLink: "reports", visible: true},
    {title: this.lang.utilityTools, iconName: "build_circle", routerLink: "tools", visible: this.authService.hasAuthority(AuthorityConstantEnum.VIEW_UTILITY_TOOLS)},
    {title: this.lang.userManagement, iconName: "account_box", routerLink: "user-manage", visible: this.authService.hasAuthority(AuthorityConstantEnum.VIEW_USER_MANAGEMENT)},
    {title: this.lang.documentInput, iconName: "assignment_returned", routerLink: "document-input", visible: this.authService.hasAuthority(AuthorityConstantEnum.VIEW_DOCUMENT_ENTRY)},
  ];

  constructor(private authService: AuthService, private router: Router) {
  }


  onLogout() {
    this.authService.logout();
  }

  ngOnInit(): void {
    //
  }

}

interface TabLink {
  title: string;
  iconName: string;
  routerLink: string;
  visible: boolean;
}
