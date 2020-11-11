import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, PipeTransform, ViewChild} from '@angular/core';
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import HttpDataSource from "./HttpDataSource";
import {PagingRequest} from "./PaginationModel";
import {tap} from "rxjs/operators";
import {BehaviorSubject, Observable} from "rxjs";
import dot from "dot-object";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSelectionListChange} from "@angular/material/list";

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit, AfterViewInit {

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  @Input() multiSelectable: boolean;
  @Input() tableColumns: TableColumn[];
  @Input() matTableDataSource: MatTableDataSource<unknown>;
  @Input() httpDataSource: HttpDataSource<unknown>;
  @Input() selectedValue: unknown | unknown[];
  @Output() selectedValueChange = new EventEmitter<unknown | unknown[]>();
  @Input() selectable: boolean = true;
  @Input() title: string;
  @Input() columnSelectable = true;

  public loading$: Observable<boolean> = new BehaviorSubject<boolean>(false);
  public totalRecord$: Observable<number> = new BehaviorSubject<number>(0);
  private selection: SelectionModel<unknown>;
  rowNoTitle = 'ردیف';
  columnToDisplay: string[];
  selectableColumns: TableColumn[];

  ngOnInit(): void {
    this.configureTableColumns()
    this.configureMatTableDataSource();
    this.configureHttpDataSource();
  }

  ngAfterViewInit(): void {
    this.paginator.page.pipe(tap(() => this.configureHttpDataSource())).subscribe();
  }

  getPageRequest() {
    const request = new PagingRequest()
    if (this.paginator) {
      request.start = this.paginator.pageIndex;
      request.max = this.paginator.pageSize;
    } else {
      request.start = 0;
      request.max = 10;
    }

    return request;
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    return this.paginator?.pageSize === numSelected;
  }

  // TODO must refactor
  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear()

    } else {
      if (this.matTableDataSource) {
        this.matTableDataSource.data.forEach(row => this.selection.select(row));
      } else {
        this.httpDataSource.subject.getValue().forEach(row => this.selection.select(row));

      }
    }

  }

  checkboxLabel(row?: unknown): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row`;
  }

  private configureMatTableDataSource() {
    if (this.matTableDataSource) {
      this.matTableDataSource.sort = this.sort;
      this.matTableDataSource.paginator = this.paginator;
    }
  }

  private configureHttpDataSource() {
    if (this.httpDataSource) {
      this.httpDataSource.find(this.getPageRequest())
      this.loading$ = this.httpDataSource.loadingSubject.asObservable();
      this.totalRecord$ = this.httpDataSource.totalRecordSubject.asObservable();
    }
  }

  getColumnValue(element: any, col: TableColumn) {
    const fieldValue = this.handelDot(element, col.fieldName);
    return this.applyPip(fieldValue, col.pipNames);
  }

  handelDot(element: any, fieldName: string): string {
    if (!element || !fieldName) {
      return '';
    }
    if (fieldName.includes("+")) {
      let result = '';
      fieldName.split('+').forEach(v => {
        result += dot.pick(v, element) + ' '
      });
      return result;
    }
    return dot.pick(fieldName, element);
  }

  applyPip(value: any, pips: PipeWrapper[]): string {
    if (!pips || pips.length == 0) {
      return value;
    }

    let result: string = value;
    for (let pipName of pips) {
      result = pipName.pip.transform(result, pipName.args);
    }
    return result;
  }

  private multiToggle(multiRow: any[]) {
    this.selectedValueChange.emit(multiRow.filter(row => this.selection.isSelected(row)));
  }

  toggle(row: any): any {
    this.selectedValueChange.emit(this.selection.isSelected(row) ? row : undefined);
  }

  private configureTableColumns() {
    this.configureMultiSelectable();

    if (this.tableColumns) {
      this.tableColumns.forEach((column, index) => {
        if (!column.colName) {
          column.colName = `col${index}`;
        }
      });

      const filteredTableColumns: TableColumn[] = this.tableColumns ? this.tableColumns.filter(col => !col.hidden) : [];
      this.selectableColumns = filteredTableColumns;
      this.columnToDisplay = filteredTableColumns.map(col => col.colName);
      this.configureDefaultColumns();
    }
  }

  private configureDefaultColumns() {
    if (this.selectable) {
      this.columnToDisplay.unshift('select', 'rowNo');
    } else {
      this.columnToDisplay.unshift('rowNo');
    }
  }

  onPageChange() {
    this.selection.clear();
  }

  private configureMultiSelectable() {
    this.selection = new SelectionModel<unknown>(this.multiSelectable, []);
    this.selection.changed.subscribe(change => this.multiSelectable ? this.multiToggle(change.added) : this.toggle(change.added[0]))
  }

  reloadTable() {
    if (this.httpDataSource) {
      this.httpDataSource.reload();
    }
  }

  onSelectColumnToShow(event: MatSelectionListChange) {
    this.columnToDisplay = event.option.selectionList._value;
    this.configureDefaultColumns();
  }
}

export interface TableColumn {
  fieldName: string;
  colName?: string;
  title: string;
  hidden?: boolean;
  pipNames?: PipeWrapper[];
}

export interface PipeWrapper {
  pip: PipeTransform,
  args?: string[]
}

export declare type DataSourceType = 'array' | 'http' | undefined;
