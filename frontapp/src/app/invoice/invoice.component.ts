import {Component, OnInit} from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

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
  public statistics: any = {};

  constructor(
    private authService: LoginAuthService,
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
      'type': ''
    };

  }

  ngOnInit() {
    this.getSelectedMonthlyMinMaxDates();
    this.getSelectedMonthly();
    this.getMonthlyTransactions();
  }

  getSelectedMonthlyMinMaxDates() {
    this.transactionService.getSelectedMonthlyMinMaxDates(this.loginuser, this.monthYear).subscribe(
      data => {
        if (data.status === 200) {
          this.statistics = data.json();
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

  printInvoice(printSectionId: string) {
    let popupWinindow;
    const innerContents = document.getElementById(printSectionId).innerHTML;
    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWinindow.document.open();
    popupWinindow.document.write('<html><head>' +
      '<link href="assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css"></head><body onload="window.print()">' +
      innerContents + '</html>');
    popupWinindow.document.close();
  }

  generateInvoice(invoiceNumber: any) {
    this.transactionService.generateInvoice(this.loginuser, this.monthYear, invoiceNumber).subscribe(
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
