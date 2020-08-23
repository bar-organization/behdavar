import {EventEmitter, Injectable} from '@angular/core';
import {SelectedToolbar} from "../document/document-toolbar/document-toolbar.component";

@Injectable({
  providedIn: 'root'
})
export class DocumentToolbarService {

  constructor() {
  }

  onClickHappen: EventEmitter<SelectedToolbar> = new EventEmitter<SelectedToolbar>();
}
