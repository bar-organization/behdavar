import {Component, OnInit} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import Url from "../model/url";
import {PursuitDto} from "../model/model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  lang = new FollowingLang();
  followingForm: FormGroup;
  pursuit: PursuitDto;

  followingTypeList: string[] = ['تلفنی', 'نامه', 'واتس آپ'];
  followingResultList: string[] = ['واریز خواهد داشت', 'جواب نداد', 'یکی از اقوامش جواب داد'];
  followingType = new FormControl();
  followingResult = new FormControl();

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.httpClient.post<PursuitDto>(Url.PURSUIT_FIND_BY_CONTRACT, this.getIdParam())
      .subscribe(value => this.pursuit = value);
  }

  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }

}
