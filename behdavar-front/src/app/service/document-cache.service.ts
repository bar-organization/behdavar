import {Injectable} from "@angular/core";
import {NavigationEnd, Router} from '@angular/router';
import {PagingRequest} from "../_custom-component/data-table/PaginationModel";

@Injectable()
export class DocumentCacheService {
  private myBasketDocumentSearchInfo: DocumentSearchInfo;
  private searchDocumentSearchInfo: DocumentSearchInfo;
  private docManagementDocumentSearchInfo: DocumentSearchInfo;

  private static readonly MY_BASKET_CACHE = 'MY_BASKET_CACHE';
  private static readonly SEARCH_CACHE = 'SEARCH_CACHE';
  private static readonly DOC_MANAGE_CACHE = 'DOC_MANAGE_CACHE';

  private previousUrl: string = undefined;
  private currentUrl: string = undefined;

  constructor(private router: Router) {
    this.currentUrl = this.router.url;
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.previousUrl = this.currentUrl;
        this.currentUrl = event.url;
      }
    });
  }

  private getDocumentSearchInfo(): DocumentSearchInfo {
    const currentRootUrl = this.getCurrentRootUrl();

    if (currentRootUrl === 'my-basket') {
      return !!DocumentCacheService.getFromSession(DocumentCacheService.MY_BASKET_CACHE) ?
        DocumentCacheService.getFromSession(DocumentCacheService.MY_BASKET_CACHE) :
        this.myBasketDocumentSearchInfo;
    }
    if (currentRootUrl === 'search') {
      return !!DocumentCacheService.getFromSession(DocumentCacheService.SEARCH_CACHE) ?
        DocumentCacheService.getFromSession(DocumentCacheService.SEARCH_CACHE) :
        this.searchDocumentSearchInfo;
    }
    if (currentRootUrl === 'document-manage') {
      return !!DocumentCacheService.getFromSession(DocumentCacheService.DOC_MANAGE_CACHE) ?
        DocumentCacheService.getFromSession(DocumentCacheService.DOC_MANAGE_CACHE) :
        this.docManagementDocumentSearchInfo;
    }
    return null;
  }

  private static getFromSession(key: string) {
    return JSON.parse(sessionStorage.getItem(key)) as DocumentSearchInfo;
  }

  private static setToSession(key: string,data:DocumentSearchInfo) {
    sessionStorage.setItem(key, JSON.stringify(data));
  }

  public clearSessionOfDocumentCache() {
    sessionStorage.removeItem(DocumentCacheService.MY_BASKET_CACHE);
    sessionStorage.removeItem(DocumentCacheService.SEARCH_CACHE);
    sessionStorage.removeItem(DocumentCacheService.DOC_MANAGE_CACHE);
  }

  private getCurrentRootUrl() {
    if (this.currentUrl.indexOf('/my-basket') >= 0)
      return 'my-basket';

    if (this.currentUrl.indexOf('/search') >= 0)
      return 'search';

    if (this.currentUrl.indexOf('/document-manage') >= 0)
      return 'document-manage';

    return '';
  }

  private setDocumentSearchInfo(documentSearchInfo: DocumentSearchInfo) {
    const currentRootUrl = this.getCurrentRootUrl();

    if (currentRootUrl === 'my-basket') {
      this.myBasketDocumentSearchInfo = documentSearchInfo;
      DocumentCacheService.setToSession(DocumentCacheService.MY_BASKET_CACHE, this.myBasketDocumentSearchInfo);
      return;
    }
    if (currentRootUrl === 'search') {
      this.searchDocumentSearchInfo = documentSearchInfo;
      DocumentCacheService.setToSession(DocumentCacheService.SEARCH_CACHE, this.searchDocumentSearchInfo);
      return;
    }
    if (currentRootUrl === 'document-manage') {
      this.docManagementDocumentSearchInfo = documentSearchInfo;
      DocumentCacheService.setToSession(DocumentCacheService.DOC_MANAGE_CACHE, this.docManagementDocumentSearchInfo);
      return;
    }
  }


  public initCache(documentSearchInfo: DocumentSearchInfo) {
    if (!this.getDocumentSearchInfo())
      this.patchValue(documentSearchInfo);
  }

  public updateCache(documentSearchInfo: DocumentSearchInfo) {
    this.patchValue(documentSearchInfo);
  }

  public applyCache(next?: (value: DocumentSearchInfo) => void) {
    next(this.getDocumentSearchInfo());
  }

  private patchValue(documentSearchInfo: DocumentSearchInfo): void {
    if (!documentSearchInfo)
      return;

    if (!this.getDocumentSearchInfo())
      this.setDocumentSearchInfo({});

    const documentSearchInfoTemp = this.getDocumentSearchInfo();

    if (documentSearchInfo.columnToDisplay !== undefined) {
      documentSearchInfoTemp.columnToDisplay = documentSearchInfo.columnToDisplay;
    }

    if (documentSearchInfo.pagingRequest !== undefined) {
      documentSearchInfoTemp.pagingRequest = documentSearchInfo.pagingRequest;
    }

    if (documentSearchInfo.documentSearchFormValue !== undefined) {
      documentSearchInfoTemp.documentSearchFormValue = documentSearchInfo.documentSearchFormValue;
    }

    this.setDocumentSearchInfo(documentSearchInfoTemp);
  }

}

export interface DocumentSearchInfo {
  columnToDisplay?: string[];
  pagingRequest?: PagingRequest;
  documentSearchFormValue?:any;
}


