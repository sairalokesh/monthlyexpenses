import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../providers/login-service';
import {AuthService} from '../providers/auth-service';
import {Router, ActivatedRoute} from '@angular/router';
import {ConfirmationService} from 'primeng/primeng';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage: any = '';
  successMessage: any = '';
  errorMessage1: any = '';
  successMessage1: any = '';
  errorMessage2: any = '';
  successMessage2: any = '';
  public user: any = {};
  public token = '';
  public userData: any;
  returnUrl: string;
  public currentuser: boolean;
  displayDialog = false;
  login: any = {};
  register: any = {};

  ngOnInit() {
    this.service.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'dashboard';
  }

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private service: LoginService,
    private confirmationService: ConfirmationService) {
    this.authService.isLoggedIn('');

  }

  userSignup(user: any) {
    this.errorMessage2 = '';
    this.successMessage2 = '';
    this.service.userSignup(user).subscribe(
      data => {
        if (data.status === 200) {
          this.userLogin(user);
        } else {
          const error = data.json();
          this.successMessage2 = '';
          this.errorMessage2 = error.message;
          setTimeout(() => {
            this.successMessage2 = '';
            this.errorMessage2 = '';
          }, 2000);
        }

      },
      err => {
        const error = err.json();
        this.successMessage2 = '';
        this.errorMessage2 = error.message;
        setTimeout(() => {
          this.successMessage2 = '';
          this.errorMessage2 = '';
        }, 2000);
      });
  }

  userLogin(user: any) {
    this.errorMessage = '';
    this.successMessage = '';
    this.service.userLogin(user).subscribe(
      data => {
        if (data.token) {
          this.router.navigate([this.returnUrl]);
        } else {
          this.successMessage = '';
          this.errorMessage = 'Please Enter valid email & Password';
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


  sendEmail(user: any) {
    this.service.sendEmail(user).subscribe(
      data => {
        if (data.status === '404') {
          this.successMessage1 = '';
          this.errorMessage1 = data.message;
          setTimeout(() => {
            this.successMessage1 = '';
            this.errorMessage1 = '';
          }, 2000);
        } else {
          this.errorMessage1 = '';
          this.successMessage1 = data.message;
          setTimeout(() => {
            this.successMessage1 = '';
            this.errorMessage1 = '';
          }, 2000);
        }
      },
      err => {
        this.successMessage1 = '';
        this.errorMessage1 = 'Enter valid email & Password';
        setTimeout(() => {
          this.successMessage1 = '';
          this.errorMessage1 = '';
        }, 2000);
      });
  }
}
