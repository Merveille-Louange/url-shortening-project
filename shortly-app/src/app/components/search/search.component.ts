import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ShortenService} from "../../services/shorten.service";
import {link} from "../../interfaces/link";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
heroForm: FormGroup = new FormGroup({});

  constructor(public shortenService:ShortenService) {
  }

  ngOnInit(): void {
  this.heroForm = new FormGroup({
    link: new FormControl("", [
      Validators.required]),
  });
    this.shortenService.getLinks();

}

get link() { return this.heroForm.get('link'); }

  submitLink(){
    if(this.heroForm.invalid)
      return
    this.shortenService.shorten(this.link?.value)

  }
  showCopyText(link:link){
    link.copied = true;
    setTimeout(()=>{
      link.copied =false
    }, 5000)
  }
}


