import {Component, OnInit} from '@angular/core';


import {HomeLang} from "../model/lang";
import {AuthService} from "../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  lang: HomeLang = new HomeLang();

  linkList: TabLink[] = [
    {title: this.lang.myBasket, iconName: "shopping_basket", routerLink: "my-basket", visible: true},
    {title: this.lang.search, iconName: "search", routerLink: "search", visible: true},
    {title: this.lang.reports, iconName: "assignment", routerLink: "reports", visible: true},
    {title: this.lang.utilityTools, iconName: "build_circle", routerLink: "tools", visible: true},
  ];

  constructor(private authService: AuthService,private router:Router) {
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
