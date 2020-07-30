import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from './home';
import {AuthGuard} from './_helpers';
import {GuarantorsComponent} from './guarantors';
import {LoginComponent} from "./login";

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},

  // TODO must fix after authentication added
  { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  { path: 'guarantors',component:GuarantorsComponent},

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

export const appRoutingModule = RouterModule.forRoot(routes);
