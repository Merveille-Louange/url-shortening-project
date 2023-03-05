import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ShortenService} from "../../services/shorten.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  signUpForm: FormGroup;
  constructor(public shortenService: ShortenService) {
    this.signUpForm = new FormGroup({
      name: new FormControl("", [
        Validators.required,
        Validators.minLength(4),
      ]),
      email: new FormControl("", [
        Validators.required,
        Validators.minLength(4),
      ]),
      confirmPassword: new FormControl("", [
        Validators.required,
        Validators.minLength(4),
      ]),
      password: new FormControl("", [Validators.required, Validators.minLength(4)]),
    });
  }

  ngOnInit(): void {
  }
  get name() { return this.signUpForm.get('name'); }
  get email() { return this.signUpForm.get('email'); }
  get password() { return this.signUpForm.get('password'); }
  get confirmPassword() { return this.signUpForm.get('confirmPassword'); }

  register(){
    // console.log(this.loginForm);
    if (this.signUpForm.invalid || this.shortenService.signingUp){
      return
    }
    this.shortenService.signup(this.signUpForm.value).then((res : any)=>{
      this.signUpForm.reset();
    });
  }

}
