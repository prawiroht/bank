import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map, catchError } from 'rxjs';
import { environment } from 'src/environments/environment';

const API_URL = environment.url 

const httpOptions = {
  headers: new HttpHeaders({
    Accept: 'application/json',
  }),
};


@Injectable({
  providedIn: 'root'
})
export class MainService {

  constructor(private http: HttpClient) { }

  getMain(): Observable<any> {
    return this.http.get<any>(API_URL + 'main/findAll', httpOptions);
  }

  getMainByAllCategories(keyword: string): Observable<any> {
    return this.http
      .get<any>(API_URL + 'main/findByAllCategories?all='+ keyword +'&page=0&size=50', httpOptions);
  }

  getCsv(): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/csv');
  
    return this.http.get(API_URL + 'main/export', {
      headers: headers,
      responseType: 'text'
    });
  }

  getRequestedMain():Observable<any>{
    return this.http.get<any>(API_URL + 'main/findByRequestStatus?page=0&size=100' , httpOptions);
  }

  postMain(req: any): Observable<any> {
    return this.http.post<any>(API_URL + 'main/input', req, httpOptions)
  }

  putMain(req: any): Observable<any> {
    return this.http.put<any>(API_URL + 'main/update', req,  httpOptions)
  }

  
  deleteMain(id:number):Observable<any>{
    return this.http.delete<any>(API_URL+'main/{id}?mainId='+id,httpOptions);
  }


  
}
