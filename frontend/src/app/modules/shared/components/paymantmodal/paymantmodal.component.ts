import {Component, Input, OnInit} from '@angular/core';
import {ReservationModel} from "../../../../models/reservation-model";
import {ReservationService} from "../../../../services/reservation.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {EventrecordModel} from "../../../../models/eventrecord-model";
import {EventrecordService} from "../../../../services/eventrecord.service";
import {EventModel} from "../../../../models/event-model";

@Component({
  selector: 'app-paymantmodal',
  templateUrl: './paymantmodal.component.html',
  styleUrls: ['./paymantmodal.component.css']
})
export class PaymantmodalComponent implements OnInit {

  @Input() public price: number;
  @Input() public fixedPrice: number;
  @Input() public event: EventModel;
  public sub: EventrecordModel = new EventrecordModel();
  public pplCount:number = 1;

  constructor(private eventRecordService: EventrecordService,
              private router: Router,
              public auth: AuthService) {
    this.fixedPrice = this.price;
  }

  ngOnInit() {
  }


  public people(ppl: string):void{
    if(+ppl > this.pplCount){
      this.price += this.fixedPrice * (+ppl - this.pplCount);
    }
    if(+ppl < this.pplCount){
      this.price -= this.fixedPrice * (this.pplCount - +ppl);
    }
    this.pplCount = +ppl;
  }

  public makePayment():void{
    this.sub.user = this.auth.user;
    this.sub.event = this.event;
    this.sub.personCount = this.pplCount;
    this.eventRecordService.subscribeEvent(this.sub).subscribe(()=>{
      this.router.navigate(['']);
      setTimeout(location.reload.bind(location), 200);
    })
  }

}
