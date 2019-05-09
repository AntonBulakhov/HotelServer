import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {EventrecordModel} from "../models/eventrecord-model";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {EventModel} from "../models/event-model";

@Injectable({
  providedIn: 'root'
})
export class EventrecordService {

  constructor( private http: HttpClient ,
               private auth: AuthService) { }
  public getAllEventrecords():Observable<EventrecordModel[]>{
    return this.http.get<EventrecordModel[]>("/api/event-records/list/user/"+this.auth.user.id);
  }

  public subscribeEvent(event: EventrecordModel):Observable<EventrecordModel>{
    return this.http.post<EventrecordModel>("/api/event-records", event);
  }

  public subExists(event: EventModel):Observable<boolean>{
    return this.http.post<boolean>("/api/event-records/ex/user/"+this.auth.user.id, event);
  }
}
