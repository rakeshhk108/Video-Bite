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
      name : 'Home',
      icon : 'fa-solid fa-house',
      link : '/home'
    },
    {
      number: 2,
      name : 'Subscribed',
      icon : 'fa-solid fa-play',
      link : '/subscribed'
      
    },
    {
      number: 3,
      name : 'History',
      icon : 'fa fa-history',
      link: '/history'
    },
    {
      number: 4,
      name : 'Liked Videos',
      icon : 'fa fa-thumbs-up',
      link : "/likedVideos"
    },
    {
      number: 5,
      name : 'Settings',
      icon : 'fa-solid fa-gear',
      link : '/home'
    },
    {
      number: 7,
      name : 'Contact',
      icon : 'fa-solid fa-phone',
      link : '/home'
    },
    {
      number: 6,
      name : 'About',
      icon : 'fa-solid fa-circle-info',
      link : '/home'
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
