import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-top-navbar',
  templateUrl: './top-navbar.component.html',
  styleUrls: ['./top-navbar.component.css']
})
export class TopNavbarComponent {

  @Output() sideNavToggled = new EventEmitter<boolean>();
  @Input("isAuthenticated") isAuthenticated : boolean;
  @Output() isLoginStatus = new EventEmitter<boolean>();
  @Output() isLogoutStatus = new EventEmitter<boolean>();
  menuStatus = false;
  public isLogin: boolean = false;
  public isLogout: boolean = false;


  sideNavToggle(){
    this.menuStatus = !this.menuStatus;
    this.sideNavToggled.emit(this.menuStatus);
  }

  login(){
    this.isLogin = !this.isLogin;
    this.isLoginStatus.emit(this.isLogin);
  }

  logout() {
    this.isLogin = ! this.isLogin;
    this.isLogoutStatus.emit(this.isLogout);
  }
}
