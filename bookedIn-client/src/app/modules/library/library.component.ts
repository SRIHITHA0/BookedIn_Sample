import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

// Define the interface here so we don't need a separate file
export interface LibraryEntryDTO {
  id: number;
  shelf: string;
  progressPct: number;
  bookTitle: string;
  bookCoverUrl: string;
  bookAuthor: string;
  startedAt: string;
}

@Component({
  selector: 'app-library',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {
  readingBooks: LibraryEntryDTO[] = [];
  completedBooks: LibraryEntryDTO[] = [];
  showChat = false;
  isSidebarCollapsed = true;

  // Inject HttpClient directly into the constructor
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Replace '1' with your actual user ID from the database
    this.fetchLibraryData(1);
  }

  fetchLibraryData(userId: number): void {
    const apiUrl = `http://localhost:9090/api/library/${userId}`;

    this.http.get<LibraryEntryDTO[]>(apiUrl).subscribe({
      next: (data) => {
        // Filter the database results into the correct shelves
        this.readingBooks = data.filter(item => item.shelf === 'Reading');
        this.completedBooks = data.filter(item => item.shelf === 'Completed');
      },
      error: (err) => {
        console.error('API Connection Failed:', err);
      }
    });
  }

  toggleSidebar(): void {
    this.isSidebarCollapsed = !this.isSidebarCollapsed;
  }

  toggleChat(): void {
    this.showChat = !this.showChat;
  }
}
