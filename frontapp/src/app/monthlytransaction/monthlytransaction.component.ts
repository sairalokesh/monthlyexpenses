import {Component, ElementRef, NgZone, OnInit, ViewChild} from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';
import {TransactionService} from '../providers/transaction-service';

import {Router, ActivatedRoute} from '@angular/router';
import {} from 'googlemaps';
import {MapsAPILoader} from '@agm/core';

@Component({
  selector: 'app-monthlytransaction',
  templateUrl: './monthlytransaction.component.html',
  styleUrls: ['./monthlytransaction.component.css']
})
export class MonthlytransactionComponent implements OnInit {

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
  
  
  public doughnutChartOptions: any = {
    responsive: true
  };
  public doughnutChartType = 'doughnut';
  public doughnutChartData = {};
  
  public transaction = {};
  public monthYear = '';
  public activityStartDate: Date;

  public isUpdate = false;

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
  public hzoom: any;

  public latitude: any;
  public longitude: any;

  public lat: any;
  public lng: any;

  @ViewChild('search')
  public searchElementRef: ElementRef;

  constructor(
    private authService: LoginAuthService,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private transactionService: TransactionService,
    private router: Router,
    private route: ActivatedRoute,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
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

    this.hzoom = 12;
    this.lat = 12.9716;
    this.lng = 77.5946;

    this.getSelectedMonthly();
    this.getMonthlyTransactions();
    this.getMonthlytransactionsCount();
    this.getmonthlyransactionsGraph();

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
          console.log(this.transactions);
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
    const address = ((document.getElementById('location') as HTMLInputElement).value);
    transaction.location = address;
    transaction.latitude = this.latitude;
    transaction.longitude = this.longitude;
    this.transactionService.saveMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
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
          this.latitude = trans.latitude;
          this.longitude = trans.longitude;
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
    const address = ((document.getElementById('location') as HTMLInputElement).value);
    transaction.location = address;
    transaction.latitude = this.latitude;
    transaction.longitude = this.longitude;
    this.transactionService.updateMonthlyTransaction(transaction).subscribe(
      data => {
        if (data.status === 200) {
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
