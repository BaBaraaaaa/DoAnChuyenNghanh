import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { count } from 'rxjs';
import { AppService } from 'src/app/services/app.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  products : Array<any> = new Array;
  user : any;
  checkRole:boolean = this.loginserv.checkRole();
  checkUser : boolean = false;
  constructor(private app: AppService ,private loginserv: LoginService){}

  ngOnInit(): void {
    console.log(this.checkRole);

    this.user = this.loginserv.checkLogin();
    if(this.user != null)
    {
      this.checkUser = true;
    }
    console.log(this.checkUser);
    this.app.getAllProduct().subscribe((res: any)=>{
      console.log(res);
      this.products = res
      console.log(this.products);
      });




  };
  public onAddtoCart(id: number)
  {
    let data = {
      id : id,
      user_id :this.user.id
    }

    this.app.addProductToCart(data).subscribe((res: any)=>
    {

        console.log(res.text);
        alert(res);


    },(err: HttpErrorResponse)=>
    {
      if(err.status == 200)
      {
        console.log(err);
        alert("thêm vào giỏ hàng thành công");


      }
      console.error(err);
    })
  }


}
