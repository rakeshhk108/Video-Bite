import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoUploadService} from "../Service/video-upload.service";

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent {

  videoId: string;
  videoUrl: string;
  videoTitle: string;
  videoDescription: string;
  tags: Array<string> = [];
  videoAvailable : boolean = false;


  constructor(private activatedRoute : ActivatedRoute, private videoService: VideoUploadService ) {
    this.activatedRoute.paramMap.subscribe(params => {
      this.videoId = params.get('videoId');
    });

    this.videoService.getVideo(this.videoId).subscribe(
      (response) => {
        this.videoUrl = response.videoUrl;
        this.videoDescription = response.description;
        this.videoTitle = response.title;
        this.tags = response.tags;
        this.videoAvailable = true;
      }
    )
  }

}
