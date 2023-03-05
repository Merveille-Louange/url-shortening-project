import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilitiesService {
  showMobileMenuDetail = false;
  toast = {msg:"", title:"", type:""}
  constructor() { }
}
