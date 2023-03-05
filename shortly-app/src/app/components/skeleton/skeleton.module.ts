import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavComponent} from "./nav/nav.component";
import {FooterComponent} from "./footer/footer.component";
import { RouterModule, Routes } from '@angular/router';
import { ToastComponent } from './toast/toast.component';



@NgModule({
  declarations: [NavComponent, FooterComponent, ToastComponent],
  exports: [NavComponent, FooterComponent, ToastComponent],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class SkeletonModule { }
