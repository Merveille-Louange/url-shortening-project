import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CardComponent} from "../card/card.component";
import {SearchComponent} from "../search/search.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BannerComponent} from "../banner/banner.component";
import {ClipboardModule} from "ngx-clipboard";



@NgModule({
  declarations: [CardComponent, SearchComponent, BannerComponent],
  exports: [CardComponent, SearchComponent, BannerComponent],
  imports: [
    CommonModule,
    FormsModule,
    ClipboardModule,
    ReactiveFormsModule
  ]
})
export class ComponentModule { }
