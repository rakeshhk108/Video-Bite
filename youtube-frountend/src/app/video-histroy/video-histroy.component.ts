import {Component, OnInit} from '@angular/core';
import {VideoDetails} from "../model/videoDetails";
import {VideoUploadService} from "../Service/video-upload.service";
import {UserService} from "../Service/user.service";

@Component({
  selector: 'app-video-histroy',
  templateUrl: './video-histroy.component.html',
  styleUrls: ['./video-histroy.component.css']
})
export class VideoHistroyComponent implements OnInit{


  featuredVideos : Array<VideoDetails> = [];

  constructor(private userService : UserService){

  }

  ngOnInit(): void {
    this.userService.getVideoHistory().subscribe((response)=>
      {
        console.log(response);
        this.featuredVideos = response;

      },
      (error) => {
        console.log(error);
      }

    );
  }


}
