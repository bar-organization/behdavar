import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {


  userStatistic: UserStatistic = {
    userInfoList: [
      {key: 'کاربر فعال', value: '-'},
      {key: 'نقش', value: '-'},
      {key: 'آخرین آپدیت طرح و برنامه', value: '-'},
      {key: 'مبلغ کل پرنده ها', value: '-'},
      {key: 'مبلغ کل وصول شده', value: '-'},

    ],
    documentInfoList: [
      {key: 'تعداد پرونده های موجود در سبد', value: '-'},
      {key: 'حساب کارشناس', value: '-'},
      {key: 'مبلغ کل پرنده ها', value: '-'},
      {key: 'مبلغ کل وصول شده', value: '-'},
    ]
  }

  constructor() {
  }


  ngOnInit(): void {
  }

}

interface UserStatistic {
  userInfoList: UserInfo[];
  documentInfoList: DocumentInfo[];
}

interface UserInfo {
  key: string;
  value: string;
}

interface DocumentInfo {
  key: string;
  value: string;
}
