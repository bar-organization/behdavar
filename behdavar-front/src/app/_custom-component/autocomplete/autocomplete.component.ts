import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Subject} from 'rxjs';
import {Lang} from "../../model/lang";
import {MessageService} from "../../service/message.service";
import {HttpClient} from "@angular/common/http";
import dot from "dot-object";

/**
 * @title Chips Autocomplete
 */
@Component({
  selector: 'autocomplete',
  templateUrl: 'autocomplete.component.html',
  styleUrls: ['./autocomplete.component.css']
})
export class AutocompleteComponent implements OnInit, AfterViewInit {
  visible = true;
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  modelCtrl = new FormControl();
  filteredModels: Subject<any[]> = new Subject<any[]>();
  lang: Lang = new Lang();
  @ViewChild('modelInput') modelInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  @Input() value: any[] = [];
  @Output() valueChange: EventEmitter<any[]> = new EventEmitter<any[]>();

  @Input() isMulti: boolean;
  @Input() customClass: string;
  @Input() placeHolder: string;
  @Input() suggestionUrl: string;
  @Input() idFieldName: string;
  @Input() titleFieldName: string;

  constructor(private messageService: MessageService, private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.selectable = this.isMulti;
  }

  ngAfterViewInit(): void {

  }

  add(event: MatChipInputEvent): void {
    const input = event.input;

    if (this.matAutocomplete && this.matAutocomplete.options && this.matAutocomplete.options.first) {
      if (this.notExistInChipList(this.matAutocomplete.options.first.value)) {
        this.value.push(this.matAutocomplete.options.first.value);
      }
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    this.modelCtrl.setValue('');
    this.clearSuggestion();
    this.notifyChange();
  }

  private notExistInChipList(selectedValue: any): boolean {
    return this.value && !this.value.find(v => this.getId(v) === this.getId(selectedValue));
  }

  private notifyChange() {
    this.valueChange.emit(this.value);
  }

  private clearSuggestion() {
    if (this.filteredModels) {
      this.filteredModels.next([]);
    }
  }

  remove(model: any): void {
    const index = this.value.indexOf(model);

    if (index >= 0) {
      this.value.splice(index, 1);
    }

    this.modelCtrl.setValue('');
    this.clearSuggestion();
    this.notifyChange();
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    if (this.notExistInChipList(event.option.value)) {
      this.value.push(event.option.value);
      this.modelInput.nativeElement.value = '';
      this.modelCtrl.setValue('');
      this.clearSuggestion();
      this.notifyChange();
    }
  }

  private searchForSuggestion(filterValue: string): void {

    if (!filterValue) {
      this.filteredModels.next([]);
      return;
    }
    filterValue = filterValue.trim();
    if (filterValue.length < 3) {
      this.filteredModels.next([]);
      return;
    }

    if (typeof filterValue === "string") {
      this.httpClient.post<any[]>(this.suggestionUrl, filterValue).subscribe(model => this.filteredModels.next(model)
        , e => this.messageService.showGeneralError(this.lang.errorOnSearch, e));
    }


  }

  getId(model: any): string {
    return dot.pick(this.idFieldName, model);
  }

  getTitle(model: any): string {
    if (!this.titleFieldName) {
      return '';
    }
    let result: string = '';
    this.titleFieldName.split('+').forEach(value => {
      result += dot.pick(value, model) + ' '
    })
    return result;
  }

  onInput(event: any) {
    if (!this.isMulti && !this.isValueEmpty()) {
      return;
    }
    this.searchForSuggestion(event.target.value);
  }

  private isValueEmpty() {
    return !this.value || this.value.length < 1;
  }
}

