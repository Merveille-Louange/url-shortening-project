import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import {ComponentModule} from "../../components/component/component.module";
import {RouterModule, Routes} from "@angular/router";

const routes : Routes = [
  {
    path:"", component:HomeComponent
  }
]

@NgModule({
  declarations: [
    HomeComponent
  ],
    imports: [
        CommonModule,
        ComponentModule,
      RouterModule.forChild(routes)
    ]
})
export class HomeModule { }
