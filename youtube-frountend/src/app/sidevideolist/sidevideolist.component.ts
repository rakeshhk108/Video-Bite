import {Component, EventEmitter, Input, Output} from '@angular/core';
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

  constructor(private videoService : VideoUploadService) {
    this.videoService.getAllVideos().subscribe((response)=>
      {
        this.featuredVideos = response;
      },
      (error) => {
        console.log(error);
      }

    );
  }

  videoLoad(videoId: string) {
    this.playVideo.emit(videoId);
  }
}
