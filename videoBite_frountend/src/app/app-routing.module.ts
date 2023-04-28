import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {VideoUploadComponent} from "./video-upload/video-upload.component";
import {SaveVideoComponent} from "./save-video/save-video.component";
import {VideoDetailComponent} from "./video-detail/video-detail.component";
import { HomeComponent } from './home/home.component';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import {VideoHistroyComponent} from "./video-histroy/video-histroy.component";
import {LoginRequestComponent} from "./login-request/login-request.component";


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
    component: VideoHistroyComponent
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
  },
  {
    path: 'login-request',
    component: LoginRequestComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
