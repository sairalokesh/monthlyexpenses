import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router} from '@angular/router';
import {} from 'googlemaps';
import {MapsAPILoader} from '@agm/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

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
  public categories: any = ['Agriculture', 'Apparels', 'Bills',
    'Clothes', 'Communications', 'CreditCards',
    'Culture', 'Education', 'Entertainment',
    'Food', 'Fruits', 'Fuel',
    'Gas', 'Gifts', 'Groceries',
    'Hospital', 'Loans', 'Meats',
    'Medicines', 'Milk', 'Pets',
    'Purchases', 'Rent', 'Salary',
    'Shopping', 'SocialLife', 'Sports',
    'Taxi', 'Toiletry', 'Transport', 'Vegetables'];

  public zoom: any;

  public latitude: any;
  public longitude: any;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(
    private authService: LoginAuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
    this.authService.isLoggedIn('categoriesmanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
    this.transaction = {
      'type': '',
      'category': ''
    };
  }

  ngOnInit() {

  }

  viewcategory(category: any) {
    this.router.navigate(['viewcategory', category]);
  }
}
