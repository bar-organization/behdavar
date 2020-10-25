import {Injectable} from '@angular/core';
import {AuthService} from "./auth.service";
import {CanActivate} from "@angular/router";
import {AuthorityConstantEnum} from "../../model/enum/AuthorityConstantEnum";

@Injectable()
export class ReportGuardService implements CanActivate {

  constructor(public auth: AuthService) {
  }

  canActivate(): boolean {
    return this.auth.hasAuthority(AuthorityConstantEnum.VIEW_REPORT);

  }
}
