import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { LoginService } from 'src/app/services/login.service';
import { AppService } from 'src/app/services/app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  hidden = false;
  isLogined : boolean = false;
  user : any;
  checkRole:boolean = this.loginSrv.checkRole();
  constructor( private loginSrv: LoginService , private route : Router,private app: AppService){}
  ngOnInit(): void {
    if(this.loginSrv.checkLogin())
    {
      this.isLogined = true;
      this.user = this.loginSrv.checkLogin();

      console.log(this.user)
    }

    console.log(this.isLogined);
    console.log(this.checkRole);
  }

  onLogout()
  {
    sessionStorage.clear();
    location.reload();
    this.isLogined = false;

  }
  onBackHome(): any
  {
   this.route.navigate(['/']);
  }
  toggleBadgeVisibility() {
    this.hidden = !this.hidden;
  }


}
