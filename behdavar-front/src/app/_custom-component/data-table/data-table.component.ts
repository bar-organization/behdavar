import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, PipeTransform, ViewChild} from '@angular/core';
import {MatSort, Sort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import HttpDataSource from "./HttpDataSource";
import {PagingRequest, SortDirectionEnum, SortOperation} from "./PaginationModel";
import {tap} from "rxjs/operators";
import {BehaviorSubject, Observable} from "rxjs";
import dot from "dot-object";
import {SelectionModel} from "@angular/cdk/collections";
import {MatSelectionListChange} from "@angular/material/list";
import {CONTRACT_STATUS_NUMBER} from "../../model/enum/ContractStatus";
import {ContractStatusPip} from "../../_pip/ContractStatusPip";

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
  @Output() columnToDisplayChange = new EventEmitter<string[]>();
  @Input() getRowClassName: (row: any) => RowClassName;
  @Input() showColorHint: boolean;

  public loading$: Observable<boolean> = new BehaviorSubject<boolean>(false);
  public totalRecord$: Observable<number> = new BehaviorSubject<number>(0);
  private selection: SelectionModel<unknown>;
  rowNoTitle = 'ردیف';

  selectableColumns: SelectedColumn[];
  columnToDisplay: string[];

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

  private configureHttpDataSource(pagingRequest?: PagingRequest) {
    if (this.httpDataSource) {
      this.httpDataSource.find(!!pagingRequest ? pagingRequest : this.getPageRequest())
      this.loading$ = this.httpDataSource.loadingSubject.asObservable();
      this.totalRecord$ = this.httpDataSource.totalRecordSubject.asObservable();
    }
  }

  getColumnValue(element: any, col: TableColumn) {
    if (col.customValue)
      return col.customValue(element);

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
      this.selectableColumns = filteredTableColumns.map(value => <SelectedColumn>{
        colName: value.colName,
        isSelected: true,
        title: value.title
      });

      this.columnToDisplay = filteredTableColumns.map(col => col.colName);
      this.configureDefaultColumns();
    }
  }

  public refreshSelectableColumns() {
    this.selectableColumns.forEach(s => {
      if (this.columnToDisplay.indexOf(s.colName) >= 0) {
        s.isSelected = true;
      } else {
        s.isSelected = false;
      }
    })
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

  reloadTable(pagingRequest?: PagingRequest) {
    if (this.httpDataSource) {
      if (pagingRequest) {
        this.paginator.pageIndex = pagingRequest.start;
        this.paginator.pageSize = pagingRequest.max;
        this.httpDataSource.find(pagingRequest);
      } else {
        this.httpDataSource.reload();
      }
    }
  }

  onSelectColumnToShow(event: MatSelectionListChange) {
    this.columnToDisplay = event.option.selectionList._value;
    this.configureDefaultColumns();
    this.columnToDisplayChange.emit(this.columnToDisplay);
  }

  onSortChange(sort: Sort) {
    if (!this.httpDataSource || !this.isSortableColumn(sort.active)) {
      return;
    }

    const sortOperation: SortOperation = new SortOperation();
    sortOperation.sortBy = sort.active;
    if (!sort.direction) {
      this.httpDataSource.sortOperation = null;
    } else {
      sortOperation.direction = sort.direction === "asc" ? SortDirectionEnum.ASC : SortDirectionEnum.DESC;
      this.httpDataSource.sortOperation = sortOperation;
    }

    this.reloadTable();
  }

  isSortableColumn(columnName: string): boolean {
    const sortedColumn = this.tableColumns.find(col => col.colName === columnName);
    return sortedColumn && sortedColumn.sortable;
  }

  getCustomClass(row: any, col: TableColumn): string {
    if (!row || !col.customValue) {
      return;
    }
    return col.customValue(row);
  }

  getColorHints(): ColorHint[] {
    const colorHints: ColorHint[] = [];
    colorHints.push(new ColorHint("black-back"));
    colorHints.push(new ColorHint("red-back"));
    colorHints.push(new ColorHint("green-back"));
    colorHints.push(new ColorHint("blue-back"));
    colorHints.push(new ColorHint("purple-back"));
    colorHints.push(new ColorHint("gray-back"));
    return colorHints;
  }


  private convertBase64ToBlobData(base64Data: string, sliceSize = 512) {
    const byteCharacters = atob(base64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);

      byteArrays.push(byteArray);
    }

    return new Blob(byteArrays);
  }

  private getFileName(element: any, fieldName: string) {
    if (!element || !fieldName || !fieldName.includes("+")) {
      return '';
    }
    return dot.pick(fieldName.split('+')[0], element);
  }

  private getBase64(element: any, fieldName: string) {
    if (!element || !fieldName || !fieldName.includes("+")) {
      return '';
    }
    return dot.pick(fieldName.split('+')[1], element);
  }

  onFileDownloadClick(base64Content: string, filename: string) {
    const blobData = this.convertBase64ToBlobData(base64Content);
    if (window.navigator && window.navigator.msSaveOrOpenBlob) { //IE
      window.navigator.msSaveOrOpenBlob(blobData, filename);
    } else { // chrome
      const blob = new Blob([blobData]);
      const url = window.URL.createObjectURL(blob);
      // window.open(url);
      const link = document.createElement('a');
      link.href = url;
      link.download = filename;
      link.click();
    }

  }
}

export interface SelectedColumn {
  colName: string;
  title: string;
  isSelected: boolean;
}

export interface TableColumn {
  fieldName: string;
  colName?: string;
  type?: ColumnType;
  customValue?: (row: any) => any;
  title: string;
  hidden?: boolean;
  sortable?: boolean;
  pipNames?: PipeWrapper[];
}

export enum ColumnType {
  SIMPLE = 'SIMPLE', DOWNLOADABLE = 'DOWNLOADABLE', COLOR = 'COLOR'
}

export interface PipeWrapper {
  pip: PipeTransform,
  args?: string[]
}

export declare type DataSourceType = 'array' | 'http' | undefined;

export declare type RowClassName =
  'black-back'
  | 'red-back'
  | 'green-back'
  | 'blue-back'
  | 'purple-back'
  | 'gray-back'
  | undefined;

class ColorHint {
  title: string;
  className: RowClassName;

  constructor(className: RowClassName) {
    this.className = className;
    this.title = this.getTitle();
  }

  private getTitle(): string {
    for (let status of CONTRACT_STATUS_NUMBER) {
      const contractStatusPip = new ContractStatusPip();
      const className = contractStatusPip.transform(status.stringValue, 'cn');
      if (className === this.className) {
        return contractStatusPip.transform(status.stringValue) as string;
      }
    }
    return "";
  }
}
