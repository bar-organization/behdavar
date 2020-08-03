import {Component, OnInit} from '@angular/core';
import {FollowingLang} from '../model/lang';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  lang = new FollowingLang();
  followingForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
  }

}
