import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html', // Use the file instead of the inline string
  styleUrl: './app.component.css'
})
export class AppComponent { }
