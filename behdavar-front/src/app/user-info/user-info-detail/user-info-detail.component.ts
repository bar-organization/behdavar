import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-info-detail',
  templateUrl: './user-info-detail.component.html',
  styleUrls: ['./user-info-detail.component.css']
})
export class UserInfoDetailComponent implements OnInit {

  @Input()
  key: string;
  @Input()
  value: string;

  constructor() {
  }

  ngOnInit(): void {
  }

}
