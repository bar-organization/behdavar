import {Injectable} from "@angular/core";
import {NavigationEnd, Router} from '@angular/router';

@Injectable()
export class DocumentCacheService {
  private myBasketDocumentSearchInfo: DocumentSearchInfo;
  private searchDocumentSearchInfo: DocumentSearchInfo;
  private docManagementDocumentSearchInfo: DocumentSearchInfo;

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
      return this.myBasketDocumentSearchInfo;
    }
    if (currentRootUrl === 'search') {
      return this.searchDocumentSearchInfo;
    }
    if (currentRootUrl === 'document-manage') {
      return this.docManagementDocumentSearchInfo;
    }
    return null;
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
      return;
    }
    if (currentRootUrl === 'search') {
      this.searchDocumentSearchInfo = documentSearchInfo;
      return;
    }
    if (currentRootUrl === 'document-manage') {
      this.docManagementDocumentSearchInfo = documentSearchInfo;
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


    if (documentSearchInfo.columnToDisplay !== undefined) {
      if (!this.getDocumentSearchInfo())
        this.setDocumentSearchInfo({});
      this.getDocumentSearchInfo().columnToDisplay = documentSearchInfo.columnToDisplay;
    }
  }

}

export interface DocumentSearchInfo {
  columnToDisplay?: string[];
}


