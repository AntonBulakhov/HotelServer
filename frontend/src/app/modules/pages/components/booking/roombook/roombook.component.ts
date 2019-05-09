import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-roombook',
  templateUrl: './roombook.component.html',
  styleUrls: ['./roombook.component.css']
})
export class RoombookComponent implements OnInit {

  private roomType: string;

  constructor(private activeRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadInfo();
  }

  public loadInfo():void{
    const type = this.activeRoute.snapshot.params['type'];
    if(type){
      switch (type) {
        case "cr":{
          this.roomType = "Классический";
          break;
        }
        case "crv":{
          this.roomType = "Классический с видом на море";
          break;
        }
        case "hs":{
          this.roomType = "Люкс Hospitality";
          break;
        }
        case "pms":{
          this.roomType = "Люкс Premium";
          break;
        }
        case "ls":{
          this.roomType = "Роскошный люкс";
          break;
        }
        case "pds":{
          this.roomType = "Президентский люкс";
          break;
        }
      }
    }


  }

}
