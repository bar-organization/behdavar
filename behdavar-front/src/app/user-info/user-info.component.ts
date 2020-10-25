import {AfterViewInit, Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserInfoDto} from "../model/model";
import Url from "../model/url";
import {JalaliPipe} from "../_pip/jalali.pipe";
import {AuthService} from "../service/auth/auth.service";
import {ThousandPip} from "../_pip/ThousandPip";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit,AfterViewInit {

  userStatistic: UserStatistic = {
    userInfoList: [
      {key: 'کاربر فعال', value: this.authService.getUserFullName()},
      {key: 'نقش', value: '--'},
      {key: 'آخرین آپدیت طرح و برنامه', value: '--'},

    ],
    documentInfoList: [
      {key: 'تعداد پرونده های موجود در سبد', value: '--'},
      {key: 'حساب کارشناس', value: '--'},
      {key: 'مبلغ کل پرنده ها', value: '--'},
      {key: 'مبلغ کل وصول شده', value: '--'},
    ]
  }

  constructor(private httpClient: HttpClient,private authService:AuthService,private jalaliPipe: JalaliPipe,private thousandPip:ThousandPip) {
  }


  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.httpClient.post<UserInfoDto>(Url.CARTABLE_GET_USER_INFO, null).subscribe(value => {
      if (!value) {
        return
      }
      this.userStatistic = {
        userInfoList: [
          {key: 'کاربر فعال', value: `${value?.user?.firstName} ${value?.user?.lastName}`},
          {key: 'نقش', value: value?.user?.roles[0]?.title},
          {key: 'آخرین آپدیت طرح و برنامه', value: this.jalaliPipe.transform(value.lastLogin)},

        ],
        documentInfoList: [
          {key: 'تعداد پرونده های موجود در سبد', value: value.activeCount ? value.activeCount.toString() : '0'},
          {key: 'حساب کارشناس', value: '-'},
          {key: 'مبلغ کل پرنده ها', value: this.thousandPip.transform(value?.userAmount?.totalAmount,'r') as string},
          {key: 'مبلغ کل وصول شده', value: this.thousandPip.transform(value?.userAmount?.receiveAmount,'r') as string},
        ]
      }
    });
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
