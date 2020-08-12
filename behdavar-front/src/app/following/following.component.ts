import {Component, OnInit} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  lang = new FollowingLang();
  followingForm: FormGroup;

  followingTypeList: string[] = ['تلفنی', 'نامه', 'واتس آپ'];
  followingResultList: string[] = ['واریز خواهد داشت', 'جواب نداد', 'یکی از اقوامش جواب داد'];
  followingType = new FormControl();
  followingResult = new FormControl();

  constructor() { }

  ngOnInit(): void {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }

}
