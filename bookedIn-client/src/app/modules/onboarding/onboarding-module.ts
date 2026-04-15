import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OnboardingRoutingModule } from './onboarding-routing-module';
import { OnboardingComponent } from './onboarding';   // standalone
import { GenresComponent } from './genres/genres';    // standalone
import { ProfileComponent } from './profile/profile'; // standalone
import { ProfileViewComponent } from './profile/profile-view/profile-view'; // standalone

@NgModule({
imports: [
CommonModule,
OnboardingRoutingModule,
OnboardingComponent,
GenresComponent,
ProfileComponent,
ProfileViewComponent
]
})
export class OnboardingModule { }
