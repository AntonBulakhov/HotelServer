import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../../services/auth.service";
import {UserService} from "../../../../services/user.service";
import {UserModel} from "../../../../models/user-model";

@Component({
  selector: 'app-reductprofview',
  templateUrl: './reductprofview.component.html',
  styleUrls: ['./reductprofview.component.css']
})
export class ReductprofviewComponent implements OnInit {

  private user: UserModel;
  public loaded: boolean = false;

  constructor(public auth: AuthService,
              private userService: UserService) { }

  ngOnInit() {
    this.userService.getUserById(this.auth.user.id).subscribe(value => {
      this.user = value;
      this.loaded = true;
    })
  }

  public onSubmit():void{
    console.log(this.user);
    // this.auth.edit(this.user);
  }
}


