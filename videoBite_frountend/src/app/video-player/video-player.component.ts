import { Component, ElementRef, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnChanges {

  @Input() videoUrl: string;
  @ViewChild('media') mediaElement: ElementRef;

  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {

    if (this.mediaElement && this.mediaElement.nativeElement) {

      if (changes['videoUrl'] && changes['videoUrl'].currentValue) {
        this.mediaElement.nativeElement.src = changes['videoUrl'].currentValue;
        this.mediaElement.nativeElement.load();
      }

    }
  }

}
