import {Routes, RouterModule} from '@angular/router';

import {LoginComponent} from './login/login.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProfileComponent} from './profile/profile.component';
import {TransactionsComponent} from './transactions/transactions.component';
import {MonthlytransactionComponent} from './monthlytransaction/monthlytransaction.component';
import {InvoiceComponent} from './invoice/invoice.component';
import {CustomtransactionComponent} from './customtransaction/customtransaction.component';
import {CategoriesComponent} from './categories/categories.component';
import {ViewcategoryComponent} from './viewcategory/viewcategory.component';

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
    path: 'getmonthlytransactions/:monthYear', component: MonthlytransactionComponent, canActivate: [AuthGuard]
  },
  {
    path: 'getmonthlyinvoice/:monthYear', component: InvoiceComponent, canActivate: [AuthGuard]
  },
  {
    path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]
  },
  {
    path: 'categories', component: CategoriesComponent, canActivate: [AuthGuard]
  },
  {
    path: 'viewcategory/:category', component: ViewcategoryComponent, canActivate: [AuthGuard]
  },
  {
    path: '**', redirectTo: 'login'
  }
];

export const routing = RouterModule.forRoot(appRoutes);
