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

}
