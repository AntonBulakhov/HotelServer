import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./modules/pages/components/home/home.component";
import {AboutComponent} from "./modules/pages/components/about/about.component";
import {RoomsComponent} from "./modules/pages/components/rooms/rooms.component";
import {EventsComponent} from "./modules/pages/components/events/events.component";
import {ConferenceComponent} from "./modules/pages/components/conference/conference.component";
import {ReviewComponent} from "./modules/pages/components/review/review.component";
import {RegistrationComponent} from "./modules/pages/components/registration/registration.component";
import {ProfileComponent} from "./modules/pages/components/profile/profile.component";
import {ConfbookComponent} from "./modules/pages/components/booking/confbook/confbook.component";
import {RoombookComponent} from "./modules/pages/components/booking/roombook/roombook.component";
import {EventbookComponent} from "./modules/pages/components/booking/eventbook/eventbook.component";
import {UsersComponent} from "./modules/pages/components/users/users.component";
import {SignComponent} from "./modules/pages/components/modal/sign/sign.component";
import {ReductprofComponent} from "./modules/pages/components/reductprof/reductprof.component";
import {AdminmenuComponent} from "./modules/pages/components/admin/adminmenu/adminmenu.component";
import {AddroomComponent} from "./modules/pages/components/admin/addroom/addroom.component";
import {AddeventComponent} from "./modules/pages/components/admin/addevent/addevent.component";
import {ConflistComponent} from "./modules/pages/components/admin/conflist/conflist.component";
import {EventlistComponent} from "./modules/pages/components/admin/eventlist/eventlist.component";
import {RoomlistComponent} from "./modules/pages/components/admin/roomlist/roomlist.component";
import {UserlistComponent} from "./modules/pages/components/admin/userlist/userlist.component";
import {AddadminComponent} from "./modules/pages/components/admin/addadmin/addadmin.component";
import {ErrorComponent} from "./modules/pages/components/error/error.component";
import {BlacklistComponent} from "./modules/pages/components/blacklist/blacklist.component";

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'rooms', component:RoomsComponent},
  {path:'events', component:EventsComponent},
  {path:'conference', component:ConferenceComponent},
  {path:'review', component:ReviewComponent},
  {path:'registration', component:RegistrationComponent},
  {path:'profile', component:ProfileComponent},
  {path:'roombook/:type', component:RoombookComponent},
  {path:'eventbook', component:EventbookComponent},
  {path:'confbook', component:ConfbookComponent},
  {path:'users', component:UsersComponent},
  {path:'sign', component:SignComponent},
  {path:'reductprof', component:ReductprofComponent},
  {path:'adminmenu', component:AdminmenuComponent},
  {path:'addroom', component:AddroomComponent},
  {path:'addevent', component:AddeventComponent},
  {path:'conflist', component:ConflistComponent},
  {path:'eventlist', component:EventlistComponent},
  {path:'roomlist', component:RoomlistComponent},
  {path:'userlist', component:UserlistComponent},
  {path:'addadmin', component:AddadminComponent},
  {path:'error', component:ErrorComponent},
  {path:'blacklist', component:BlacklistComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
