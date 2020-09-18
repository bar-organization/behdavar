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
import {MatCheckboxChange} from "@angular/material/checkbox";

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit, AfterViewInit {

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  @Input()
  tableColumns: TableColumn[];

  @Input()
  matTableDataSource: MatTableDataSource<unknown>;

  @Input()
  httpDataSource: HttpDataSource<unknown>;

  @Input()
  idField: number;

  @Output()
  idFieldChange = new EventEmitter<number>();

  @Input()
  idFieldName: string;

  @Input()
  selectable: boolean = true;

  public loading$: Observable<boolean> = new BehaviorSubject<boolean>(false);
  public totalRecord$: Observable<number> = new BehaviorSubject<number>(0);
  private selection = new SelectionModel<unknown>(false, []);
  loadingText = 'درحال واکشی...';
  rowNoTitle = 'ردیف';


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
      request.max = 5;
    }

    return request;
  }

  getDisplayedColumns(): string[] {
    if (!this.tableColumns) {
      return [];
    }
    let columnToDisplay = this.tableColumns.filter(value => !value.hidden).map(value => value.colName);

    if (this.selectable) {
      columnToDisplay.unshift('select', 'rowNo');
    } else {
      columnToDisplay.unshift('rowNo');
    }

    return columnToDisplay;
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

  handelDot(element: any, fieldName: string): string {
    if (!element || !fieldName) {
      return '';
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

  // TODO must refactor and separate method implementation
  toggle(row: any, $event: MatCheckboxChange): any {
    let result = $event ? this.selection.toggle(row) : null;

    if (this.selection.isSelected(row)) {
      if (!this.idFieldName) {
        this.idFieldName = 'id';
      }
      this.idFieldChange.emit(dot.pick(this.idFieldName, row));
    } else {
      this.idFieldChange.emit(undefined);

    }
    return result;
  }

  private configureTableColumns() {
    if (this.tableColumns) {
      this.tableColumns.forEach((column, index) => {
        if (!column.colName) {
          column.colName = `col${index}`;
        }
      });
    }
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
