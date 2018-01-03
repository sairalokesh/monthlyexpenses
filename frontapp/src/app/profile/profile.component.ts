import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpResponse, HttpEventType} from '@angular/common/http';
import {AuthService} from '../providers/auth-service';
import {UserService} from '../providers/user-service';
import {SignaturePad} from 'angular2-signaturepad/signature-pad';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public loginuser: any = {};
  errorMessage: any = '';
  successMessage: any = '';

  selectedFiles: FileList;
  currentFileUpload: File;

  selectedBackgroundFiles: FileList;
  currentBackgroundFileUpload: File;

  @ViewChild(SignaturePad) signaturePad: SignaturePad;

  constructor(private authService: AuthService, private service: UserService) {
    this.authService.isLoggedIn('profilemanagement');
    const dbuser = JSON.parse(localStorage.getItem('currentUser'));
    this.loginuser = dbuser.user;
  }

  updateuser(user: any) {
    this.service.updateUser(user).subscribe(
      data => {
        if (data.status === 200) {
          const dbuser = JSON.parse(localStorage.getItem('currentUser'));
          const updateuser = data.json();
          dbuser.user = updateuser;
          this.errorMessage = '';
          this.successMessage = 'User Profile updated successfully';
          localStorage.setItem('currentUser', JSON.stringify(dbuser));
          this.loginuser = updateuser;
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }

  selectFile(event, userId) {
    this.selectedFiles = event.target.files;
    this.currentFileUpload = this.selectedFiles.item(0);
    this.service.pushFileToStorage(this.currentFileUpload, userId).subscribe(
      data => {
        if (data.status === 200) {
          const dbuser = JSON.parse(localStorage.getItem('currentUser'));
          const updateuser = data.json();
          dbuser.user = updateuser;
          this.errorMessage = '';
          this.successMessage = 'User Profile updated successfully';
          localStorage.setItem('currentUser', JSON.stringify(dbuser));
          setTimeout(() => {
            this.loginuser.userProfileName = updateuser.userProfileName;
          }, 200);
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }
      });

    this.selectedFiles = undefined;
  }


  selectBackgroundFile(event, userId) {
    this.selectedBackgroundFiles = event.target.files;
    this.currentBackgroundFileUpload = this.selectedBackgroundFiles.item(0);
    this.service.pushBackgroundFileToStorage(this.currentBackgroundFileUpload, userId).subscribe(
      data => {
        if (data.status === 200) {
          const dbuser = JSON.parse(localStorage.getItem('currentUser'));
          const updateuser = data.json();
          dbuser.user = updateuser;
          this.errorMessage = '';
          this.successMessage = 'User Profile updated successfully';
          localStorage.setItem('currentUser', JSON.stringify(dbuser));
          setTimeout(() => {
            this.loginuser.backgroundImageName = updateuser.backgroundImageName;
          }, 200);
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }
      });

    this.selectedBackgroundFiles = undefined;
  }


  private signaturePadOptions: Object = {
    'minWidth': 2,
    'penColor': 'rgb(66, 133, 244)',
    'backgroundColor': 'rgb(255,255,255)',
    'canvasWidth': 450,
    'canvasHeight': 150
  };

  ngAfterViewInit() {
    this.signaturePad.set('minWidth', 2);
    this.signaturePad.clear();
  }

  drawComplete() {
    const blob = this.dataURItoBlob(this.signaturePad.toDataURL('image/png', 0.5));
    this.errorMessage = '';
    this.successMessage = '';
    console.log(blob);
    const currentuser = JSON.parse(localStorage.getItem('currentUser'));
    const fd = new FormData(document.forms[0]);
    fd.append('file', blob, currentuser.user.firstName + '_' + currentuser.user.id + '.png');
    fd.append('userId', currentuser.user.id);
    this.service.uploadusersignature(fd).subscribe(
      data => {
        if (data.status === 200) {
          const updateuser = data.json();
          const dbcurrent = JSON.parse(localStorage.getItem('currentUser'));
          dbcurrent.user = updateuser;
          localStorage.setItem('currentUser', JSON.stringify(dbcurrent));
          this.errorMessage = '';
          this.successMessage = 'User Signature updated';
          setTimeout(() => {
            this.loginuser.signatureName = updateuser.signatureName;
          }, 200);
        } else {
          const error = data.json();
          this.successMessage = '';
          this.errorMessage = error.message;
        }

      },
      err => {
        const error = err.json();
        this.successMessage = '';
        this.errorMessage = error.message;
      });
  }

  drawClear() {
    this.signaturePad.clear();
  }


  dataURItoBlob(dataURI) {
    let byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
      byteString = atob(dataURI.split(',')[1]);
    else
      byteString = atob(dataURI.split(',')[1]);

    const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    const ia = new Uint8Array(byteString.length);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ia], {type: mimeString});
  }

  ngOnInit() {
  }

}
