import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router'; // <-- Added RouterLink

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [CommonModule, RouterLink], // <-- Added RouterLink to imports
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  // You no longer need the constructor or onSignIn method here 
  // if you use routerLink in the HTML as shown below.
}