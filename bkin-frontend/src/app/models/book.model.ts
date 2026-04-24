export interface Genre {
  id: number;
  name: string;
  description: string;
}

export interface Book {
  id: number;
  title: string;
  author: string;
  isbn: string;
  description: string;
  coverImageUrl: string;
  publishedDate: string;
  genre: Genre | null;
  averageRating: number;
  totalReviews: number;
}

export interface UserBookStatus {
  status: 'WANT_TO_READ' | 'READING' | 'COMPLETED';
  rating: number | null;
  review: string | null;
}
