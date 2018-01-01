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

  minDate: Date;
  maxDate: Date;


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

    const year = parseInt(this.monthYear.split('-')[0]);
    const month = parseInt(this.monthYear.split('-')[1]);

    this.minDate = new Date();
    this.minDate.setMonth(month);
    this.minDate.setFullYear(year);
    this.maxDate = new Date();
    this.maxDate.setMonth(month + 1);
    this.maxDate.setFullYear(year);


    this.transactionService.getSelectedMonthlyMinMaxDates(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          const minmax = data.json();

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

}
