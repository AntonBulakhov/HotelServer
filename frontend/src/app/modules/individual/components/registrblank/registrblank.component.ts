import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import { Validators } from '@angular/forms';
import {UserModel} from "../../../../models/user-model";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../../services/auth.service";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: 'app-registrblank',
  templateUrl: './registrblank.component.html',
  styleUrls: ['./registrblank.component.css']
})
export class RegistrblankComponent implements OnInit {

  emailControl: FormControl;

  public newUser: UserModel = new UserModel();
  @Input() public isAdmin: boolean = false;
  public userExistsByEmail: boolean = false;
  public userExistsByLogin: boolean = false;
  registerForm: FormGroup;
  submitted = false;
  form: FormGroup;

  constructor(private userService: UserService,
              private router: Router,
              private auth: AuthService,
              private formBuilder: FormBuilder) {  }



  ngOnInit() {
    this.registerForm = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName : ['', Validators.required],
    login : ['', Validators.required],
    number:['', [Validators.required,Validators.minLength(7)]],
    password :['', [Validators.required,Validators.minLength(6)]],
    birthdate :['', Validators.required],
    email :['', [Validators.required, Validators.email]],
  });
  //   this.firstnameControl = new FormControl('', Validators.required);
  //   this.secondnameControl = new FormControl('', Validators.required);
  //   this.numberControl = new FormControl('', Validators.compose([Validators.required,Validators.min(9999999)])) ;
  //   this.passwordControl = new FormControl('', Validators.compose([Validators.required,Validators.minLength(8)]));
  //   this.dateControl = new FormControl('');
  //   this.emailControl = new FormControl('', Validators.compose([
  //     Validators.required,
  //     Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
  //   ]));
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.regNewUser();

  }

  public regNewUser():void{
    if(!this.userExistsByEmail && !this.userExistsByLogin){
      if(this.isAdmin){
        this.newUser.role = 1;
      }else {
        this.newUser.role = 0;
      }
      this.auth.signUp(this.newUser);
    }
  }

  public ifExistsByEmail():void{
    if(this.newUser.email != null) this.userService.findUserByEmail(this.newUser.email).subscribe((exists)=>{
      if(exists) {
        this.userExistsByEmail = true;
      }
    });
  }

  public ifExistsByLogin():void{
    if(this.newUser.login != null) this.userService.findUserByLogin(this.newUser.login).subscribe((exists)=>{
      if(exists) {
        this.userExistsByLogin = true;
      }
    })
  }

}
