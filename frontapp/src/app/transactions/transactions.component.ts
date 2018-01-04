import {Component, OnInit} from '@angular/core';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';
import {HelperService} from '../providers/helper-service';

import {Router} from '@angular/router';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  users: any[];
  submitAttempt = false;
  errorMessage: any = '';
  successMessage: any = '';
  user: any = {};
  displayDialog = false;
  public adduser = true;
  public msgs: any = [];
  public loginuser: any = {};
  public transaction = {};

  public transactions: any = [];
  public categories: any = ['Salary', 'Rent', 'Food', 'Car', 'Eating Out', 'Transport',
    'Toiletry', 'Entertainment', 'Sports', 'Taxi', 'Health', 'Clothes', 'Communications',
    'Gifts', 'Pets', 'Bills', 'Apparels', 'Culture', 'Social Life', 'Loans', 'Education', 'Shopping', 'Fuel'];

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
    this.transaction = {
      'type': '',
      'category': ''
    };
  }

  ngOnInit() {
    this.getAllTransactions();
  }

  getAllTransactions() {
    this.transactionService.getAllTransactions(this.loginuser).subscribe(
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

  saveTransaction(transaction: any) {
    transaction.user = this.loginuser;
    this.transactionService.saveTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
          this.msgs = [];
          this.errorMessage = '';
          this.successMessage = 'Transaction is saved successfully!';
          this.transaction = {
            'type': '',
            'category': ''
          };
          this.getAllTransactions();

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

  getMothlyTransactions(monthYear: any) {
    this.helperService.monthyear = monthYear;
    this.router.navigate(['getmonthlytransactions']);
  }

  getMothlyInvoice(monthYear: any) {
    this.helperService.monthyear = monthYear;
    this.router.navigate(['getmonthlyinvoice']);
  }

}
