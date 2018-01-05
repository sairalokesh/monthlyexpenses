import {Component, OnInit} from '@angular/core';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router} from '@angular/router';

@Component({
  selector: 'app-customtransaction',
  templateUrl: './customtransaction.component.html',
  styleUrls: ['./customtransaction.component.css']
})
export class CustomtransactionComponent implements OnInit {

  users: any[];
  submitAttempt = false;
  errorMessage: any = '';
  successMessage: any = '';
  user: any = {};
  displayDialog = false;
  public adduser = true;
  public msgs: any = [];
  public loginuser: any = {};
  public transactions: any = [];
  public monthlystatistic: any = {};
  public lineChartOptions: any = {
    responsive: true
  };

  public lineChartType = 'line';
  public lineChartData = {};
  public doughnutChartOptions: any = {
    responsive: true
  };

  public doughnutChartType = 'doughnut';
  public doughnutChartData = {};
  public transaction = {};
  public monthYear = '';
  public activityStartDate: Date;

  public isUpdate = false;
  public searchtransaction = {};

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router) {
    this.authService.isLoggedIn('searchtransactionmanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
    this.transaction = {
      'type': ''
    };
  }

  ngOnInit() {
    this.transactionService.getCurrentMonthlyMinMaxDates(this.loginuser).subscribe(
      data => {
        if (data.status === 200) {
          const statics = data.json();
          statics.startDate = new Date(statics.dbStartDate);
          statics.endDate = new Date(statics.dbEndDate);
          this.searchtransaction = statics;
          this.getSelectedTransactions(statics);
          this.getRangeTransactions(statics);
          this.getRangetransactionsCount(statics);
          this.getRangeTransactionsGraph(statics);
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }
      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
    // this.getSelectedMonthly();
    // this.getMonthlyTransactions();
    // this.getMonthlytransactionsCount();
    // this.getmonthlyransactionsGraph();
  }

  searchTransaction(statics: any) {
    this.searchtransaction = statics;
    this.getSelectedTransactions(statics);
    this.getRangeTransactions(statics);
    this.getRangetransactionsCount(statics);
    this.getRangeTransactionsGraph(statics);
  }


  getSelectedTransactions(statics: any) {
    statics.user = this.loginuser;
    this.transactionService.getSelectedTransactions(statics).subscribe(
      data => {
        if (data.status === 200) {
          this.monthlystatistic = data.json();
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
  }


  getRangeTransactions(statics: any) {
    statics.user = this.loginuser;
    this.transactionService.getRangeTransactions(statics).subscribe(
      data => {
        if (data.status === 200) {
          this.transactions = data.json();
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
  }

  getRangetransactionsCount(statics: any) {
    statics.user = this.loginuser;
    this.transactionService.getRangetransactionsCount(statics).subscribe(
      data => {
        if (data.status === 200) {
          const monthlydata = data.json();
          this.lineChartData = {
            labels: monthlydata.monthYear,
            datasets: monthlydata.dataDTO
          };

        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
  }


  getRangeTransactionsGraph(statics: any) {
    statics.user = this.loginuser;
    this.transactionService.getRangeTransactionsGraph(statics).subscribe(
      data => {
        if (data.status === 200) {
          const graphdata = data.json();

          this.doughnutChartData = {
            labels: graphdata.labels,
            datasets: [{
              backgroundColor: graphdata.color,
              data: graphdata.data
            }]
          };

        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
  }

  generateRangeInvoice(statics: any) {
    statics.user = this.loginuser;
    this.transactionService.generateRangeInvoice(statics).subscribe(
      data => {
        if (data.status === 200) {
          window.open('./resources/reports/Invoice.pdf');
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
        setTimeout(() => {
          this.successMessage = '';
          this.errorMessage = '';
        }, 2000);
      });
  }

}
