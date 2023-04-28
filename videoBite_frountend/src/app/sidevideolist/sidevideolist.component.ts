import {Component, EventEmitter, Input, Output} from '@angular/core';
import { Router } from '@angular/router';
import {VideoDetails} from "../model/videoDetails";
import {VideoUploadService} from "../Service/video-upload.service";

@Component({
  selector: 'app-sidevideolist',
  templateUrl: './sidevideolist.component.html',
  styleUrls: ['./sidevideolist.component.css']
})
export class SidevideolistComponent {

  @Input() videoId : String;
  @Output() playVideo = new EventEmitter<string>();
  featuredVideos : Array<VideoDetails> = [];

  errorimage : string = "./assets/images/youtube-logo-png-2067.png";

  constructor(private videoService : VideoUploadService, private router: Router) {
    this.videoService.getAllVideos().subscribe((response)=>
      {
        this.featuredVideos = response;
      },
      (error) => {
        console.log(error);
      }

    );
  }

  videoLoad(videId : string) {
    this.videoId = videId;
  
    this.router.navigateByUrl("/video-details/" + this.videoId);
  }
}
