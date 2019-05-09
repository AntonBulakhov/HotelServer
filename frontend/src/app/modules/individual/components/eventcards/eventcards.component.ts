import { Component, OnInit } from '@angular/core';
import {EventModel} from "../../../../models/event-model";
import {EventService} from "../../../../services/event.service";
import file from "../../../../../assets/imgSrc.json"
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-eventcards',
  templateUrl: './eventcards.component.html',
  styleUrls: ['./eventcards.component.css']
})
export class EventcardsComponent implements OnInit {

  public events: EventModel[];

  public selectedEvent: EventModel = new EventModel();

  public imgLink = file;

  public price: number;

  constructor(private eventService: EventService,
              private auth: AuthService) { }

  ngOnInit() {
    this.loadData();
  }

  public setPrice(price: number){
    this.price = price*(1- (this.auth.user.discount/100));
  }

  public setEvent(evetn: EventModel):void{
    this.selectedEvent = evetn;
  }

  private loadData():void{
    this.eventService.getAllEvents().subscribe(data=>{
      this.events = data as EventModel[];

    })
  }
}
