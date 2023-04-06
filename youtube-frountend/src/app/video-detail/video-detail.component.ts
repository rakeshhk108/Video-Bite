import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {VideoUploadService} from "../Service/video-upload.service";
import {UserService} from "../Service/user.service";

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
  likeCount: number = 0;
  disLikeCount: number= 0;
  viewCount: number = 0;
  dislikeCount: number = 0;
  subscribed: boolean = false;
  subscribeText: string = "Subscribe";
  panelOpenState: boolean = false;



  constructor(private activatedRoute : ActivatedRoute, private videoService: VideoUploadService, private userService: UserService) {
    this.activatedRoute.paramMap.subscribe(params => {
      this.videoId = params.get('videoId');
    });

    this.videoService.getVideo(this.videoId).subscribe(
      (response) => {
        console.log(response);
        this.videoUrl = response.videoUrl;
        this.videoDescription = response.description;
        this.videoTitle = response.title;
        this.tags = response.tags;
        this.videoAvailable = true;
        this.likeCount = response.likeCount;
        this.disLikeCount = response.disLikeCount;
        this.viewCount = response.viewCount;
      }
    )
  }

  likeVideo() {
      this.videoService.likeVideo(this.videoId).subscribe((response) =>
        {
          this.likeCount = response.likeCount;
          this.disLikeCount = response.disLikeCount;
          console.log(response.disLikeCount);
        }
      )
  }

  disLikeVideo() {
    this.videoService.disLikeVideo(this.videoId).subscribe((response) =>
      {
        this.likeCount = response.likeCount;
        this.disLikeCount = response.disLikeCount;
      }
    )
  }

  subscribeToUser() {
      this.subscribed = !this.subscribed;

      if(!this.subscribed){
          let userId = this.userService.getUserId();
          this.videoService.subscribe(userId).subscribe(response =>
          {
            console.log(response);
          })
      }
  }
}
