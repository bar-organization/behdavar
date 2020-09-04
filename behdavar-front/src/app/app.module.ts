import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {UserInfoComponent} from './user-info/user-info.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {FlexModule} from '@angular/flex-layout';
import {UserInfoDetailComponent} from './user-info/user-info-detail/user-info-detail.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';
import {DocumentSearchComponent} from './document/document-search/document-search.component';
import {DocumentToolbarComponent} from './document/document-toolbar/document-toolbar.component';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {GuarantorsComponent} from './guarantors/guarantors.component';
import {CustomerComponent} from './customer/customer.component';
import {FollowingComponent} from './following/following.component';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {DocumentAttachmentComponent} from './document-attachment/document-attachment.component';
import {MyBasketComponent} from './navigation-tab/my-basket/my-basket.component';
import {SearchPanelComponent} from './navigation-tab/search-panel/search-panel.component';
import {ReportsComponent} from './navigation-tab/reports/reports.component';
import {UtilityToolsComponent} from './navigation-tab/utility-tools/utility-tools.component';
import {AppRoutingModule} from "./app-routing.module";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {DataTableComponent} from './_custom-component/data-table/data-table.component'
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {AuthInterceptorService} from "./service/auth/AuthInterceptorService";
import {DocumentComponent} from "./document/document.component";
import {NgxSpinnerModule} from "ngx-spinner";
import {SpinnerHttpInterceptor} from "./service/SpinnerHttpInterceptor";
import {ScrollingModule} from "@angular/cdk/scrolling";
import {JalaliPipe} from './_pip/jalali.pipe';
import {BlankToDashPipe} from './_pip/blank-to-dash.pipe';
import {FinancialStatusComponent} from './financial-status/financial-status.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";

@NgModule({
  imports: [
    MatSnackBarModule,
    AppRoutingModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatGridListModule,
    MatListModule,
    MatToolbarModule,
    FlexModule,
    MatTabsModule,
    MatMenuModule,
    MatTableModule,
    MatCheckboxModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatSortModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    FormsModule,
    NgxSpinnerModule,
    ScrollingModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    UserInfoComponent,
    GuarantorsComponent,
    UserInfoDetailComponent,
    DocumentSearchComponent,
    DocumentToolbarComponent,
    UserInfoDetailComponent,
    CustomerComponent,
    FollowingComponent,
    DocumentAttachmentComponent,
    MyBasketComponent,
    SearchPanelComponent,
    ReportsComponent,
    UtilityToolsComponent,
    DataTableComponent,
    DocumentComponent,
    JalaliPipe,
    BlankToDashPipe,
    FinancialStatusComponent],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: SpinnerHttpInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
