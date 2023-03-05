import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ShortenService} from "../../services/shorten.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  constructor(public shortenService: ShortenService) {
    this.loginForm = new FormGroup({
      email: new FormControl("", [
        Validators.required,
        Validators.minLength(4),
      ]),
      password: new FormControl("", [Validators.required, Validators.minLength(4)]),
    });
  }

  ngOnInit(): void {

  }
  login(){
    // console.log(this.loginForm);
    if (this.loginForm.invalid || this.shortenService.signingIn){
        return
    }
    this.shortenService.login(this.loginForm.value);
  }
}
