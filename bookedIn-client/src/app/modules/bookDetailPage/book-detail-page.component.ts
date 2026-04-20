import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BookService } from '../../services/book.service';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-book-detail',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './book-detail-page.component.html',
  styleUrls: ['./book-detail-page.component.css']
})
export class BookDetailPageComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private bookService = inject(BookService);
  private reviewService = inject(ReviewService);

  // Core Data
  book = signal<any>(null);
  reviews = signal<any[]>([]);
  averageRating = signal<number>(0);
  
  // States
  isLoading = signal<boolean>(true);
  isError = signal<boolean>(false);
  isReviewFormVisible = signal<boolean>(false);

  // Form Data
  newReviewContent = signal<string>('');
  newReviewRating = signal<number>(5);

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.loadData(id ? +id : 1);
    });
  }

  loadData(id: number) {
    this.isLoading.set(true);
    this.isError.set(false);

    this.bookService.getBookById(id).subscribe({
      next: (data) => {
        this.book.set(data);
        this.isLoading.set(false);
      },
      error: () => {
        this.isError.set(true);
        this.isLoading.set(false);
      }
    });

    this.reviewService.getReviewsByBook(id).subscribe(data => this.reviews.set(data));
    this.reviewService.getAverageRating(id).subscribe(val => this.averageRating.set(val));
  }

  toggleReviewForm() {
    this.isReviewFormVisible.update(val => !val);
  }

  submitReview() {
    if (!this.newReviewContent().trim()) return;

    const reviewPayload = {
      user: { id: 1 }, // Demo User
      book: { id: this.book().id },
      rating: this.newReviewRating(),
      content: this.newReviewContent()
    };

    this.reviewService.addReview(reviewPayload).subscribe({
      next: () => {
        this.newReviewContent.set('');
        this.newReviewRating.set(5);
        this.isReviewFormVisible.set(false);
        this.loadData(this.book().id); // Refresh List
      },
      error: (err) => console.error('Submission failed', err)
    });
  }

  getStarArray(rating: number): number[] {
    return Array(Math.round(rating || 0)).fill(0);
  }

  getEmptyStarArray(rating: number): number[] {
    return Array(5 - Math.round(rating || 0)).fill(0);
  }
}