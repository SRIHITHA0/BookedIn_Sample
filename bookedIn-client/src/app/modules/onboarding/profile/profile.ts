import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProfileService } from '../../../services/profile';

@Component({
selector: 'app-profile',
standalone: true,
imports: [CommonModule, FormsModule],
templateUrl: './profile.html',
styleUrls: ['./profile.css']
})
export class ProfileComponent {

bio: string = '';
imagePreview: string | ArrayBuffer | null = null;

constructor(private profileService: ProfileService) {}

  onImageSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      return;
    }

    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };

    reader.readAsDataURL(input.files[0]);
  }

  completeProfile(): void {
    this.profileService.saveProfile({
      bio: this.bio,
      favoriteGenres: [],
      profileImageUrl: this.imagePreview
    }).subscribe(() => {
      console.log('✅ Profile saved successfully');
    });
  }
}
