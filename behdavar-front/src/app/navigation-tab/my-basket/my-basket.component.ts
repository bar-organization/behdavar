import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Lang} from "../../model/lang";

@Component({
  selector: 'my-basket',
  templateUrl: './my-basket.component.html',
  styleUrls: ['./my-basket.component.css']
})
export class MyBasketComponent implements OnInit {
  private lang = new Lang();

  constructor(private route:Router) {
  }

  ngOnInit(): void {
  }

}
