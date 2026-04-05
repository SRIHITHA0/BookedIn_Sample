import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenresComponent } from './genres/genres';
import { ProfileComponent } from './profile/profile';
import { ProfileView } from './profile/profile-view/profile-view';
const routes: Routes = [
{ path: 'genres', component: GenresComponent },
{ path: 'profile', component: ProfileComponent } ,
{
path: 'profile-view',
component: ProfileView
}
];

@NgModule({
imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OnboardingRoutingModule {}


