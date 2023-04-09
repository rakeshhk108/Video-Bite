import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import { SaveVideoComponent } from './save-video/save-video.component';
import { VideoUploadComponent } from './video-upload/video-upload.component';
import { VideoPlayerComponent } from './video-player/video-player.component';
import {NgxFileDropModule} from "ngx-file-drop";
import {NavbarModule} from "./navbar/navbar.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatChipsModule} from "@angular/material/chips";
import { AuthConfigModule } from './auth/auth-config.module';
import {AuthInterceptor} from "angular-auth-oidc-client";
import { VideoDetailComponent } from './video-detail/video-detail.component';
import {FlexModule} from "@angular/flex-layout";
import { HomeComponent } from './home/home.component';
import { VideoCardComponent } from './video-card/video-card.component';
import { VideoHistroyComponent } from './video-histroy/video-histroy.component';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import { SidevideolistComponent } from './sidevideolist/sidevideolist.component';
import {MatLineModule} from "@angular/material/core";
import { LoginRequestComponent } from './login-request/login-request.component';
import { CommentComponent } from './comment/comment.component';


@NgModule({
  declarations: [
    AppComponent,
    SaveVideoComponent,
    VideoUploadComponent,
    VideoPlayerComponent,
    VideoDetailComponent,
    HomeComponent,
    VideoCardComponent,
    VideoHistroyComponent,
    LikedVideosComponent,
    SidevideolistComponent,
    LoginRequestComponent,
    CommentComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        VgCoreModule,
        VgControlsModule,
        VgOverlayPlayModule,
        VgBufferingModule,
        NgxFileDropModule,
        NavbarModule,
        BrowserAnimationsModule,
        HttpClientModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatChipsModule,
        MatIconModule,
        MatInputModule,
        MatSelectModule,
        MatSnackBarModule,
        MatFormFieldModule,
        MatCardModule,
        AuthConfigModule,
        FlexModule,
        MatListModule,
        MatExpansionModule,
        MatLineModule,

    ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
