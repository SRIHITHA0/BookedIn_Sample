import { Routes } from '@angular/router';
import { LandingComponent } from './modules/landing/landing.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { ProfileEditComponent } from './profile-edit/profile-edit';

// Profile lives inside onboarding/profile
import { ProfileComponent } from './modules/onboarding/profile/profile';
import { ProfileViewComponent } from './modules/onboarding/profile/profile-view/profile-view';

export const routes: Routes = [
{ path: '', component: LandingComponent },
{ path: 'login', component: LoginComponent },
{ path: 'profile-edit', component: ProfileEditComponent },

{ path: 'signup', component: SignupComponent },

{
path: 'onboarding',
loadChildren: () =>
      import('./modules/onboarding/onboarding-module')
        .then(m => m.OnboardingModule)
  },

  { path: 'profile', component: ProfileComponent },
  { path: 'profile-view', component: ProfileViewComponent }
];




