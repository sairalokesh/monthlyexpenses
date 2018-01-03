import {Routes, RouterModule} from '@angular/router';

import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProfileComponent} from './profile/profile.component';
import {TransactionsComponent} from './transactions/transactions.component';
import {MonthlytransactionComponent} from './monthlytransaction/monthlytransaction.component';
import {InvoiceComponent} from './invoice/invoice.component';
import {CustomtransactionComponent} from './customtransaction/customtransaction.component';

import {AuthGuard} from './guards/auth.guard';

const appRoutes: Routes = [
  {
    path: '', component: LoginComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]
  },
  {
    path: 'transactions', component: TransactionsComponent, canActivate: [AuthGuard]
  },
  {
    path: 'searchtransactions', component: CustomtransactionComponent, canActivate: [AuthGuard]
  },
  {
    path: 'getmonthlytransactions', component: MonthlytransactionComponent, canActivate: [AuthGuard]
  },
  {
    path: 'getmonthlyinvoice', component: InvoiceComponent, canActivate: [AuthGuard]
  },
  {
    path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]
  },
  {
    path: '**', redirectTo: 'login'
  }
];

export const routing = RouterModule.forRoot(appRoutes);
