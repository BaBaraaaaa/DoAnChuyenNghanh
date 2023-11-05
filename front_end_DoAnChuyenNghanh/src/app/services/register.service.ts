import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
const api = 'http://localhost:8080/api/v1/';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }
  register(body : any): any
  {
    return this.http.post<any>(`${api}user`,body);
  }

}
