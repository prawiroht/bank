import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const API_URL = environment.url

const httpOptions = {
  headers: new HttpHeaders({ 
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class SaldoService {

  constructor(private http : HttpClient) { }

  getSaldo():Observable<any>{
    return this.http.get<any>(API_URL + '/deposit/getTotalDeposit', httpOptions);
  }
}
