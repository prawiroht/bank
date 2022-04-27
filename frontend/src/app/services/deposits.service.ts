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
export class DepositsService {
  private depositsUrl = url + 'deposit/';

  constructor(private http: HttpClient) { }

  getDeposits(): Observable<any> {
    return this.http.get<any>(this.depositsUrl + `findAll`, httpOptions);
  }
}
