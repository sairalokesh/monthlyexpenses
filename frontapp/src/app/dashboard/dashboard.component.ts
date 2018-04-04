import {Component, OnInit} from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {ConfirmationService} from 'primeng/primeng';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  users: any[];
  submitAttempt = false;
  errorMessage: any = '';
  successMessage: any = '';
  user: any = {};
  displayDialog = false;
  public adduser = true;
  public msgs: any = [];
  public loginuser: any = {};

  public monthlystatistic: any = {};
  public yearlystatistic: any = {};
  public weeklystatistic: any = {};
  public todaystatistic: any = {};
  public transactions: any = [];


  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public barChartType = 'bar';
  public barChartData = {};

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

  constructor(private authService: LoginAuthService, private userService: UserService, private confirmationService: ConfirmationService) {
    this.authService.isLoggedIn('usermanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
  }

  ngOnInit() {
    this.userService.getMonthly(this.loginuser).subscribe(
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

    this.userService.getYearly(this.loginuser).subscribe(
      data => {
        if (data.status === 200) {
          this.yearlystatistic = data.json();
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

    this.userService.getWeekly(this.loginuser).subscribe(
      data => {
        if (data.status === 200) {
          this.weeklystatistic = data.json();
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

    this.userService.getToday(this.loginuser).subscribe(
      data => {
        if (data.status === 200) {
          this.todaystatistic = data.json();
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

    this.userService.todayTransactions(this.loginuser).subscribe(
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


    this.userService.yearlyTransactionsCount(this.loginuser).subscribe(
      data => {
        if (data.status === 200) {
          console.log(data.json());
          const yearlydata = data.json();

          this.barChartData = {
            labels: yearlydata.monthYear,
            datasets: yearlydata.dataDTO
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

    this.userService.monthlyransactionsCount(this.loginuser).subscribe(
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


    this.userService.monthlyransactionsGraph(this.loginuser).subscribe(
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

