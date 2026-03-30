import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  isLightMode = false;

  onSignIn(event?: Event) {
    if (event) event.preventDefault();
    console.log("Navigation triggered");
  }

  toggleTheme() {
    this.isLightMode = !this.isLightMode;
  }
}
