import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenresComponent } from './genres/genres';
import { ProfileComponent } from './profile/profile';
import { OnboardingComponent } from './onboarding';
import { ProfileViewComponent } from './profile/profile-view/profile-view';

const routes: Routes = [
{ path: '', component: OnboardingComponent },
{ path: 'genres', component: GenresComponent },
{ path: 'profile', component: ProfileComponent },
{ path: 'profile-view', component: ProfileViewComponent }
];

@NgModule({
imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OnboardingRoutingModule {}
