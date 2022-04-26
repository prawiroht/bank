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
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(page: number): Observable<any> {
    return this.http.get<any>(url + `user/findAllPagination?page=${page}&size=8`, {
      responseType: 'json',
    });
  }

}
