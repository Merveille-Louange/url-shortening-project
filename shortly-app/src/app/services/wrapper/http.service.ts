import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiBase = 'http://localhost:8080/';

  constructor(private http:HttpClient) {}

  post(url:string, params:object ={}){
    url = this.apiBase + url;

    return this.http.post(url, params).toPromise().catch(err => {
  console.log(err);
});
  }
  get(url:string, params:object ={}){
    const u = this.parseParams(params);
    console.log(u)
    url = this.apiBase + url + '?' + u;
    return this.http.get(url).toPromise().catch(err => {
  console.log(err);
});
  }
  parseParams(params:any){
    let res = ""
    if (params){
      const keys = Object.keys(params);
      keys.forEach((item:string)=>{
        res = res.concat(item).concat("=").concat(params[item].toString()).concat('&')
      })
    }
    return res;
  }
}
