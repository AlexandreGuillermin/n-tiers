import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { MobilityServices } from './mobility.service';

const optionRequete = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class MobilityApiService {

  constructor(private http: HttpClient) { }

  /*Post une mobilité. Retourne un observable*/
  postMobility(userFormJson: any): Observable<Object> {
    return this.http.post('http://localhost:8081/mobility', userFormJson, optionRequete);
  }

  /*Get toute les mobilités. Retourne un observable*/
  getMobility(): Observable<Object> {
    return this.http.get('http://localhost:8081/mobility');
  }

  /*Get les mobilités filtrés. Retourne un observable*/
  getMobilityFilter(mobility: MobilityServices): Observable<Object> {
    return this.http.post('http://localhost:8081/mobility/filter', JSON.parse(JSON.stringify(mobility)), optionRequete);
  }

  /*Supprime les mobilités filtrés. Retourne un observable*/
  delMobility(mobility: MobilityServices): Observable<Object> {
    return this.http.post('http://localhost:8081/mobility/delete', JSON.parse(JSON.stringify(mobility)), optionRequete);
  }
}