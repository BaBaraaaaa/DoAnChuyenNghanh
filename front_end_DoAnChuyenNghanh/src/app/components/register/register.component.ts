import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class  RegisterComponent   {
  registerForm :FormGroup = new FormGroup({
    user_name : new FormControl('',[Validators.required]),
    password :new FormControl('',[Validators.required]),
    email : new FormControl('',[Validators.required],),

    full_name : new FormControl('',[Validators.required]),
    phone_number : new FormControl('',[Validators.required])



  });

  checkPassword: boolean = true;
  constructor (private register: RegisterService,private login: LoginService){}
  body : object =  Object ({
    user_name : this.registerForm.get('user_name')?.value
  })



  onSubmit() :void {
    if (this.registerForm.invalid) {
      console.log('Form is invalid', this.registerForm.value);
      console.log('Form is invalid', this.registerForm.invalid);

      return;
    }


    console.log(this.registerForm.value)
  this.register.register(this.registerForm.value).subscribe((res: any)=>
  {
    if (res == null) {
          alert("lỗi");
        } else if (res != null) {
          console.log(res);
          alert("đăng ký thành công!");

          let jsonData = JSON.stringify(res);
          sessionStorage.setItem('user',jsonData);
          location.assign('http://localhost:4200/')
        }

  },(error: HttpErrorResponse) =>
      {
        console.error('Error:', error.error);
        alert(error.statusText)

      }
      );
    // this.register.register(this.body).subscribe
    // ((res: any)=>
    // {
    //   if (res == null) {
    //     alert("lỗi");
    //   } else if (res != null) {
    //     console.log(res);
    //     alert("đăng ký thành công!");
    //     let jsonData = JSON.stringify(res);
    //     sessionStorage.setItem('user',jsonData);
    //     location.assign('http://localhost:4200/')

    //   }
    // },(error: HttpErrorResponse) => {
    //   {
    //     console.error('Error:', error.error);
    //     alert(error.statusText)
    //   }


    // });
  }


}
