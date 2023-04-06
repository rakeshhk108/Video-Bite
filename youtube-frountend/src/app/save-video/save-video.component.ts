import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {ActivatedRoute} from "@angular/router";
import {VideoUploadService} from "../Service/video-upload.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatChipInputEvent} from "@angular/material/chips";
import {VideoDetails} from "../model/videoDetails";

@Component({
  selector: 'app-save-video',
  templateUrl: './save-video.component.html',
  styleUrls: ['./save-video.component.css']
})
export class SaveVideoComponent {


  saveVideoDetailsForm : FormGroup;

  // first argument is the default value of the formcontrol
  title : FormControl = new FormControl('', Validators.required);
  description: FormControl = new FormControl('', Validators.required);
  videoStatus: FormControl = new FormControl('', Validators.required);

  selectable = true;
  addOnBlur = true;
  removable = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile : File;
  selectedFileName: string;
  videoId: string;
  fileSelected: boolean = false;
  videoUrl : string;
  thumbnaiUrl: string;
  videoAvailable: boolean = false;


  constructor(private activatedRoute : ActivatedRoute, private videoService : VideoUploadService, private matSnackBar : MatSnackBar) {
    this.activatedRoute.paramMap.subscribe(params => {
      this.videoId = params.get('videoId');
    });

    this.videoService.getVideo(this.videoId).subscribe(
      (response) => {
        this.videoUrl = response.videoUrl;
        this.videoAvailable = true;
      }
    )

    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      videoStatus: this.videoStatus,
      description: this.description
    })
  }


  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }


  ngOnInit(): void {
  }

  onFileSelected(event: Event) {
    this.selectedFile = (event.target as HTMLInputElement).files[0];
    this.selectedFileName = this.selectedFile.name;
    this.fileSelected = true;
  }

  upload() {
    this.videoService.uploadTumbnail(this.selectedFile , this.videoId).subscribe(data =>
      {
        this.thumbnaiUrl = data;
        this.matSnackBar.open("Thumbnail Upload Successful!!" , "OK")
      },
      error => {
        console.log(error);
      }
    )
  }

  saveVideo() {
    const videoMetaData : VideoDetails ={
      "id": this.videoId,
      "title" : this.saveVideoDetailsForm.get('title')?.value,
      "description": this.saveVideoDetailsForm.get('description')?.value,
      "tags" : this.tags,
      "videoStatus" : this.saveVideoDetailsForm.get('videoStatus')?.value,
      "videoUrl": this.videoUrl,
      "tumbnail" : this.thumbnaiUrl,
      "likeCount": 0,
      "disLikeCount": 0,
      "viewCount": 0
    }

    this.videoService.saveVideo(videoMetaData).subscribe((response)=>{
      console.log(response);
      this.matSnackBar.open("video Metadata Update successfully","OK")
    })
  }
}
