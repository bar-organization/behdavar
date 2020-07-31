﻿import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
// used to create fake backend
import {ErrorInterceptor, fakeBackendProvider, JwtInterceptor} from './_helpers';

import {appRoutingModule} from './app.routing';
import {AppComponent} from './app.component';
import {HomeComponent} from './home';
import {LoginComponent} from './login';
import {RegisterComponent} from './register';
import {AlertComponent} from './_components';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {UserInfoComponent} from './user-info/user-info.component'
import {MatToolbarModule} from '@angular/material/toolbar';
import {FlexModule} from '@angular/flex-layout';
import {UserInfoDetailComponent} from './user-info/user-info-detail/user-info-detail.component'
import {GuarantorsComponent} from './guarantors';
import {MatTabsModule} from "@angular/material/tabs";
import {MatMenuModule} from "@angular/material/menu";
import {DocumentSearchComponent} from './document/document-search/document-search.component';
import {DocumentToolbarComponent} from './document/document-toolbar/document-toolbar.component';
import {MatTableModule} from "@angular/material/table";
import {CustomerSearchFormComponent} from './document/document-search/customer-search-form/customer-search-form.component';
import {DocumentSearchFormComponent} from './document/document-search/document-search-form/document-search-form.component';
import {BankMachineSearchFormComponent} from './document/document-search/bank-machine-search-form/bank-machine-search-form.component';
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    appRoutingModule,
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
    MatCheckboxModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    UserInfoComponent,
    GuarantorsComponent,
    UserInfoDetailComponent,
    DocumentSearchComponent,
    DocumentToolbarComponent,
    CustomerSearchFormComponent,
    BankMachineSearchFormComponent,
    DocumentSearchFormComponent],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},

    // provider used to create fake backend
    fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
