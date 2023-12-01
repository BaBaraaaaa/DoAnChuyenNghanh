import { of } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  user :any;
  checkRoleAmdin: boolean = false;
  constructor(private login: LoginService){}
  ngOnInit(): void {
  if(this.login.checkRole())
  {
    this.checkRoleAmdin = true;
  }

    console.log(this.checkRoleAmdin);
  }
  title = 'front_end_DoAnChuyenNghanh';


}
