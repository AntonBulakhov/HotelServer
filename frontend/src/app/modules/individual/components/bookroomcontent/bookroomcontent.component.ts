import {Component, Input, OnInit} from '@angular/core';
import {ReservationModel} from "../../../../models/reservation-model";
import {ReservationService} from "../../../../services/reservation.service";
import {AuthService} from "../../../../services/auth.service";
import {RoomService} from "../../../../services/room.service";
import {RoomModel} from "../../../../models/room-model";

@Component({
  selector: 'app-bookroomcontent',
  templateUrl: './bookroomcontent.component.html',
  styleUrls: ['./bookroomcontent.component.css']
})
export class BookroomcontentComponent implements OnInit {
  private child: number = 0;
  private adult: number = 1;
  @Input() public  roomType: string;

  public priceAll: number = 0;

  public hasBath:boolean = false;
  public hasBabyBed:boolean = false;
  public hasFridge:boolean = false;
  public hasTv:boolean = false;


  public newReservation: ReservationModel = new ReservationModel();

  public requiredRoom: RoomModel = new RoomModel();

  constructor( private reservationService: ReservationService,
               private roomService: RoomService,
               public auth: AuthService) { }

  ngOnInit() {
  }

  public makeReservation():void{
    this.newReservation.personCount = Number(this.adult) + Number(this.child);
    this.requiredRoom.maxPersons = this.newReservation.personCount;
    if (!this.hasBath) {
      this.requiredRoom.hasBath = 0;
    }else {
      this.requiredRoom.hasBath = 1;
    }
    if (!this.hasBabyBed) {
      this.requiredRoom.hasBabyBed = 0;
    }else {
      this.requiredRoom.hasBabyBed = 1;
    }
    if (!this.hasFridge) {
      this.requiredRoom.hasFridge = 0;
    }else {
      this.requiredRoom.hasFridge = 1;
    }
    if (!this.hasTv) {
      this.requiredRoom.hasTv = 0;
    }else {
      this.requiredRoom.hasTv = 1;
    }
    this.requiredRoom.roomType = this.roomType;

    this.roomService.getRequiredRoom(this.requiredRoom).subscribe(data=>{
      if(data != null){
        console.log(data);
        this.newReservation.room = data;
        this.priceAll = this.date_diff_indays(this.newReservation.dateFrom, this.newReservation.dateTo) * this.newReservation.room.price
      }
    })
   // this.reservationService.postReservation(this.newReservation);
  }

  public date_diff_indays(date1, date2):any {
    let dt1 = new Date(date1);
    let dt2 = new Date(date2);
    return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
  }
}
