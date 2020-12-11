import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Subject} from 'rxjs';
import {ConfirmDialogLang, Lang} from "../../model/lang";
import {MessageService} from "../../service/message.service";
import {HttpClient} from "@angular/common/http";
import dot from "dot-object";

@Component({
  selector: 'confirm-dialog',
  templateUrl: 'confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {
  lang: ConfirmDialogLang = new ConfirmDialogLang();

  constructor() {
  }

  ngOnInit(): void {

  }

}

