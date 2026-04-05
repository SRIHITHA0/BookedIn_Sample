import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileService } from '../../../../services/profile';

@Component({
standalone: true,
selector: 'app-profile-view',
imports: [CommonModule],
templateUrl: './profile-view.html',
styleUrls: ['./profile-view.css']
})
export class ProfileView {

profile: any;

constructor(private profileService: ProfileService) {
    this.profileService.getProfile().subscribe((res: any) => {
      this.profile = res;
    });
  }
}
