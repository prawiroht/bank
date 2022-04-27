import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

const url: string = environment.url;
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class RightAccessService {

  constructor(private http: HttpClient) { }

  putAccess(userId: number, groupId: number, isActive: string): Observable<any> {
    return this.http.put<any>(url + `access/updateByUserIdAndGroupId?userId=${userId}&groupId=${groupId}&isActive=${isActive}`, {
      responseType: 'json',
    })
  }
}
