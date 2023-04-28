import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, switchMap} from "rxjs";
import {VideoDetails} from "../model/videoDetails";

@Injectable({
  providedIn: 'root'
})
export class UserService {
   private userId: string;

  constructor(private httpClient : HttpClient) { }



  registerUser() {
    this.httpClient.get("http://localhost:8080/api/user/register", {responseType: "text"})
      .subscribe(data => {
        this.userId = data;
      })
  }

  getUserId(): string{
    return this.userId;
  }

  getVideoHistory(): Observable<Array<VideoDetails>> {
    return this.httpClient.get("http://localhost:8080/api/user/register", {responseType: "text"})
      .pipe(
        switchMap(userId => {
          this.userId = userId;
          return this.httpClient.get<Array<VideoDetails>>("http://localhost:8080/api/user/" + this.userId + "/history");
        })
      );
  }

  getLikedVideoHistory() {
    return this.httpClient.get("http://localhost:8080/api/user/register", {responseType: "text"})
      .pipe(
        switchMap(userId => {
          this.userId = userId;
          return this.httpClient.get<Array<VideoDetails>>("http://localhost:8080/api/user/" + this.userId + "/likeVideos");
        })
      );
  }
}
