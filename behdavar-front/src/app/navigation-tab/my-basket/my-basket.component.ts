import {Component, OnInit} from '@angular/core';
import {DocumentToolbarService} from "../../service/document-toolbar.service";
import {SelectedToolbar} from "../../document/document-toolbar/document-toolbar.component";

@Component({
  selector: 'my-basket',
  templateUrl: './my-basket.component.html',
  styleUrls: ['./my-basket.component.css']
})
export class MyBasketComponent implements OnInit {
  v: SelectedToolbar
  isSearch: boolean = true;
  isCustomer: boolean;
  isFollowing: boolean;
  isGuarantor: boolean;

  constructor(private d: DocumentToolbarService) {
    this.d.onClickHappen.subscribe(d => {
      this.v = d;
      this.isSearch = false;

      if (d.no === 1) {
        this.isFollowing = true;
      } else if (d.no === 2) {
        this.isGuarantor = true;
      } else if (d.no === 3) {
        this.isCustomer = true;
      } else {
        this.isSearch = true;
      }

    });
  }

  ngOnInit(): void {
  }

  onExit() {
    this.isSearch = true;
    this.isCustomer = false;
    this.isFollowing = false;
    this.isGuarantor = false;
  }
}
