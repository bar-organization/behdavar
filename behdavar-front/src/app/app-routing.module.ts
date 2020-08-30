import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AuthGuardService} from "./service/auth/auth-guard.service";
import {MyBasketComponent} from "./navigation-tab/my-basket/my-basket.component";
import {SearchPanelComponent} from "./navigation-tab/search-panel/search-panel.component";
import {ReportsComponent} from "./navigation-tab/reports/reports.component";
import {UtilityToolsComponent} from "./navigation-tab/utility-tools/utility-tools.component";
import {LoginComponent} from "./login/login.component";
import {GuarantorsComponent} from "./guarantors/guarantors.component";
import {FollowingComponent} from "./following/following.component";
import {CustomerComponent} from "./customer/customer.component";
import {DocumentAttachmentComponent} from "./document-attachment/document-attachment.component";
import {DocumentComponent} from "./document/document.component";

const routes: Routes = [
  // HOME
  {
    path: '', component: HomeComponent, canActivate: [AuthGuardService],
    children: [
      {path: '', redirectTo: 'my-basket', pathMatch: 'full'},
      {
        path: 'my-basket', component: MyBasketComponent,
        children: [
          {path: '', redirectTo: 'find', pathMatch: 'full'},
          {path: 'find', component: DocumentComponent},
          {path: 'following/:id', component: FollowingComponent},
          {path: 'guarantors/:id', component: GuarantorsComponent},
          {path: 'customers/:id', component: CustomerComponent},
        ]
      },
      {
        path: 'search', component: SearchPanelComponent,
        children: [
          {path: '', redirectTo: 'find', pathMatch: 'full'},
          {path: 'find', component: DocumentComponent},
          {path: 'following/:id', component: FollowingComponent},
          {path: 'guarantors/:id', component: GuarantorsComponent},
          {path: 'customers/:id', component: CustomerComponent},
        ]
      },
      {path: 'reports', component: ReportsComponent},
      {path: 'tools', component: UtilityToolsComponent},

    ]
  },
  // OTHER....
  // TODO must fix after authentication added
  {path: 'login', component: LoginComponent},
  // { path: 'register', component: RegisterComponent },
  {path: 'guarantors', component: GuarantorsComponent},
  {path: 'following', component: FollowingComponent},
  {path: 'customers', component: CustomerComponent},
  {path: 'attachment', component: DocumentAttachmentComponent},

  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
