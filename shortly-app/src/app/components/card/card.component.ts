import {Component, Input, OnInit} from '@angular/core';
import {card} from "../../interfaces/card";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
@Input() cardData:card = { title:"", description:"", imageUrl:""}
  card:card = { title:"", description:"", imageUrl:""}
  constructor() { }

  ngOnInit(): void {
  }

}
