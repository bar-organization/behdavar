import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
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

  @Output()
  changeId = new EventEmitter<number>();

  @Input()
  idFieldName: string;

  public loading$: Observable<boolean> = new BehaviorSubject<boolean>(false);
  public totalRecord$: Observable<number> = new BehaviorSubject<number>(0);
  private selection = new SelectionModel<unknown>(false, []);
  loadingText = 'درحال واکشی...';
  rowNoTitle = 'ردیف';


  ngOnInit(): void {
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
    let columnToDisplay = this.tableColumns.filter(value => !value.hidden).map(value => value.fieldName);
    columnToDisplay.unshift('select', 'rowNo');
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

  handelDotAndPip(element: any, col: TableColumn): string {
    if (!element || !col || !col.fieldName) {
      return '';
    }

    let result = dot.pick(col.fieldName, element);
    // TODO must handel pip
    // let pipChain = '';
    // if (col.pipNames && col.pipNames.length > 0) {
    //   for (let pipName of col.pipNames) {
    //     pipChain += ' | ' + pipName;
    //   }
    // }
    // console.log(pipChain);
    // return result + pipChain;
    return result;
  }

  // TODO must refactor and separate method implementation
  toggle(row: any, $event: MatCheckboxChange): any {
    let result = $event ? this.selection.toggle(row) : null;

    if (this.selection.isSelected(row)) {
      if (!this.idFieldName) {
        this.idFieldName = 'id';
      }
      this.changeId.emit(dot.pick(this.idFieldName, row));
    } else {
      this.changeId.emit(undefined);

    }
    return result;
  }
}

export interface TableColumn {
  fieldName: string;
  title: string;
  hidden?: boolean;
  pipNames?: string[];
}

export declare type DataSourceType = 'array' | 'http' | undefined;
