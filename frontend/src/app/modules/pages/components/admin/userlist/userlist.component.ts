import { Component, OnInit } from '@angular/core';
import {UserSafeModel} from "../../../../../models/user-model";
import {UserService} from "../../../../../services/user.service";

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {
public users: UserSafeModel[];

constructor(private userService: UserService) { }

ngOnInit() {
  this.loadData();
}

private loadData():void{
  this.userService.getAllUsers().subscribe(data=>{
  this.users = data as UserSafeModel[];
})
}

}
