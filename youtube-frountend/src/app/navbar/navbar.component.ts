import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
   sidenavstatus : boolean = false;

   isAuthenticated : boolean = false;

   isLogin : boolean = false;

   @Output() isNavOpen = new EventEmitter<boolean>();


  constructor(private oidcSecurityService: OidcSecurityService) {
  }

  ngOnInit():void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated})=>{
      this.isAuthenticated = isAuthenticated;
    })
  }

  list = [
    {
      number: 1,
      name : 'home',
      icon : 'fa-solid fa-house'
    },
    {
      number: 2,
      name : 'Analytics',
      icon : 'fa-solid fa-chart-line'
    },
    {
      number: 3,
      name : 'Product',
      icon : 'fa-solid fa-box'
    },
    {
      number: 4,
      name : 'Order',
      icon : 'fa-solid fa-cart-shopping'
    },
    {
      number: 5,
      name : 'Settings',
      icon : 'fa-solid fa-gear'
    },
    {
      number: 6,
      name : 'About',
      icon : 'fa-solid fa-circle-info'
    },
    {
      number: 7,
      name : 'Contact',
      icon : 'fa-solid fa-phone'
    }



  ]

  login(isLogin : boolean)
  {
    if(isLogin)
    {
      this.oidcSecurityService.authorize()
    }
  }

  logOff(){
    this.oidcSecurityService.logoffAndRevokeTokens();
  }


  sidenavevent(isSidenavOpen : boolean) {
    this.sidenavstatus = isSidenavOpen;
    this.isNavOpen.emit(isSidenavOpen);
  }
}
