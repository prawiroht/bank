import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

const url: string = environment.url;
const httpOptions = {
  headers: new HttpHeaders({
    Accept: 'application/json',
  }),
};


@Injectable({
  providedIn: 'root'
})
export class GiroService {
  private giroUrl = url + 'currentaccount/';

  constructor(private http: HttpClient) { }


  getGiro(): Observable<any> {
    return this.http.get<any>(this.giroUrl + `findAll`, httpOptions);
  }

  getAllCategories(keyword: string): Observable<any> {
    return this.http.get<any>(this.giroUrl + `getAllCategories?all=${keyword}`, httpOptions);
  }

}
