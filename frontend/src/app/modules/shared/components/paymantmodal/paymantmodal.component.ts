import {Component, Input, OnInit} from '@angular/core';
import {ReservationModel} from "../../../../models/reservation-model";
import {ReservationService} from "../../../../services/reservation.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {EventrecordModel} from "../../../../models/eventrecord-model";
import {EventrecordService} from "../../../../services/eventrecord.service";

@Component({
  selector: 'app-paymantmodal',
  templateUrl: './paymantmodal.component.html',
  styleUrls: ['./paymantmodal.component.css']
})
export class PaymantmodalComponent implements OnInit {

  @Input() public price: number;
  @Input() public sub: EventrecordModel;
  constructor(private eventRecordService: EventrecordService,
              private router: Router,
              public auth: AuthService) { }

  ngOnInit() {
  }



  public makePayment():void{
    this.sub.user = this.auth.user;
    this.eventRecordService.subscribeEvent(this.sub).subscribe(()=>{
      this.router.navigate(['']);
      setTimeout(location.reload.bind(location), 200);
    })
  }

}
