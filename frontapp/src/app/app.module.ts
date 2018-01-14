import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {MenubarModule, MenuItem} from 'primeng/primeng';
import {DataTableModule, SharedModule} from 'primeng/primeng';
import {DialogModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {MessagesModule} from 'primeng/primeng';
import {ConfirmDialogModule, ConfirmationService} from 'primeng/primeng';
import {InputTextareaModule} from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';
import {PanelModule} from 'primeng/primeng';
import {CalendarModule} from 'primeng/primeng';

import {routing} from './app.routing';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {TransactionsComponent} from './transactions/transactions.component';
import {ProfileComponent} from './profile/profile.component';

import {LoginService} from './providers/login-service';
import {AuthService} from './providers/auth-service';
import {UserService} from './providers/user-service';
import {TransactionService} from './providers/transaction-service';


import {AuthGuard} from './guards/auth.guard';

import {ChartsModule} from 'ng2-charts';
import {ChartModule} from 'angular2-chartjs';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import {SignaturePadModule} from 'angular2-signaturepad';
import {MonthlytransactionComponent} from './monthlytransaction/monthlytransaction.component';
import {InvoiceComponent} from './invoice/invoice.component';
import {CustomtransactionComponent} from './customtransaction/customtransaction.component';

import {AgmCoreModule} from '@agm/core';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ProfileComponent,
    TransactionsComponent,
    MonthlytransactionComponent,
    InvoiceComponent,
    CustomtransactionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MenubarModule,
    DataTableModule,
    DialogModule,
    InputTextModule,
    ButtonModule,
    MessagesModule,
    InputTextareaModule,
    DropdownModule,
    ConfirmDialogModule,
    BrowserAnimationsModule,
    routing,
    ChartsModule,
    SignaturePadModule,
    PanelModule,
    CalendarModule,
    ChartModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAjUHpiDhHJwK0vCMayeOTvEB08RXI1YCg',
      libraries: ['places']
    })
  ],
  providers: [
    AuthGuard,
    LoginService,
    AuthService,
    UserService,
    ConfirmationService,
    TransactionService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
