import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class TransactionService {

  public token = '';

  constructor(public http: Http) {
    this.http = http;
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  getAllTransactions(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/transactions', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  saveTransaction(newproduct: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/create', newproduct, options)
      .map((response: Response) => {
        const savedTransaction = response.json();
        return savedTransaction;
      });
  }


  getSelectedMonthly(user: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedMonthly/' + monthyear, user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getMonthlyTransactions(user: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthlyTransactions/' + monthyear, user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getMonthlytransactionsCount(user: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthlytransactionsCount/' + monthyear, user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getmonthlyransactionsGraph(user: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getmonthlyransactionsGraph/' + monthyear, user, options)
      .map((response: Response) => {
        return response;
      });
  }


  getSelectedMonthlyMinMaxDates(user: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedMonthlyMinMaxDates/' + monthyear, user, options)
      .map((response: Response) => {
        return response;
      });
  }







}
