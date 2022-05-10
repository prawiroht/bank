import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

const API_URL = environment.url;

const httpOptions = {
  headers: new HttpHeaders({
    Accept: 'application/json',
  }),
};


@Injectable({
  providedIn: 'root'
})
export class GiroService {

  constructor(private http: HttpClient) { }


  getGiro(): Observable<any> {
    return this.http.get<any>(API_URL + `currentaccount/findAll`, httpOptions);
  }

  getGiroByAllCategories(keyword: string): Observable<any> {
    return this.http.get<any>(API_URL + `currentaccount/getAllCategories?all=` + keyword + `&page=0&size=20`, httpOptions);
  }

  postGiro(req: any): Observable<any> {
    return this.http.post<any>(API_URL + 'currentaccount/posts', req, httpOptions)
  }

  putGiro(req: any): Observable<any> {
    return this.http.put<any>(API_URL + 'currentaccount/update', req,  httpOptions)
  }

  deleteGiro(id:number):Observable<any>{
    return this.http.delete<any>(API_URL+'currentaccount/'+id,httpOptions);
  }

  getRequest():Observable<any>{
    return this.http.get<any>(API_URL + `currentaccount/findByRequestStatus?page=0&size=100`, httpOptions);
  }

}
