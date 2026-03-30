import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupData = {
    fullName: '',
    penName: '',
    gender: '',
    country: '',
    language: '',
    email: ''
  };

  constructor(private router: Router) {}

  onSubmit() {
    console.log('Signup attempt:', this.signupData);
  }
}