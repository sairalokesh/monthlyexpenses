import {Component, OnInit } from '@angular/core';
import {LoginAuthService} from '../providers/auth-service';
import {TransactionService} from '../providers/transaction-service';

import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-monthyearcategory',
  templateUrl: './monthyearcategory.component.html',
  styleUrls: ['./monthyearcategory.component.css']
})
export class MonthyearcategoryComponent implements OnInit {

 
  public loginuser: any = {};
  public category = '';
  public transactions: any = [];

  constructor(
    private authService: LoginAuthService,
    private transactionService: TransactionService,
    private router: Router,
     private route: ActivatedRoute) {
    this.authService.isLoggedIn('categoriesmanagement');
    this.category = route.snapshot.params['category'];
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
 
  }

  ngOnInit() {
    this.getAllTransactions();
  }

  getAllTransactions() {
    this.transactionService.getAllTransactionsByCategory(this.loginuser, this.category).subscribe(
      data => {
        if (data.status === 200) {
          this.transactions = data.json();
        }

      },
      err => {
     
      });
  }
  
  getMothlyCategoryTransactions(monthYear: any){
  	this.router.navigate(['viewcategory',this.category, monthYear]);
  
  }  
}
