import {Component, ElementRef, Input, ViewContainerRef} from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent {

  videoUrl: string;
  @Input("videoUrl")
  set VideoUrl(url: string){
    this.videoUrl = url;
    console.log(this.videoUrl);
  }
  constructor(private element: ElementRef) {
    console.log(this.videoUrl);
  }




}
