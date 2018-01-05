import {Component, OnInit} from '@angular/core';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router, ActivatedRoute} from '@angular/router';

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

  public categories: any = ['Salary', 'Rent', 'Food', 'Car', 'Eating Out', 'Transport',
    'Toiletry', 'Entertainment', 'Sports', 'Taxi', 'Health', 'Clothes', 'Communications',
    'Gifts', 'Pets', 'Bills', 'Apparels', 'Culture', 'Social Life', 'Loans', 'Education', 'Shopping', 'Fuel'];

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router,
    private route: ActivatedRoute) {
    this.authService.isLoggedIn('transactionmanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
    this.monthYear = route.snapshot.params['monthYear'];
    this.transaction = {
      'type': '',
      'category': ''
    };
    window.scrollTo(0, 0);
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


  getMonthlyTransactions() {
    this.transactionService.getMonthlyTransactions(this.loginuser, this.monthYear).subscribe(
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

  getMonthlytransactionsCount() {
    this.transactionService.getMonthlytransactionsCount(this.loginuser, this.monthYear).subscribe(
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


  getmonthlyransactionsGraph() {
    this.transactionService.getmonthlyransactionsGraph(this.loginuser, this.monthYear).subscribe(
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

  saveMonthlyTransaction(transaction: any) {
    transaction.user = this.loginuser;
    transaction.monthYear = this.monthYear;
    this.transactionService.saveMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          this.msgs = [];
          this.errorMessage = '';
          this.successMessage = 'Transaction is saved successfully!';
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
          this.transaction = {
            'type': '',
            'category': ''
          };

          this.getSelectedMonthly();
          this.getMonthlyTransactions();
          this.getMonthlytransactionsCount();
          this.getmonthlyransactionsGraph();

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

  editTransaction(transaction: any) {
    this.transactionService.editMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          const trans = data.json();
          this.activityStartDate = new Date(trans.dbTransactionDate);
          trans.transactionDate = this.activityStartDate;
          this.transaction = trans;
          this.isUpdate = true;
          window.scrollTo(0, 420);
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

  updateMonthlyTransaction(transaction: any) {
    transaction.user = this.loginuser;
    transaction.monthYear = this.monthYear;
    this.transactionService.updateMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          this.msgs = [];
          this.errorMessage = '';
          this.successMessage = 'Transaction is updated successfully!';
          setTimeout(() => {
            this.successMessage = '';
            this.errorMessage = '';
          }, 2000);
          this.transaction = {
            'type': '',
            'category': ''
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

  deleteTransaction(transaction: any) {
    this.confirmationService.confirm({
      message: 'Do you want to delete this transaction?',
      header: 'Delete Transaction Confirmation',
      icon: 'fa fa-trash',
      accept: () => {
        this.transactionService.deleteMonthlyTransaction(transaction).subscribe(
          data => {
            if (data.status === 200) {
              this.msgs = [];
              this.errorMessage = '';
              this.successMessage = 'Transaction is deleted successfully!';
              setTimeout(() => {
                this.successMessage = '';
                this.errorMessage = '';
              }, 2000);
              this.transaction = {
                'type': '',
                'category': ''
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
    });
  }

}
