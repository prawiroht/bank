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

  getUser(page: any): Observable<any> {
    return this.http.get<any>(url + `user/findAllPagination?page=${page}&size=4`, {
      responseType: 'json',
    });
  }

  postUser(req: any): Observable<any> {
    return this.http.post<any>(url + `user/post`, req, {
      responseType: 'json',
    })
  }

  putUser(req: any): Observable<any> {
    return this.http.put<any>(url + `user/update`, req, {
      responseType: 'json',
    })
  }

  deleteUser(userId: number): Observable<any> {
    return this.http.delete<any>(url + `user/delete?id=${userId}`)
  }

  getByUsername(username: string): Observable<any> {
    return this.http.get<any>(
      url + `users/findByUsername?username=${username}`,
      httpOptions
    );
  }
}
