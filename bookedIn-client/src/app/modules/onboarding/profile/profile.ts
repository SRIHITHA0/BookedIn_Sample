import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

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
    const profile = JSON.parse(localStorage.getItem('profile') || '{}');
    profile.bio = this.bio;
    profile.profileImageUrl = this.imagePreview;
    profile.memberSince = 'April 2026'; // example
    localStorage.setItem('profile', JSON.stringify(profile));
    alert('✅ Profile saved!');
  }
}
