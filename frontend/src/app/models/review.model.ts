import { Book } from '../models/book.model';
import { User } from '../models/user.model';

export interface Review {
  id: number;
  book: Book;
  user: User;  // Изменили с reader на user
  rating: number;
  comment: string;
}

export interface ReviewCreateDTO {
  bookId: number;
  userId: number;  // Изменили с readerId на userId
  rating: number;
  comment: string;
}
