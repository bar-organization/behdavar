import {Component, Input, OnInit} from '@angular/core';
import {DocumentToolbarLang} from "../../model/lang";
import {AuthService} from "../../service/auth/auth.service";
import {AuthorityConstantEnum} from "../../model/enum/AuthorityConstantEnum";
import {Router} from "@angular/router";

@Component({
  selector: 'app-document-toolbar',
  templateUrl: './document-toolbar.component.html',
  styleUrls: ['./document-toolbar.component.css']
})
export class DocumentToolbarComponent implements OnInit {

  documentToolbarLang: DocumentToolbarLang = new DocumentToolbarLang();

  @Input()
  id: number;
  canShowChangeExpert: boolean;

  constructor(public authService: AuthService, private route: Router) {
    // TODO must change to right permission
    this.canShowChangeExpert = route.url.match('document-manage') && this.authService.hasAuthority(AuthorityConstantEnum.VIEW_DOCUMENT_MANAGEMENT);
  }

  ngOnInit(): void {
  }

}

export interface SelectedToolbar {
  no: number;
  value: any;
}
