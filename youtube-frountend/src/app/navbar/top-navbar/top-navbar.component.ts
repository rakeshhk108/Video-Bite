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
  menuStatus = false;
  private isLogin: boolean = false;


  sideNavToggle(){
    this.menuStatus = !this.menuStatus;
    this.sideNavToggled.emit(this.menuStatus);
  }

  login(){
    this.isLogin = !this.isLogin;
    this.isLoginStatus.emit(this.isLogin);
  }
}
