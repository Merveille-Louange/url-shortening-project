import { Component } from '@angular/core';
import {UtilitiesService} from "./services/utilities/utilities.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'shortly-app';

  constructor(public utils: UtilitiesService) {


  }

}
