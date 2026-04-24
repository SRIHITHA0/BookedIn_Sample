import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { BookService } from '../../core/services/book.service';
import { AuthService } from '../../core/services/auth.service';
import { ChatService, Conversation } from '../../core/services/chat.service';
import { ThemeService } from '../../core/services/theme.service';
import { Book, Genre } from '../../models/book.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, DecimalPipe],
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  @ViewChild('booksSection') booksSection!: ElementRef;

  recommendedBooks: Book[]  = [];
  trendingBooks:    Book[]  = [];
  genres:           Genre[] = [];
  searchQuery           = '';
  selectedGenreFilter   = 'all';
  isLoading             = false;
  displayName           = '';
  showChatSelector      = false;
  showMobileSearch      = false;
  showMobileMenu        = false;
  chatSection           = 'group';
  personalConversations: Conversation[] = [];

  readonly chatRooms = ['general', 'fiction', 'mystery', 'sci-fi', 'fantasy', 'thriller'];

  constructor(
    private bookService: BookService,
    private authService: AuthService,
    private chatService: ChatService,
    public  theme: ThemeService,
    private router: Router
  ) {}

  get myAvatarLetter(): string {
    return this.displayName ? this.displayName.charAt(0).toUpperCase() : '?';
  }

  ngOnInit(): void {
    this.displayName = this.authService.getDisplayName();
    this.loadRecommendedBooks();
    this.loadTrendingBooks();
    this.loadGenres();
  }

  loadRecommendedBooks(): void {
    this.isLoading = true;
    this.bookService.getRecommendedBooks().subscribe({
      next: (books) => { this.recommendedBooks = books; this.isLoading = false; },
      error: () => { this.isLoading = false; }
    });
  }

  loadTrendingBooks(): void {
    this.bookService.getTrendingBooks().subscribe({
      next: (books) => this.trendingBooks = books
    });
  }

  loadGenres(): void {
    this.bookService.getAllGenres().subscribe({
      next: (genres) => this.genres = genres
    });
  }

  onSearch(): void {
    const q = this.searchQuery.trim();
    if (!q) {
      this.selectedGenreFilter = 'all';
      this.loadRecommendedBooks();
      return;
    }
    this.selectedGenreFilter = 'all';
    this.showMobileSearch = false;
    this.isLoading = true;
    this.bookService.searchBooks(q).subscribe({
      next: (books) => {
        this.recommendedBooks = books;
        this.isLoading = false;
        setTimeout(() => this.scrollToResults(), 100);
      },
      error: () => { this.recommendedBooks = []; this.isLoading = false; }
    });
  }

  private scrollToResults(): void {
    this.booksSection?.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }

  clearSearch(): void {
    this.searchQuery = '';
    this.selectedGenreFilter = 'all';
    this.loadRecommendedBooks();
  }

  filterByGenre(genreName: string): void {
    this.selectedGenreFilter = genreName;
    this.searchQuery = '';
    if (genreName === 'all') {
      this.loadRecommendedBooks();
      return;
    }
    this.isLoading = true;
    this.bookService.getBooksByGenre(genreName).subscribe({
      next: (books) => { this.recommendedBooks = books; this.isLoading = false; },
      error: () => { this.recommendedBooks = []; this.isLoading = false; }
    });
  }

  goToBook(id: number): void { this.router.navigate(['/books', id]); }

  openChat(): void {
    this.showChatSelector = true;
    this.showMobileMenu = false;
    this.chatSection = 'group';
    this.loadPersonalChats();
  }

  closeChatSelector(): void { this.showChatSelector = false; }

  loadPersonalChats(): void {
    this.chatService.getPersonalConversations().subscribe({
      next: (convs) => this.personalConversations = convs,
      error: () => {}
    });
  }

  avatarLetter(name: string): string {
    return name ? name.charAt(0).toUpperCase() : '?';
  }

  goToChat(roomId: string): void {
    this.showChatSelector = false;
    this.router.navigate(['/chat', roomId]);
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
