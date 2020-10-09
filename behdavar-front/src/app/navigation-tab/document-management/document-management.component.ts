import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Lang} from "../../model/lang";

@Component({
  selector:'document-management',
  templateUrl:'./document-management.component.html'
})
export class DocumentManagementComponent implements OnInit{
  private lang = new Lang();

  constructor(public route: Router) {
  }

  ngOnInit(): void {
  }

}
