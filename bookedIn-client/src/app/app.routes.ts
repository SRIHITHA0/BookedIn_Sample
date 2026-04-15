import { Routes } from '@angular/router';
 import { LandingComponent } from './modules/landing/landing.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { BookDetailPageComponent } from './modules/bookDetailPage/book-detail-page.component';
//import { ProfileComponent } from './modules/profile/profile.component';

export const routes: Routes = [
   { path: '', component: LandingComponent },
   { path: 'login', component: LoginComponent },
   { path: 'signup', component: SignupComponent },
   //{ path: 'profile', component: ProfileComponent }
   { path: 'book/:id', component: BookDetailPageComponent }
];
