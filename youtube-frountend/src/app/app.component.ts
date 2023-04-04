import {Component, OnInit} from '@angular/core';
import {LoginResponse, OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'youtube-frountend';
  isNavOpen: boolean;

  constructor(public oidcSecurityService: OidcSecurityService) {
    this.oidcSecurityService.getAccessToken().subscribe(at => {
      console.log(at);
    });
  }

  ngOnInit() {
    this.oidcSecurityService.checkAuth()
      .subscribe(({ isAuthenticated}) =>{
      console.log("app is authenticated" , isAuthenticated);

    });
  }
}
