import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router, ActivatedRoute} from '@angular/router';
import {} from 'googlemaps';
import {MapsAPILoader} from '@agm/core';

@Component({
  selector: 'app-viewcategory',
  templateUrl: './viewcategory.component.html',
  styleUrls: ['./viewcategory.component.css']
})
export class ViewcategoryComponent implements OnInit {


  errorMessage: any = '';
  successMessage: any = '';

  public loginuser: any = {};
  public transactions: any = [];
  public monthlystatistic: any = {};
  
  
  public lineChartOptions: any = {
    responsive: true
  };

  public lineChartType = 'line';
  public lineChartData = {};
  
  
  
  public transaction = {};
  public monthYear = '';
  public category = '';
  public activityStartDate: Date;

  public isUpdate = false;

  public zoom: any;
  public hzoom: any;

  public latitude: any;
  public longitude: any;

  public lat: any;
  public lng: any;


  constructor(
    private authService: LoginAuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router,
    private route: ActivatedRoute,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
    this.authService.isLoggedIn('categoriesmanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
    this.monthYear = route.snapshot.params['monthYear'];
    this.category = route.snapshot.params['category'];
  }

  ngOnInit() {

    this.hzoom = 12;
    this.lat = 12.9716;
    this.lng = 77.5946;

    this.getSelectedMonthly();
    this.getMonthlyTransactions();
    this.getMonthlytransactionsCount();

    this.setCurrentPosition();
   
  }
  
  getMonthlyTransactions() {
    this.transactionService.getCategoryTransactionsByMonth(this.loginuser,this.category, this.monthYear).subscribe(
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
    this.transactionService.getMonthyearcategorytransactionsLineGraph(this.loginuser,this.category, this.monthYear).subscribe(
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
  
  getSelectedMonthly() {
    this.transactionService.getSelectedCategoryMonthly(this.loginuser,this.category, this.monthYear).subscribe(
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
