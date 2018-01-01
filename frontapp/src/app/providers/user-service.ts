import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {

  public token: string;

  constructor(public http: Http) {
    this.http = http;
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  updateUser(updateuser: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./user/update', updateuser, options)
      .map((response: Response) => {
        return response;
      });
  }


  getMonthly(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthly', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getYearly(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getYearly', user, options)
      .map((response: Response) => {
        return response;
      });
  }


  yearlyTransactionsCount(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getYearlyTransaction', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  monthlyransactionsCount(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthlyTransaction', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  monthlyransactionsGraph(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthlyChart', user, options)
      .map((response: Response) => {
        return response;
      });
  }



  getWeekly(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getWeekly', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getToday(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getToday', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  todayTransactions(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getTodayTransactions', user, options)
      .map((response: Response) => {
        return response;
      });
  }


  pushFileToStorage(file: File, userId: any) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    formdata.append('userId', userId);
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./user/userprofile', formdata, options)
      .map((response: Response) => {
        return response;
      });
  }

  pushBackgroundFileToStorage(file: File, userId: any) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    formdata.append('userId', userId);
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./user/userbackground', formdata, options)
      .map((response: Response) => {
        return response;
      });
  }

  uploadusersignature(formdata: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./user/usersignature', formdata, options)
      .map((response: Response) => {
        return response;
      });
  }
}
