import { Component } from '@angular/core';
import { VideoDetails } from '../model/videoDetails';
import { VideoUploadService } from '../Service/video-upload.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  featuredVideos : Array<VideoDetails> = [];

  constructor(private videoService : VideoUploadService){

  }

  ngOnInit():void{
        this.videoService.getAllVideos().subscribe((response)=>
        {
            this.featuredVideos = response;
        },        
        (error) => {
            console.log(error);
        }
        
        );
  }
}
