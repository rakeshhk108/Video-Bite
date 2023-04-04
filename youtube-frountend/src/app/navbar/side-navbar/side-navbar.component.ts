import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-side-navbar',
  templateUrl: './side-navbar.component.html',
  styleUrls: ['./side-navbar.component.css']
})
export class SideNavbarComponent {

  @Input() sidenavList=[] as any;
  @Input() navstatus : boolean = false;
}
