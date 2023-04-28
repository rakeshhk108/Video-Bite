import { Component } from '@angular/core';
import {catchError, tap, throwError} from "rxjs";
import {UploadVideoResponse} from "../model/upload-video-response";
import {VideoUploadService} from "../Service/video-upload.service";
import {Router} from "@angular/router";
import {NgxFileDropEntry} from "ngx-file-drop";

@Component({
  selector: 'app-video-upload',
  templateUrl: './video-upload.component.html',
  styleUrls: ['./video-upload.component.css']
})
export class VideoUploadComponent {

  constructor(private vedioService: VideoUploadService, private router: Router) { }

  isUpload: boolean = false;
  public files: NgxFileDropEntry[] = [];
  fileEntery: FileSystemFileEntry | undefined;

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        this.fileEntery = droppedFile.fileEntry as FileSystemFileEntry;

        this.fileEntery.file((file: File) => {

          this.isUpload = true;

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);

          /**
           // You could upload it like this:
           const formData = new FormData()
           formData.append('logo', file, relativePath)

           // Headers
           const headers = new HttpHeaders({
            'security-token': 'mytoken'
          })

           this.http.post('https://mybackend.com/api/upload/sanitize-and-save-logo', formData, { headers: headers, responseType: 'blob' })
           .subscribe(data => {
            // Sanitized logo returned from backend
          })
           **/

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event: any) {
    console.log(event);
  }

  public fileLeave(event: any) {
    console.log(event);
  }


  public uploadVid() {

    if (this.fileEntery != undefined) {
      this.fileEntery.file(file => {
        this.vedioService.upload(file).pipe(
          tap((response: UploadVideoResponse) => {
            this.router.navigateByUrl("/save-video-details/" + response.videoId);
          }),
          catchError((error: any) => {
            console.error(error);
            return throwError(()=> new Error(error));
          })
        ).subscribe();
      });
    }
  }


}
