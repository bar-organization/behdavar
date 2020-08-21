import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from './home';
import {GuarantorsComponent} from './guarantors';
import {LoginComponent} from './login';
import {CustomerComponent} from './customer';
import {FollowingComponent} from './following';
import {DocumentAttachmentComponent} from "./document-attachment/document-attachment.component";

const routes: Routes = [
  {path: '', component: HomeComponent},

  // TODO must fix after authentication added
  { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  { path: 'guarantors',component:GuarantorsComponent},
  { path: 'following',component:FollowingComponent},
  { path: 'customers',component:CustomerComponent},
  { path: 'attachment',component:DocumentAttachmentComponent},

  {path: '**', redirectTo: ''}
];

export const appRoutingModule = RouterModule.forRoot(routes);
