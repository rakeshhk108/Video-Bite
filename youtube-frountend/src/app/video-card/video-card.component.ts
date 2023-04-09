import { Component, Input } from '@angular/core';
import { VideoDetails } from '../model/videoDetails';
import {Router} from "@angular/router";
import {OidcSecurityService} from "angular-auth-oidc-client";

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.css']
})
export class VideoCardComponent {



  @Input() displayVideos: VideoDetails;

  constructor(private router: Router, private oidcSecurityService : OidcSecurityService ) {
  }

  ShowVideo() {

    this.oidcSecurityService.checkAuth()
      .subscribe(({ isAuthenticated }) => {
        if (isAuthenticated) {
          this.router.navigateByUrl("video-details/"+ this.displayVideos.id);
        }
        else
        {
          this.router.navigateByUrl("login-request");
        }
      });

  }
}
