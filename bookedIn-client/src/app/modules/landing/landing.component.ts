import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  // All logic must be INSIDE these class braces

  isLightMode = false;

  toggleTheme() {
    this.isLightMode = !this.isLightMode;
  }

  onSignIn(event?: Event) {
    if (event) {
      event.preventDefault();
    }
    console.log("Navigation triggered");
  }

} // This is the final closing brace
