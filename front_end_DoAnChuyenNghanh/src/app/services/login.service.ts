import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
const api = 'http://localhost:8080/api/v1/auth';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private user : any
  constructor(private http: HttpClient) { }

  login(body : any): any
  {
    const headers = new HttpHeaders();
    headers.set(
      'Access-Control-Allow-Origin','*'
    );

    return this.http.post<any>(`${api}/login`,body,{headers});

  }
  checkLogin() :any {

    let jsonData = sessionStorage.getItem('user');
    if(jsonData)
    {
      return JSON.parse(jsonData);
    }
      return false;
  }
  checkRole():boolean
  {
    let checkrole = this.checkLogin();
    if(checkrole)
    {
      for(var a of checkrole.roles)
      {
        if(a.name =="Admin")
        {
          return true;
        }
      }

    }
    return false;
  }

}
