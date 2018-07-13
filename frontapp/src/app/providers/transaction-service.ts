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
  
  /* Start Transaction Tab Functionality */

  getAllTransactions(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/transactions', user, options)
      .map((response: Response) => {
        return response;
      });
  }

  saveTransaction(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/create', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }
  
   /* End Transaction Tab Functionality */

  /* Start Monthly Transactions Tab Functionality */
  
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
  
  saveMonthlyTransaction(newproduct: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/monthlycreate', newproduct, options)
      .map((response: Response) => {
        return response;
      });
  }
  
   editMonthlyTransaction(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/editMonthlyTransaction', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }
  
    updateMonthlyTransaction(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/updateMonthlyTransaction', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }

  deleteMonthlyTransaction(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./transaction/deleteMonthlyTransaction', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }
  
  /* End Monthly Transaction Tab Functionality */
  
  /* Start  Categories Tab Functionality */
  
    getAllTransactionsByCategory(user: any, category: any) {
 	const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedCategory/' + category, user, options)
      .map((response: Response) => {
        return response;
     });
  }
  
  getCategoryTransactionsByMonth(user: any, category: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getCategoryTransactions/' + category+'/'+monthyear, user, options)
    .map((response: Response) => {
        return response;
     });
  }
  
  getMonthyearcategorytransactionsLineGraph(user: any, category: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthcategorytransactionsCount/' + category+'/'+monthyear, user, options)
    .map((response: Response) => {
        return response;
     });
  }
  
  
  getSelectedCategoryMonthly(user: any, category: any, monthyear: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedCategoryMonthly/' + category+'/'+monthyear, user, options)
    .map((response: Response) => {
        return response;
     });
  }
  
  
  
  
  
  
  


  getSelectedTransactions(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedTransactions', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }

  
  
  


  generateInvoice(user: any, monthyear: any, invoiceNumber: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/generateInvoice/' + monthyear + '/' + invoiceNumber, user, options)
      .map((response: Response) => {
        return response;
      });
  }

  getRangeTransactions(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getRangeTransactions', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }

  

  getRangetransactionsCount(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getRangetransactionsCount', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }

  


  getRangeTransactionsGraph(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getRangeTransactionsGraph', transaction, options)
      .map((response: Response) => {
        return response;
      });
  }

  generateRangeInvoice(transaction: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/generateRangeInvoice', transaction, options)
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

  getCurrentMonthlyMinMaxDates(user: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getCurrentMonthlyMinMaxDates', user, options)
      .map((response: Response) => {
        return response;
      });
  }


  getSelectedCategory(user: any, category: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getSelectedCategory/' + category, user, options)
      .map((response: Response) => {
        return response;
      });
  }


  getMonthyearcategorytransactionsCount(user: any, category: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getMonthyearcategorytransactionsCount/' + category, user, options)
      .map((response: Response) => {
        return response;
      });
  }


  getyearcategorytransactionsCount(user: any, category: any) {
    const headers = new Headers({'Authorization': 'Bearer ' + this.token});
    const options = new RequestOptions({headers: headers});
    return this.http.post('./statistic/getyearcategorytransactionsCount/' + category, user, options)
      .map((response: Response) => {
        return response;
      });
  }
  
   /* Category Tab Functionality Starts */

  
  
  
  




}
