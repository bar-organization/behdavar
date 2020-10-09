import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ChangeExpertLang} from "../model/lang";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector:'change-expert',
  templateUrl:'./change-expert.component.html'
})
export class ChangeExpertComponent implements OnInit{

  lang = new ChangeExpertLang();
  documentNumber: string = '------';
  changeExpertFormGroup: FormGroup;

  constructor(private route: ActivatedRoute,private fb:FormBuilder) {

  }

  ngOnInit(): void {
    this.changeExpertFormGroup = this.fb.group({
      newExpert:[''],

    })
  }


  private getIdParam(): number {
    let id = this.route.snapshot.params['id'];
    try {
      return Number(id);
    } catch (e) {
      return null;
    }

  }

  changeExpert() {

  }
}
