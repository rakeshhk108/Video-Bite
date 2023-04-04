import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VideoUploadComponent} from "./video-upload/video-upload.component";
import {SaveVideoComponent} from "./save-video/save-video.component";
import {VideoDetailComponent} from "./video-detail/video-detail.component";
import { HomeComponent } from './home/home.component';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import { VideoHistoryComponent } from './video-history/video-history.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "likedVideos",
    component: LikedVideosComponent
  },
  {
    path: "history",
    component: VideoHistoryComponent
  },
  {
    path:'uploadVideo',
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
