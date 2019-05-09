import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel, UserSafeModel} from "../models/user-model";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  public getUserById(id: string):Observable<UserModel>{
    return this.http.get<UserModel>("/api/users/id/"+id);
  }

  public getAllUsers():Observable<UserSafeModel[]>{
    return this.http.get<UserSafeModel[]>("/api/users/list");
  }

  findUserByEmail(email:string): any{
    return this.http.get("/api/users/email/"+email+"/ex");
  }

  findUserByLogin(login:string): any{
    return this.http.get("/api/users/login/"+login+"/ex");
  }

  public getAllBlacklistUsers():Observable<UserModel>{
    return this.http.get<UserModel>("/api/users/blacklist");
  }


}
