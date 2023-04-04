import { Component, Input } from '@angular/core';
import { VideoDetails } from '../model/videoDetails';

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.css']
})
export class VideoCardComponent {

  @Input() displayVideo : VideoDetails;
}
