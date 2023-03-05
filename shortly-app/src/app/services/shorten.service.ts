import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpService} from "./wrapper/http.service";
import {link} from "../interfaces/link";

@Injectable({
  providedIn: 'root'
})
export class ShortenService {
  links:Array<link> = []
  userdata:any = {};
  loginErrMsg = "";
  signupErrMsg = "";
  errMsg ="";
  signingIn = false;
  signingUp = false;
  shortening = false
  isSuccess = false;
  constructor(private httpService: HttpService, private router:Router) { }

  getShortened(link:string){

    this.httpService.post(link, {}).then((res:any) => {
      this.links = res.data;
    });
  }
  shorten(url:string){
    this.shortening = true;
    this.httpService.post("shorten", {url, email:this.userdata?.email ? this.userdata.email : ''}).then((res:any)=>{
      if(res && res.code){
        this.links.push(res);
        localStorage.setItem("shortenedList", JSON.stringify(this.links));
      }else{
        this.errMsg = res?.message ? res.message : "Error trying to save your account, please try again later"

      }
    }).finally(()=>{
      this.shortening = false;
    })

  }

  login(params:object){
      this.signingIn = true;
      this.httpService.post("user/login", params).then((res:any)=>{
         if(res && res.erc == 1){
           this.userdata = res.data;
           this.links = res.data.urls
           localStorage.setItem("shortenedList", JSON.stringify(this.links));
           this.router.navigateByUrl('/home')
         } else{
           
           this.loginErrMsg = res?.message ? res.message : "Error trying to login"
         }
      }).catch((error:any)=>{
                       this.loginErrMsg = error?.msg ? error.msg : "Error trying to login"

      })
      .finally(()=>{
            this.signingIn = false;
      })
    

  }
  signup(params:object){
    this.isSuccess = false;
      this.signingUp = true;
      return this.httpService.post("user/signup", params).then((res:any)=>{
         if(res && res.erc == 1){
           this.isSuccess = true
           this.signupErrMsg = "Congratulations!, Your account has been saved successfully"

         } else{
           this.signupErrMsg = res?.message ? res.message : "Error trying to save your account, please try again later"

         }
        console.log(res);
      }).catch((error:any)=>{
              this.signupErrMsg = error?.msg ? error.msg : "Error trying to save your account, please try again later"

      }).finally(()=>{
              this.signingUp = false;
      })
    }


  
  getLinks() {
    let data = localStorage.getItem("shortenedList");
    this.links =  data ? JSON.parse(data) : [];
  }

}
