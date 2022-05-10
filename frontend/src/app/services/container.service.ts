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
export class ContainerService {

  constructor(private http : HttpClient) { }

  getContainer(): Observable<any>{
    return this.http.get<any>(API_URL + 'container/findAll', httpOptions)
  }

  getContainerByAllCategories(keyword: string): Observable<any> {
    return this.http
      .get<any>(API_URL + 'container/findByAllCategories?all='+ keyword +'&page=0&size=50', httpOptions);
  }

  getRequestedContainer():Observable<any>{
    return this.http.get<any>(API_URL + 'container/findByRequestStatus?page=0&size=100' , httpOptions);
  }

  postContainer(req : any): Observable<any> {
    return this.http.post<any>(API_URL + 'container/post', req, httpOptions)
  }

  putContainer(req : any): Observable<any> {
    return this.http.put<any>(API_URL + 'container/put', req, httpOptions)
    }

  deleteContainer(id:number):Observable<any>{
    return this.http.delete<any>(API_URL+'container/delete?containerId='+id,httpOptions);
  }
}
