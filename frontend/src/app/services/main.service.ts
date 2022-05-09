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

  // exporttoCsv(stickSearch: any): Observable<any> {
  //   return this.http
  //     .get<any>(API_URL + 'main/export', httpOptions);
  // }

  getCsv(): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/csv');
  
    return this.http.get(API_URL + 'main/export', {
      headers: headers,
      responseType: 'text'
    });
  }


  
}
