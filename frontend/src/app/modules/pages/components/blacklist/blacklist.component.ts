import { Component, OnInit } from '@angular/core';
import {UserSafeModel} from "../../../../models/user-model";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: 'app-blacklist',
  templateUrl: './blacklist.component.html',
  styleUrls: ['./blacklist.component.css']
})
export class BlacklistComponent implements OnInit {
  public users: UserSafeModel[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData():void{
    this.userService.getAllBlacklistUsers().subscribe(data=>{
      this.users = data as UserSafeModel[];
    })
  }

}
