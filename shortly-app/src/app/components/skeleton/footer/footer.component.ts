import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  socialMedias = [{name: 'facebook', is_active:false}, {name:'twitter',is_active:false}, {name: 'pinterest', is_active:false}, {name:'instagram', is_active:false}];
  links = [
    {title:'features',
    sublinks:[
      {name: 'link shortening',
        url: ''},
      {name: 'Branded Links',
        url: ''},
      {name: 'Analytics',
        url: ''}
    ]},
    {title:'Resources',
      sublinks:[
        {
          name:"blog",
          url: ''
        },
        {name:'developers',
        url: ''},
        {name:'support',
        url: ''}
      ]},
    {title:'company',
      sublinks:[{
      name: 'About', url: ''
      },
        {name: 'our team', url:''},
        {name: 'career', url:''},
        {name: 'contact', url: ''}

      ]}
  ]

  constructor() { }

  ngOnInit(): void {
  }

  enableLink(link:any){
    link.is_active = true
  }
  disableLink(link:any){
    link.is_active = false
  }

}
