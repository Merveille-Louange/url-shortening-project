import { Component, OnInit } from '@angular/core';
import {Navigation} from "../../../interfaces/Navigation";
import {ActivatedRoute} from "@angular/router";
import {UtilitiesService} from "../../../services/utilities/utilities.service";
import {ShortenService} from "../../../services/shorten.service";


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {
  navigationArray: Array<Navigation> = [
    {
      name:"home",
      link:"/home"
    },
    {
      name:"pricing",
      link:"/pricing"
    },
    {
      name:"resources",
      link:"/resource"
    }
  ]

  constructor(private route:ActivatedRoute, public utils:UtilitiesService, public shortenService:ShortenService){

  }


  toggleDisplayDivIf() {
    this.utils.showMobileMenuDetail = !this.utils.showMobileMenuDetail;
  }

  isNavActive(nav:Navigation){
    console.log(this.route.url)
  }

}
