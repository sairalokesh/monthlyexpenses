import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class LoginService {

  constructor(public http: Http) {
    this.http = http;
  }

  userLogin(user: any) {
    return this.http.post('./login', user)
      .map((response: Response) => {
        const loginuser = response.json();
        if (loginuser && loginuser.token) {
          localStorage.setItem('currentUser', JSON.stringify(loginuser));
        }
        return loginuser;
      });
  }

  userSignup(user: any) {
    return this.http.post('./registerUser', user)
      .map((response: Response) => {
        return response;
      });
  }

  sendEmail(user: any) {
    return this.http.post('./forgotpassword', user)
      .map((response: Response) => {
        const useremail = response.json();
        return useremail;
      });
  }

  checkemail(email: any) {
    return this.http.get('./checkemail/' + email)
      .map((response: Response) => {
        return  response;
      });
  }

  logout() {
    localStorage.removeItem('currentUser');
  }
}
