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
export class InvestmentService {

  constructor(private http: HttpClient) { }

  getInvestment(): Observable<any> {
    return this.http.get<any>(API_URL + `investment/findAll`, httpOptions);
  }

  getByAllCategories(keyword: string): Observable<any> {
    return this.http.get<any>(API_URL + `investment/getAllCategories?all=` + keyword + `&page=0&size=20`, httpOptions);
  }
}
