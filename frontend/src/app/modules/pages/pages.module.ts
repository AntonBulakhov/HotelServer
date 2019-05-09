import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import {SharedModule} from "../shared/shared.module";
import {IndividualModule} from "../individual/individual.module";
import { AboutComponent } from './components/about/about.component';
import { EventsComponent } from './components/events/events.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ConferenceComponent } from './components/conference/conference.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { ReviewComponent } from './components/review/review.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RoombookComponent } from './components/booking/roombook/roombook.component';
import { EventbookComponent } from './components/booking/eventbook/eventbook.component';
import { ConfbookComponent } from './components/booking/confbook/confbook.component';
import { UsersComponent } from './components/users/users.component';
import {RouterModule} from "@angular/router";
import { SignComponent } from './components/modal/sign/sign.component';
import { ReductprofComponent } from './components/reductprof/reductprof.component';
import { AddroomComponent } from './components/admin/addroom/addroom.component';
import { RoomlistComponent } from './components/admin/roomlist/roomlist.component';
import { AddeventComponent } from './components/admin/addevent/addevent.component';
import { EventlistComponent } from './components/admin/eventlist/eventlist.component';
import { ConflistComponent } from './components/admin/conflist/conflist.component';
import { AdminmenuComponent } from './components/admin/adminmenu/adminmenu.component';
import { AddadminComponent } from './components/admin/addadmin/addadmin.component';
import { UserlistComponent } from './components/admin/userlist/userlist.component';
import {ReviewService} from "../../services/review.service";
import {UserService} from "../../services/user.service";
import {ReservationService} from "../../services/reservation.service";
import {RoomService} from "../../services/room.service";

import { ErrorComponent } from './components/error/error.component';

import {AuthService} from "../../services/auth.service";
import {AuthInterceptorService} from "../../services/auth-interceptor.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import { BlacklistComponent } from './components/blacklist/blacklist.component';


@NgModule({
  declarations: [HomeComponent, AboutComponent, EventsComponent, RegistrationComponent,
    ConferenceComponent, RoomsComponent, ReviewComponent, ProfileComponent, RoombookComponent,
    EventbookComponent, ConfbookComponent, UsersComponent, SignComponent, ReductprofComponent,
    AddroomComponent, RoomlistComponent, AddeventComponent, EventlistComponent, ConflistComponent,
    AdminmenuComponent, AddadminComponent, UserlistComponent, ErrorComponent, BlacklistComponent],
  imports: [
    CommonModule,
    SharedModule,
    IndividualModule,
    RouterModule
  ],exports:[
    HomeComponent,
    AboutComponent,
    ConferenceComponent,
    EventsComponent,
    RoomsComponent,
    RegistrationComponent,
    ReviewComponent,
    ProfileComponent,
    RoombookComponent,
    EventbookComponent,
    ConfbookComponent,
    UsersComponent,
    SignComponent,
    ReductprofComponent,
    AddroomComponent, RoomlistComponent, AddeventComponent, EventlistComponent, ConflistComponent, AdminmenuComponent,
    AddadminComponent, UserlistComponent,
    ErrorComponent, BlacklistComponent
  ],
  providers:[
    ReviewService,
    UserService,
    ReservationService,
    RoomService,
    AuthService,
    AuthInterceptorService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}
  ]
})
export class PagesModule { }
