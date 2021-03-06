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
export class GroupService {

  constructor(private http: HttpClient) { }

  getGroup(): Observable<any> {
    return this.http.get<any>(url + `group/findAll`, {
      responseType: 'json',
    });
  }

  getGroupPagination(page: any): Observable<any> {
    return this.http.get<any>(url + `group/findAllPagination?page=${page}&size=5`, {
      responseType: 'json',
    });
  }

  postGroup(req: any): Observable<any> {
    return this.http.post<any>(url + `group/post`, req, {
      responseType: 'json',
    })
  }

  putGroup(req: any): Observable<any> {
    return this.http.put<any>(url + `group/update`, req, {
      responseType: 'json',
    })
  }

}
