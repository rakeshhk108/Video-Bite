import { Component } from '@angular/core';
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-login-request',
  templateUrl: './login-request.component.html',
  styleUrls: ['./login-request.component.css']
})
export class LoginRequestComponent {

  constructor(private oidcSecurityService: OidcSecurityService ) {
  }
  login() {
    this.oidcSecurityService.authorize();
  }
}
