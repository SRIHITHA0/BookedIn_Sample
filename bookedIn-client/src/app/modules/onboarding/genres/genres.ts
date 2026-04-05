import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
selector: 'app-genres',
standalone: true,
imports: [CommonModule, RouterLink],
templateUrl: './genres.html',
styleUrls: ['./genres.css']
})
export class GenresComponent {

genres = [
{ name: 'Fantasy', image: 'assets/genreImg/fantasy.png' },
{ name: 'Romance', image: 'assets/genreImg/romance.png' },
{ name: 'Horror', image: 'assets/genreImg/horror.png' },
{ name: 'Science Fiction', image: 'assets/genres/scifi.png' },
{ name: 'Mystery', image: 'assets/genres/mystery.png' },
{ name: 'Thriller', image: 'assets/genres/thriller.png' },
{ name: 'Historical Fiction', image: 'assets/genres/historical.png' },
{ name: 'Contemporary', image: 'assets/genres/contemporary.png' },
{ name: 'Adventure', image: 'assets/genres/adventure.png' }
];

selectedGenres: string[] = [];

toggleGenre(name: string): void {
    if (this.selectedGenres.includes(name)) {
      this.selectedGenres = this.selectedGenres.filter(g => g !== name);
    } else if (this.selectedGenres.length < 3) {
      this.selectedGenres.push(name);
    }
  }

  canGoNext(): boolean {
    return this.selectedGenres.length === 3;
  }
}
