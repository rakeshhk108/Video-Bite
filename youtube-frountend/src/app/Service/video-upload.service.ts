import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {UploadVideoResponse} from "../model/upload-video-response";
import {HttpClient} from "@angular/common/http";
import {VideoDetails} from "../model/videoDetails";

@Injectable({
  providedIn: 'root'
})
export class VideoUploadService {

  constructor(private httpClient : HttpClient) { }


  upload(fileEntery: File): Observable<any>{


    const formData = new FormData()
    formData.append('file', fileEntery, fileEntery.name)


    //we have to do http post request

    return this.httpClient.post<UploadVideoResponse>('http://localhost:8080/videos/upload',formData);
  }

  uploadTumbnail(fileEntery: File , videoId : string): Observable<string>{


    const formData = new FormData()
    formData.append('file', fileEntery, fileEntery.name);
    formData.append('videoId', videoId);


    //we have to do http post request
    return this.httpClient.post('http://localhost:8080/videos/upload_thumbnail',formData,{
      responseType: "text"
    });

  }


  //get the video details

  getVideo(videoId:string):Observable<VideoDetails>
  {
    return this.httpClient.get<VideoDetails>("http://localhost:8080/videos/getVideo/" + videoId);
  }

  saveVideo(videoMetaData: VideoDetails):Observable<VideoDetails> {
    return this.httpClient.put<VideoDetails>("http://localhost:8080/videos/update", videoMetaData);
  }
}