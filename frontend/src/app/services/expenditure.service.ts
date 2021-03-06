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

  getRequestedExpenditure():Observable<any>{
    return this.http.get<any>(API_URL + 'expenditure/findByRequestStatus?page=0&size=100' , httpOptions);
  }

  postExpenditure(req: any): Observable<any> {
    return this.http.post<any>(API_URL + 'expenditure/input', req, httpOptions)
  }

  putExpenditure(req: any): Observable<any> {
    return this.http.put<any>(API_URL + 'expenditure/put', req, httpOptions)
  }

  deleteExpenditure(id:number):Observable<any>{
    return this.http.delete<any>(API_URL+'expenditure/delete?id='+id,httpOptions);
  }

  getExpenditureByDateAndBank(startDate:string, endDate: string, bankId: number):Observable<any>{
    return this.http.get<any>(API_URL + 'expenditure/getTotalExpenditureWithParam?startDate=' + startDate + '&endDate=' + endDate + '&bankId=' + bankId , httpOptions);
  }

  getApprovedExpenditure():Observable<any>{
    return this.http.get<any>(API_URL + 'expenditure/findByApprovedStatus?page=0&size=100' , httpOptions);
  }
}
