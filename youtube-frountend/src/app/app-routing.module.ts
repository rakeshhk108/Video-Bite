import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VideoUploadComponent} from "./video-upload/video-upload.component";
import {SaveVideoComponent} from "./save-video/save-video.component";
import {VideoDetailComponent} from "./video-detail/video-detail.component";

const routes: Routes = [
  {
    path:'',
    component: VideoUploadComponent
  },
  {
    path: 'save-video-details/:videoId',
    component : SaveVideoComponent
  },
  {
    path: 'video-details/:videoId',
    component : VideoDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
