import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // <-- Add this line
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule], // Now Angular will recognize this
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  constructor(private router: Router) {}

  onSignIn(event?: Event) {
    if (event) {
      event.preventDefault();
    }
    this.router.navigate(['/profile']);
  }
}
