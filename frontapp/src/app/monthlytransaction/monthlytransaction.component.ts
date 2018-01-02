import {Component, OnInit} from '@angular/core';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';
import {HelperService} from '../providers/helper-service';

import {Router} from '@angular/router';

@Component({
  selector: 'app-monthlytransaction',
  templateUrl: './monthlytransaction.component.html',
  styleUrls: ['./monthlytransaction.component.css']
})
export class MonthlytransactionComponent implements OnInit {

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

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private helperService: HelperService,
    private router: Router) {
    this.authService.isLoggedIn('transactionmanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
    this.monthYear = this.helperService.monthyear;
    this.transaction = {
      'type': ''
    };

  }

  ngOnInit() {
    this.getSelectedMonthly();
    this.getMonthlyTransactions();
    this.getMonthlytransactionsCount();
    this.getmonthlyransactionsGraph();
  }


  getSelectedMonthly() {
    this.transactionService.getSelectedMonthly(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          this.monthlystatistic = data.json();
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }


  getMonthlyTransactions() {
    this.transactionService.getMonthlyTransactions(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          this.transactions = data.json();
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }

  getMonthlytransactionsCount() {
    this.transactionService.getMonthlytransactionsCount(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          console.log(data.json());
          const monthlydata = data.json();

          this.lineChartData = {
            labels: monthlydata.monthYear,
            datasets: monthlydata.dataDTO
          };

        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }


  getmonthlyransactionsGraph() {
    this.transactionService.getmonthlyransactionsGraph(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          console.log(data.json());
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
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }

  saveMonthlyTransaction(transaction: any) {
    transaction.user = this.loginuser;
    transaction.monthYear = this.monthYear;
    console.log(transaction);
    this.transactionService.saveMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          this.msgs = [];
          this.errorMessage = '';
          this.successMessage = 'Transaction is saved successfully!';
          this.transaction = {
            'type': ''
          };

          this.getSelectedMonthly();
          this.getMonthlyTransactions();
          this.getMonthlytransactionsCount();
          this.getmonthlyransactionsGraph();

        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }

  editTransaction(transaction: any) {
    this.transactionService.editMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          const trans = data.json();
          this.activityStartDate = new Date(trans.dbTransactionDate);
          trans.transactionDate = this.activityStartDate;
          this.transaction = trans;
          this.isUpdate = true;
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });

  }

  updateMonthlyTransaction(transaction: any) {
    transaction.user = this.loginuser;
    transaction.monthYear = this.monthYear;
    console.log(transaction);
    this.transactionService.updateMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          this.msgs = [];
          this.errorMessage = '';
          this.successMessage = 'Transaction is updated successfully!';
          this.transaction = {
            'type': ''
          };

          this.getSelectedMonthly();
          this.getMonthlyTransactions();
          this.getMonthlytransactionsCount();
          this.getmonthlyransactionsGraph();
          this.isUpdate = false;

        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });

  }

  deleteTransaction(transaction: any) {
    this.confirmationService.confirm({
      message: 'Do you want to delete this record?',
      header: 'Delete Transaction Confirmation',
      icon: 'fa fa-trash',
      accept: () => {
        this.transactionService.deleteMonthlyTransaction(transaction).subscribe(
          data => {
            if (data.status === 200) {
              this.msgs = [];
              this.errorMessage = '';
              this.successMessage = 'Transaction is deleted successfully!';
              this.transaction = {
                'type': ''
              };

              this.getSelectedMonthly();
              this.getMonthlyTransactions();
              this.getMonthlytransactionsCount();
              this.getmonthlyransactionsGraph();
              this.isUpdate = false;

            } else {
              const error = data.json();
              this.successMessage = '';
              this.errorMessage = error.message;
            }

          },
          err => {
            const error = err.json();
            this.successMessage = '';
            this.errorMessage = error.message;
          });
      }
    });
  }

}
