import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {EventrecordModel} from "../models/eventrecord-model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EventrecordService {

  constructor( private http: HttpClient ) { }
  public getAllEventrecords():Observable<EventrecordModel[]>{
    return this.http.get<EventrecordModel[]>("/api/eventrecord/list");
  }

  public subscribeEvent(event: EventrecordModel):Observable<EventrecordModel>{
    return this.http.post<EventrecordModel>("/api/event-records", event);
  }
}
