import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Departman } from '../models/departman';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DepartmanService {

  // End Point u Development mode-u
  private readonly API_URL = 'http://localhost:8083/departman/';

  // End Point u Deployment mode-u
  //private readonly API_URL = 'https://backend-rva.herokuapp.com/Departman/';

  dataChange: BehaviorSubject<Departman[]> = new BehaviorSubject<Departman[]>([]);

  constructor(private httpClient: HttpClient) { }
  public getAllDepartman(): Observable<Departman[]> {
      this.httpClient.get<Departman[]>(this.API_URL).subscribe(data => {
          this.dataChange.next(data);
      },
          (error: HttpErrorResponse) => {
              console.log(error.name + ' ' + error.message);
          });
      return this.dataChange.asObservable();
  }
  public addDepartman(departman: Departman): void {
      this.httpClient.post(this.API_URL, departman).subscribe();
  }
  public updateDepartman(departman: Departman): void {
      this.httpClient.put(this.API_URL, departman).subscribe();
  }
  public deleteDepartman(id: number): void {
      this.httpClient.delete(this.API_URL + id).subscribe();
  }
}
