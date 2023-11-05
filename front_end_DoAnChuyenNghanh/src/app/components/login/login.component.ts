import { HttpErrorResponse, HttpClient } from '@angular/common/http';

import { LoginService } from './../../services/login.service';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'src/app/services/app.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

   loginF: FormGroup = new FormGroup(
  {
    userName: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required])
  }

);
constructor(private loginser :LoginService, private route: Router, private app :AppService)
{}

onSubmit(): void {
  if (this.loginF.invalid) {
    console.log('Form is invalid', this.loginF.value);
    return;
  }
  sessionStorage.clear();
  this.loginser.login(this.loginF.value).subscribe(
    (res: object) => {
      if (res == null) {
        alert("lỗi");
      } else if (res != null) {
        // console.log(res);
        alert("đăng nhập thành công!");
        let jsonData = JSON.stringify(res);
        sessionStorage.setItem('user',jsonData);
        location.assign('http://localhost:4200/')

      }

    },
    (    error: HttpErrorResponse) => {
      if(error.status == 400)
      {
        console.error('Error:', error.error);
        alert(error.error)
      }


    }
  );

}


}
