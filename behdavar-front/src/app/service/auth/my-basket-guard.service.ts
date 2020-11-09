import {Injectable} from "@angular/core";
import {CanActivate} from "@angular/router";
import {AuthService} from "./auth.service";
import {AuthorityConstantEnum} from "../../model/enum/AuthorityConstantEnum";


@Injectable()
export class MyBasketGuardService implements CanActivate {

  constructor(public auth: AuthService) {
  }

  canActivate(): boolean {
    return this.auth.hasAuthority(AuthorityConstantEnum.VIEW_MY_BASKET);

  }
}
