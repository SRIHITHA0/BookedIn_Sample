import { Routes } from '@angular/router';
import { LandingComponent } from './modules/landing/landing.component';
//import { ProfileComponent } from './modules/profile/profile.component';

export const routes: Routes = [
  { path: '', component: LandingComponent }        // Default page
  //{ path: 'profile', component: ProfileComponent }    // Where "Sign In" leads
];
