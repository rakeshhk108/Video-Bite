import { Component } from '@angular/core';
import {VideoDetails} from "../model/videoDetails";
import {UserService} from "../Service/user.service";

@Component({
  selector: 'app-liked-videos',
  templateUrl: './liked-videos.component.html',
  styleUrls: ['./liked-videos.component.css']
})
export class LikedVideosComponent {

  featuredVideos : Array<VideoDetails> = [];

  constructor(private userService : UserService){

  }

  ngOnInit(): void {
    this.userService.getLikedVideoHistory().subscribe((response)=>
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
