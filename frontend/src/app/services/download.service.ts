import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable, map, catchError } from 'rxjs';


const API_URL = environment.url 

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  constructor(private http: HttpClient) { }

  download(file: string | undefined): Observable<Blob> {
    return this.http.get(API_URL + 'main/export', {
      responseType: 'blob'
    });
  }
}
