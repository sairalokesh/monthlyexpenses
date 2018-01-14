import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router} from '@angular/router';
import {} from 'googlemaps';
import {MapsAPILoader} from '@agm/core';


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

  public zoom: any;

  public latitude: any;
  public longitude: any;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
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
    this.setCurrentPosition();
    this.mapsAPILoader.load().then(() => {
      const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: [],
        componentRestrictions: {country: 'IN'}
      });
      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          const place: google.maps.places.PlaceResult = autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          console.log(this.latitude);
          console.log(this.longitude);
          this.zoom = 20;
        });
      });
    });
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

  saveTransaction(transaction: any) {
    transaction.user = this.loginuser;
    const address = ((document.getElementById('location') as HTMLInputElement).value);
    transaction.location = address;
    transaction.latitude = this.latitude;
    transaction.longitude = this.longitude;
    this.transactionService.saveTransaction(transaction).subscribe(
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
          this.getAllTransactions();

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

  getMothlyTransactions(monthYear: any) {
    this.router.navigate(['getmonthlytransactions', monthYear]);
  }

  getMothlyInvoice(monthYear: any) {
    this.router.navigate(['getmonthlyinvoice', monthYear]);
  }

  private setCurrentPosition() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 20;
      });
    }
  }

}
