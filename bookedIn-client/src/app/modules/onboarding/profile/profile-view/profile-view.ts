import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
selector: 'app-profile-view',
templateUrl: './profile-view.html',
styleUrls: ['./profile-view.css'],
standalone: true,
imports: [CommonModule,RouterModule]
})
export class ProfileViewComponent implements OnInit {
profileData: any = null;
initials: string = '';

ngOnInit() {
    const saved = localStorage.getItem('profile');
    if (saved) {
      this.profileData = JSON.parse(saved);

      if (this.profileData.name) {
        this.initials = this.profileData.name
          .split(' ')
          .map((n: string) => n[0])
          .join('')
          .toUpperCase();
      } else {
        this.initials = 'AU';
      }
    }
  }
}
