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
export class AccountTypeService {

  constructor(private http: HttpClient) { }

  
  getAccountType(): Observable<any>{
    return this.http.get<any>(API_URL + 'accountType/findAll', httpOptions)
  }
}
