import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/services/app.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit{
  user: any;
  checkUser : boolean = false;
  products: any;
  prodInCate  : any;
  category : any;
  constructor(private login: LoginService , private app: AppService){}
  ngOnInit(): void {
    this.user = this.login.checkLogin();
    if(this.user != null)
    {
      this.checkUser = true;
    }
    console.log(this.checkUser);
    this.app.getAllCategory().subscribe((res: any)=>
    {
      this.category = res;
    })
    this.app.getAllProduct().subscribe((res: any)=>{

      this.products = res;
    console.log(this.products);


      });




  };
  onClick(id: any)
  {
    console.log(id);
  this.prodInCate = [];
      for(var b of this.products)
      {
        if(b.categoryId == id)
        {
          console.log(b.categoryId);

          this.prodInCate.push(b); // Thêm sản phẩm vào mảng this.prodInCate

          console.log(this.prodInCate);


        }
      }


  }
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
