import {Component} from '@angular/core';


import {HomeLang} from "../model/lang";
import {AuthService} from "../service/auth/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  lang: HomeLang = new HomeLang();

  constructor(private authService: AuthService) {
  }


  onLogout() {
    this.authService.logout();
  }

}
