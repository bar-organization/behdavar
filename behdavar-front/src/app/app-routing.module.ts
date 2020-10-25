import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ReportGuardService} from "./service/auth/report-guard.service";
import {MyBasketComponent} from "./navigation-tab/my-basket/my-basket.component";
import {SearchPanelComponent} from "./navigation-tab/search-panel/search-panel.component";
import {ReportsComponent} from "./navigation-tab/reports/reports.component";
import {UtilityToolsComponent} from "./navigation-tab/utility-tools/utility-tools.component";
import {LoginComponent} from "./login/login.component";
import {GuarantorsComponent} from "./guarantors/guarantors.component";
import {FollowingComponent} from "./following/following.component";
import {CustomerComponent} from "./customer/customer.component";
import {DocumentComponent} from "./document/document.component";
import {FinancialStatusComponent} from "./financial-status/financial-status.component";
import {UserManagementComponent} from "./navigation-tab/user-management/user-management.component";
import {DocumentInputComponent} from "./navigation-tab/document-input/document-input.component";
import {DocumentManagementComponent} from "./navigation-tab/document-management/document-management.component";
import {ChangeExpertComponent} from "./change-expert/change-expert.component";
import {DocumentAttachmentComponent} from "./document-attachment/document-attachment.component";
import {DocumentManageGuardService} from "./service/auth/document-manage-guard.service";
import {ToolsGuardService} from "./service/auth/tools-guard.service";
import {UserManageGuardService} from "./service/auth/user-manage-guard.service";
import {DocumentInputGuardService} from "./service/auth/document-input-guard.service";

const routes: Routes = [
  // HOME
  {
    path: '', component: HomeComponent,
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
          {path: 'attachment/:id', component: DocumentAttachmentComponent},
          {path: 'financialStatus/:id', component: FinancialStatusComponent},
        ]
      },
      {
        path: 'search', component: SearchPanelComponent,
        children: [
          {path: '', redirectTo: 'find', pathMatch: 'full'},
          {path: 'find', component: DocumentComponent},
          {path: 'following/:id', component: FollowingComponent},
          {path: 'guarantors/:id', component: GuarantorsComponent},
          {path: 'attachment/:id', component: DocumentAttachmentComponent},
          {path: 'customers/:id', component: CustomerComponent},
        ]
      },
      {path: 'reports', component: ReportsComponent, canActivate: [ReportGuardService]},
      {path: 'tools', component: UtilityToolsComponent, canActivate: [ToolsGuardService]},
      {path: 'user-manage', component: UserManagementComponent, canActivate: [UserManageGuardService]},
      {path: 'document-input', component: DocumentInputComponent, canActivate: [DocumentInputGuardService]},
      {
        path: 'document-manage', component: DocumentManagementComponent, canActivate: [DocumentManageGuardService],
        children: [
          {path: '', redirectTo: 'find', pathMatch: 'full'},
          {path: 'find', component: DocumentComponent},
          {path: 'following/:id', component: FollowingComponent},
          {path: 'guarantors/:id', component: GuarantorsComponent},
          {path: 'customers/:id', component: CustomerComponent},
          {path: 'attachment/:id', component: DocumentAttachmentComponent},
          {path: 'financialStatus/:id', component: FinancialStatusComponent},
          {path: 'change-expert/:id', component: ChangeExpertComponent},
        ]
      },
    ]
  },
  // OTHER....
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
