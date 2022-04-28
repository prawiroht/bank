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
export class DepositsService {

  constructor(private http: HttpClient) { }

  getDeposits(): Observable<any> {
    return this.http.get<any>(API_URL + `deposit/findAll`, httpOptions);
  }

  getDepositsByAllCategories(keyword: string): Observable<any> {
    return this.http.get<any>(API_URL + `deposit/getAllCategories?all=` + keyword + `&page=0&size=20`, httpOptions);
  }
}
