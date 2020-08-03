import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from './home';
import {AuthGuard} from './_helpers';
import {GuarantorsComponent} from './guarantors';
import {LoginComponent} from "./login";
import {CustomerComponent} from "./customer";
import {FollowingComponent} from "./following";

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},

  // TODO must fix after authentication added
  { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  { path: 'guarantors',component:GuarantorsComponent},
  { path: 'following',component:FollowingComponent},
  { path: 'customers',component:CustomerComponent},

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

export const appRoutingModule = RouterModule.forRoot(routes);
