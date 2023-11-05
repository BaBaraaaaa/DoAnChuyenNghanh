import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from    '@angular/common/http';
const api = 'http://localhost:8080/api/v1/';
@Injectable(
    {
        providedIn: 'root'
    }
)
export class AppService
{

    constructor(private http: HttpClient){}

    public getAllProduct()
    {
      // console.log( this.http.get(`${api}product`));
        return this.http.get(`${api}product`);
    }
    public addProductToCart( data :any)
    {
      return this.http.get(`${api}cart/${data.id}/${data.user_id}`);
    }
    public getProductInCartByUser_id(user_id:any)
    {
      return this.http.get(`${api}cart/${user_id}`);
    }
    public getCartByUserId()
    {

    }
    public deleteProductInCartByUser_Id(data: any)
    {
      return this.http.delete(`${api}cart/${data.id}/${data.user_id}`);
    }

}
