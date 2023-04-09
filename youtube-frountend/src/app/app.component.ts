import {Component, OnInit} from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";
import {UserService} from "./Service/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'youtube-frountend';
  isNavOpen: boolean;

  constructor(private oidcSecurityService: OidcSecurityService, private userService : UserService) {

  }

  ngOnInit(): void {
    this.oidcSecurityService.checkAuth()
      .subscribe(({ isAuthenticated }) => {
        console.log('app is authenticated', isAuthenticated);
        if (isAuthenticated) {
          this.userService.registerUser();
        }
      });
  }

}
