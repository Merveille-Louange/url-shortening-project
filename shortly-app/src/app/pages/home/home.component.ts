import { Component, OnInit } from '@angular/core';
import {card} from "../../interfaces/card";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  cards:Array<card> = [
    {title:"Brand Recognition",
    description: "boost your brand with each click. Generic links don't mean a thing. Branded links help instill confidence in your product",
    imageUrl:"assets/images/icon-brand-recognition.svg"},
    {title:"Detailed Records",
      description: "Get insight into who is clicking your link. Knowing when and where people engage with your content helps engage better decisions",
      imageUrl:"assets/images/icon-detailed-records.svg"},
    {title:"Fully customizable",
      description: "Improve brand awareness and content discoverability through customizable links, supercharging audience engagement.",
      imageUrl:"assets/images/icon-fully-customizable.svg"}
  ]


  constructor() { }

  ngOnInit(): void {
  }

}
