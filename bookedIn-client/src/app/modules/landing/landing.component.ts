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
  // Properties must be inside the class
  isLightMode: boolean = false;

  // Methods must be inside the class
  onSignIn(event?: Event) {
    if (event) {
      event.preventDefault();
    }
    console.log("Navigation triggered");
    // Usually, you would call an AuthService here for Auth0
  }

  toggleTheme() {
    this.isLightMode = !this.isLightMode;
  }
}
