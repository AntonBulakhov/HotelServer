import { Component, OnInit } from '@angular/core';
import {UserSafeModel} from "../../../../models/user-model";
import {UserService} from "../../../../services/user.service";
import {AuthService} from "../../../../services/auth.service";

@Component({
  selector: 'app-blacklist',
  templateUrl: './blacklist.component.html',
  styleUrls: ['./blacklist.component.css']
})
export class BlacklistComponent implements OnInit {
  public users: UserSafeModel[];

  constructor(private userService: UserService,
              public auth: AuthService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData():void{
    this.userService.getAllBlacklistUsers().subscribe(data=>{
      this.users = data as UserSafeModel[];
    })
  }

  public unblock(id: string):void{
    this.userService.unblockUserById(id).subscribe(()=>{
      setTimeout(location.reload.bind(location), 200);
    })
  }

}
