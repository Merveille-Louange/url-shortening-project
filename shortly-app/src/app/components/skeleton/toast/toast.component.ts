import { Component, OnInit } from '@angular/core';
import {UtilitiesService} from "../../../services/utilities/utilities.service";


@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss']
})
export class ToastComponent implements OnInit {

  constructor(public utils:UtilitiesService) { }

  ngOnInit(): void {
  }

}
