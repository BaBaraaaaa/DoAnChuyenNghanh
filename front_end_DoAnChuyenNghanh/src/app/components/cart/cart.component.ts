import { LoginComponent } from './../login/login.component';
import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/services/app.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent  implements OnInit{
  user : any;
  product : any;
  cart: any;
  constructor(private app: AppService ,private login : LoginService){}
  ngOnInit(): void {
    this.user = this.login.checkLogin();
    let user_id =   this.user.id;
    this.app.getProductInCartByUser_id(user_id).subscribe((res:any)=>
    {
      this.product = res;
    console.log(this.product);
    })

  }
  public onDeleteProductInCart(id :  number)
  {
  let data = {
    id : id,
    user_id : this.user.id
  }
  this.app.deleteProductInCartByUser_Id(data).subscribe((res: any)=>
  {
    alert("đã xóa thành công ");
  })
  }

  // onCart():any
  // {
  //   // let user_id = this.user.id;
  //   this.app.getProductInCartByUser_id(user_id).subscribe((res:any)=>
  //   {
  //   console.log(user_id);
  //   })
  // }

}
