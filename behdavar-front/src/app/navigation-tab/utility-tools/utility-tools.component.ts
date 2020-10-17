import {Component, OnInit} from '@angular/core';
import {UtilityToolsLang} from "../../model/lang";

@Component({
  selector: 'utility-tools',
  templateUrl: './utility-tools.component.html',
  styleUrls: ['./utility-tools.component.css']
})
export class UtilityToolsComponent implements OnInit {
  lang = new UtilityToolsLang();
  links : LinkModel[] = [
    {link:'https://118tel.ir',icon:'../../assets/image/118.jpg',title:this.lang.link_118},
    {link:'https://www.centinsur.ir',icon:'../../assets/image/bemeh.jpg',title:this.lang.link_insurance},
    {link:'https://www.google.com/maps',icon:'../../assets/image/map.jpg',title:this.lang.link_map},
    {link:'http://lawyer.rayansaipa.com/',icon:'../../assets/image/saipa.jpg',title:this.lang.link_saipa},
    {link:'http://www.behdavar.com',icon:'../../assets/image/icon.jpg',title:this.lang.link_behdavar},
  ]
  constructor() {
  }

  ngOnInit(): void {
  }

}

interface LinkModel {
  link:string,
  icon:string
  title: string;
}
