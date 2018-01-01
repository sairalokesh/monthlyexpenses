import {Component, OnInit} from '@angular/core';
import {MenubarModule, MenuItem} from 'primeng/primeng';
import {Router, ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {AuthService} from './providers/auth-service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  public currentuser: any;
  public subscription: Subscription;
  ngOnInit() {

  }
  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {
    this.currentuser = this.authService.getStatus().subscribe(currentuser => {
      this.currentuser = currentuser;
    });
  }


  logout() {
    localStorage.removeItem('currentUser');
    this.authService.isLoggedIn('');
    this.router.navigate(['login']);
  }

  myFunction() {
    const x = document.getElementById('myTopnav');
    if (x.className === 'topnav') {
      x.className += ' responsive';
    } else {
      x.className = 'topnav';
    }
  }





}
