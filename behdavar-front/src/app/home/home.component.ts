import {Component, OnInit} from '@angular/core';


import {HomeLang} from "../model/lang";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  lang: HomeLang = new HomeLang();


  ngOnInit() {
    // this.loadAllUsers();
  }


}
