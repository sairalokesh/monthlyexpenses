import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';

@Injectable()
export class LoginAuthService {
  private subject = new Subject<any>();

  isLoggedIn(type: string) {
    if (localStorage.getItem('currentUser')) {
      this.subject.next({status: 'true', active: type});
    } else {
      this.subject.next({status: 'false', active: ''});
    }

  }

  clearStatus() {
    this.subject.next();
  }

  getStatus(): Observable<any> {
    return this.subject.asObservable();
  }

  constructor() {
  }

}
