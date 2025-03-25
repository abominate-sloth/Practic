import { Genre } from './genre.model';
import { Author } from './author.model';

export interface Book {
  id: number;
  title: string;
  genreId: number | null;
  genre?: Genre | null;
  publishYear: number;
  isbn: string;
  copiesAvailable: number;
  authors: Author[];
  authorIds?: number[];
  averageRating?: number | null; // Добавляем новое поле
}
