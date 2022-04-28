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
export class ExpenditureService {

  constructor(private http : HttpClient) { }

  getExpenditure() : Observable<any>{
    return this.http.get<any>(API_URL + 'expenditure/findAll' , httpOptions);

  }

  getExpenditureByAllCategories(keyword: string): Observable<any> {
    return this.http
      .get<any>(API_URL + 'expenditure/findByAllCategories?all='+ keyword +'&page=0&size=50', httpOptions);
  }
}