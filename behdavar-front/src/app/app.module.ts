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
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
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
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
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
import {UserManagementComponent} from './navigation-tab/user-management/user-management.component';
import {UserRegistrationComponent} from './user-registration/user-registration.component';
import {RoleRegistrationComponent} from './role-registration/role-registration.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatTooltipModule} from "@angular/material/tooltip";
import {AddRoleComponent} from './role-registration/add-role/addRole.component';
import {DocumentInputComponent} from './navigation-tab/document-input/document-input.component';
import {MatRadioModule} from "@angular/material/radio";
import {ContactToFlatStringPipe} from "./_pip/contant-to-flat-string.pipe";
import {DocumentManagementComponent} from "./navigation-tab/document-management/document-management.component";
import {ChangeExpertComponent} from "./change-expert/change-expert.component";
import {AutocompleteComponent} from "./_custom-component/autocomplete/autocomplete.component";
import {MatChipsModule} from "@angular/material/chips";
import {ContractStatusPip} from "./_pip/ContractStatusPip";
import {PursuitTypePip} from "./_pip/PursuitTypePip";
import {ThousandPip} from "./_pip/ThousandPip";
import {CurrencyMaskModule} from "ng2-currency-mask";
import {DocumentManageGuardService} from "./service/auth/document-manage-guard.service";
import {ReportGuardService} from "./service/auth/report-guard.service";
import {ToolsGuardService} from "./service/auth/tools-guard.service";
import {UserManageGuardService} from "./service/auth/user-manage-guard.service";
import {DocumentInputGuardService} from "./service/auth/document-input-guard.service";
import {PaymentService} from "./service/payment-service";
import {DocumentFlowComponent} from "./document-flow/document-flow.component";
import {DocumentChangeStatusComponent} from "./document-chagne-status/document-change-status.component";
import {ContractService} from "./service/contract-service";
import {MyBasketGuardService} from "./service/auth/my-basket-guard.service";
import {SearchGuardService} from "./service/auth/search-guard.service";
import {ResultTypePip} from "./_pip/ResultTypePip";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {PlatformModule} from '@angular/cdk/platform';
import {PaymentTypePip} from "./_pip/PaymentTypePip";
import {YesNoPipe} from "./_pip/yes-no.pipe";
import {DocumentCacheService} from "./service/document-cache.service";
import {JalaliMomentDateAdapter} from "./service/jalali-date/jalali-moment-date-adapter";
import {JALALI_MOMENT_FORMATS, MOMENT_FORMATS} from "./service/jalali-date/jalali_moment_formats";
import {PursuitService} from "./service/pursuit-service";

@NgModule({
  imports: [
    PlatformModule,
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
    ScrollingModule,
    MatAutocompleteModule,
    MatTooltipModule,
    MatRadioModule,
    MatChipsModule,
    CurrencyMaskModule
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
    ContactToFlatStringPipe,
    ContractStatusPip,
    PursuitTypePip,
    ResultTypePip,
    PaymentTypePip,
    YesNoPipe,
    ThousandPip,
    FinancialStatusComponent,
    UserManagementComponent,
    UserRegistrationComponent,
    RoleRegistrationComponent,
    AddRoleComponent,
    DocumentInputComponent,
    DocumentManagementComponent,
    ChangeExpertComponent,
    AutocompleteComponent,
    DocumentFlowComponent,
    DocumentChangeStatusComponent,

  ],

  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: SpinnerHttpInterceptor, multi: true},

    {
      provide: DateAdapter,
      useClass: JalaliMomentDateAdapter,
      deps: [MAT_DATE_LOCALE]
    },
    { provide: MAT_DATE_LOCALE, useValue: "fa" }, // en-GB  fr
    {
      provide: MAT_DATE_FORMATS,
      useFactory: locale => {
        if (locale === "fa") {
          return JALALI_MOMENT_FORMATS;
        } else {
          return MOMENT_FORMATS;
        }
      },
      deps: [MAT_DATE_LOCALE]
      // useValue: JALALI_MOMENT_FORMATS
    },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } },

    JalaliPipe,
    PaymentTypePip,
    ThousandPip,
    YesNoPipe,
    ResultTypePip,
    PursuitTypePip,
    MyBasketGuardService,
    SearchGuardService,
    DocumentManageGuardService,
    ReportGuardService,
    ToolsGuardService,
    UserManageGuardService,
    DocumentInputGuardService,
    PaymentService,
    ContractService,
    PursuitService,
    DocumentCacheService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
