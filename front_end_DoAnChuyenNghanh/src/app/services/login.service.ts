import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
const api = 'http://localhost:8080/api/v1/auth/';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private user : any
  constructor(private http: HttpClient) { }

  login(body : any): any
  {
    return this.http.post<any>(`${api}login`,body);
  }
  checkLogin() :any {

    let jsonData = sessionStorage.getItem('user');
    if(jsonData)
    {
      return JSON.parse(jsonData);
    }
      return false;
  }

}
