import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
selector: 'app-profile-edit',
standalone: true,
imports: [CommonModule, FormsModule],   // ✅ needed for *ngFor, ngModel, ngForm
templateUrl: './profile-edit.html',
styleUrls: ['./profile-edit.css']
})
export class ProfileEditComponent implements OnInit {
profileData: { name: string; bio: string; favoriteGenres: string[]; profileImageUrl: string } = {
name: '',
bio: '',
favoriteGenres: [],
profileImageUrl: ''
};
newGenre: string = '';

constructor(private router: Router) {}

  ngOnInit() {
    const savedProfile = localStorage.getItem('profile');
    if (savedProfile) {
      this.profileData = JSON.parse(savedProfile);
    }
  }

  addGenre() {
    if (this.newGenre.trim()) {
      this.profileData.favoriteGenres.push(this.newGenre.trim());
      this.newGenre = '';
    }
  }

  removeGenre(genre: string) {
    this.profileData.favoriteGenres =
      this.profileData.favoriteGenres.filter((g: string) => g !== genre);
  }
onFileSelected(event: Event) {
  const input = event.target as HTMLInputElement;
  if (input.files && input.files[0]) {
    const file = input.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.profileData.profileImageUrl = reader.result as string; // base64 string
    };
    reader.readAsDataURL(file);
  }
}

  saveProfile() {
    localStorage.setItem('profile', JSON.stringify(this.profileData));
    alert('Profile updated successfully!');   // ✅ feedback
    this.router.navigate(['/profile-view']);  // ✅ redirect back
  }
}
