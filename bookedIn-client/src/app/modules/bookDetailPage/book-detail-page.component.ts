import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BookService } from '../../services/book.service';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-book-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './book-detail-page.component.html',
  styleUrls: ['./book-detail-page.component.css']
})
export class BookDetailPageComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private bookService = inject(BookService);
  private reviewService = inject(ReviewService);

  // State Signals
  book = signal<any>(null);
  reviews = signal<any[]>([]);
  averageRating = signal<number>(0);
  isLoading = signal<boolean>(true);
  isError = signal<boolean>(false);

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.loadData(id ? +id : 1);
    });
  }

  loadData(id: number) {
    this.isLoading.set(true);
    this.isError.set(false);

    // Fetch Book Details
    this.bookService.getBookById(id).subscribe({
      next: (data) => {
        this.book.set(data);
        this.isLoading.set(false);
        console.log('Book loaded:', data);
      },
      error: (err) => {
        console.error('API Error:', err);
        this.isError.set(true);
        this.isLoading.set(false);
      }
    });

    // Fetch Reviews & Ratings
    this.reviewService.getReviewsByBook(id).subscribe(data => this.reviews.set(data));
    this.reviewService.getAverageRating(id).subscribe(val => this.averageRating.set(val));
  }

  // Star Logic Helpers
  getStarArray(rating: number): number[] {
    const rounded = Math.round(rating || 0);
    return Array(Math.min(5, rounded)).fill(0);
  }

  getEmptyStarArray(rating: number): number[] {
    const rounded = Math.round(rating || 0);
    const emptyCount = Math.max(0, 5 - rounded);
    return Array(emptyCount).fill(0);
  }
}