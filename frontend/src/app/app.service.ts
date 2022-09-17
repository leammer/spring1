import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PersonalInfo } from './profile/personal-info'

@Injectable()
export class AppService {
  currentUserId = 1;

  constructor(private http: HttpClient) { }
  
  getCurrentUserId(id: number) {
    return this.currentUserId;
  }

  setCurrentUserId(id: number) {
    this.currentUserId = id;
  }
  
  getPersonalInfo(id: number) {
    return this.http.get<PersonalInfo>(`http://localhost:8090//app/personal/${id}`);
  }
}
