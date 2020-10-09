import { Component, OnInit } from '@angular/core';
import {Lang} from "../../model/lang";
import {Router} from "@angular/router";

@Component({
  selector: 'search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {
  private lang = new Lang();

  constructor(public route: Router) {
  }

  ngOnInit(): void {
  }

}
