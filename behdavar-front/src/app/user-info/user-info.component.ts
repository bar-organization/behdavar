import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {


  userStatistic: UserStatistic = {
    userInfoList: [
      {key: 'کاربر فعال', value: 'محمد محمدی'},
      {key: 'نقش', value: 'کارشناس وصول'},
      {key: 'آخرین آپدیت طرح و برنامه', value: '۱۳۹۹/۰۲/۰۷'},
      {key: 'مبلغ کل پرنده ها', value: '212'},
      {key: 'مبلغ کل وصول شده', value: '6543'},

    ],
    documentInfoList: [
      {key: 'تعداد پرونده های موجود در سبد', value: '564'},
      {key: 'حساب کارشناس', value: '87869'},
      {key: 'مبلغ کل پرنده ها', value: '21287969879869'},
      {key: 'مبلغ کل وصول شده', value: '65438968968969'},
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
