import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../providers/login-service';
import {LoginAuthService} from '../providers/auth-service';
import {Router, ActivatedRoute} from '@angular/router';
import {ConfirmationService} from 'primeng/primeng';

import {AuthService} from 'angularx-social-login';
import {SocialUser} from 'angularx-social-login';
import {GoogleLoginProvider, FacebookLoginProvider, LinkedInLoginProvider} from 'angularx-social-login';

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
  signuptab = false;
  logintab = true;

  ngOnInit() {
    this.service.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'dashboard';
  }

  constructor(
    private authService: LoginAuthService,
    private route: ActivatedRoute,
    private router: Router,
    private service: LoginService,
    private confirmationService: ConfirmationService,
    private socialAuthService: AuthService) {
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

  signInWithFB(): void {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(
      (userData) => {
        console.log(' sign in data : ', userData);
        const email = userData.email;
        this.service.checkemail(email).subscribe(
          data => {
            if (data.status === 200) {
              const user = data.json();
              console.log(user);
              this.userLogin(user);
            } else {
              this.signuptab = false;
              this.logintab = true;
              this.register.email = userData.email;
              this.register.firstName = userData.firstName;
              this.register.lastName = userData.lastName;
              this.signuptab = true;
              this.logintab = false;
            }
          },
          err => {
            this.signuptab = false;
            this.logintab = true;
            this.register.email = userData.email;
            this.register.firstName = userData.firstName;
            this.register.lastName = userData.lastName;
            this.signuptab = true;
            this.logintab = false;
          });
      }
    );
  }

  signInWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      (userData) => {
        console.log(' sign in data : ', userData);
        const email = userData.email;
        this.service.checkemail(email).subscribe(
          data => {
            if (data.status === 200) {
              const user = data.json();
              console.log(user);
              this.userLogin(user);
            } else {
              this.signuptab = false;
              this.logintab = true;
              this.register.email = userData.email;
              this.register.firstName = userData.firstName;
              this.register.lastName = userData.lastName;
              this.signuptab = true;
              this.logintab = false;
            }
          },
          err => {
            this.signuptab = false;
            this.logintab = true;
            this.register.email = userData.email;
            this.register.firstName = userData.firstName;
            this.register.lastName = userData.lastName;
            this.signuptab = true;
            this.logintab = false;
          });
      }
    );
  }

  signInWithLinkedIn(): void {
    this.socialAuthService.signIn(LinkedInLoginProvider.PROVIDER_ID).then(
      (userData) => {
        console.log(' sign in data : ', userData);
        const email = userData.email;
        this.service.checkemail(email).subscribe(
          data => {
            if (data.status === 200) {
              const user = data.json();
              console.log(user);
              this.userLogin(user);
            } else {
              this.signuptab = false;
              this.logintab = true;
              this.register.email = userData.email;
              this.register.firstName = userData.firstName;
              this.register.lastName = userData.lastName;
              this.signuptab = true;
              this.logintab = false;
            }
          },
          err => {
            this.signuptab = false;
            this.logintab = true;
            this.register.email = userData.email;
            this.register.firstName = userData.firstName;
            this.register.lastName = userData.lastName;
            this.signuptab = true;
            this.logintab = false;
          });
      }
    );
  }

  signOut(): void {
    this.socialAuthService.signOut();
  }
}
