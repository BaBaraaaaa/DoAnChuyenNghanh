import { count, of } from 'rxjs';
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
  TongGia: number = 0;
  sl: number = 0;;
  constructor(private app: AppService ,private login : LoginService){}
  ngOnInit(): void {
    this.user = this.login.checkLogin();
    let user_id =   this.user.id;
    this.app.getProductInCartByUser_id(user_id).subscribe((res:any)=>
    {
      this.product = res;
    // console.log(this.product);
    })
    console.log()
    this.app.getCartByUserId(user_id).subscribe((res:object)=>
    {
      this.cart = res;
      // console.log();

      // console.log(typeof(this.cart));
      // console.log(this.cart);
      // console.log(this.cart[1].count);
    for(var a of this.cart)
    {

    for(var b of this.product)
    {
      if(a.product_id == b.id)
      {
        this.TongGia += b.price * a.count; // Tính tổng giá
        this.sl += a.count; // Tính tổng số lượng

      }

      console.log(this.sl);
    }
    }
    console.log(this.TongGia  );

    })


      // console.log(typeof(this.cart.length));



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
  oncountCart()
  {



  }
  onChange(event : any ,item : any): any
  {
    const newCount = +event.target.value;
    item.count = newCount;
    item.totalPrice = item.price *newCount;



  }



}
