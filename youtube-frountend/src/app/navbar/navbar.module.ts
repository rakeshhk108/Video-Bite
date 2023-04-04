import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavbarRoutingModule } from './navbar-routing.module';
import { NavbarComponent } from './navbar.component';
import { TopNavbarComponent } from './top-navbar/top-navbar.component';
import {SideNavbarComponent} from "./side-navbar/side-navbar.component";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    NavbarComponent,
    TopNavbarComponent,
    SideNavbarComponent
  ],
  exports: [
    NavbarComponent
  ],
    imports: [
        CommonModule,
        NavbarRoutingModule,
        MatButtonModule
    ]
})
export class NavbarModule { }
