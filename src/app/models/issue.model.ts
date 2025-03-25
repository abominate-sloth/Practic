import { Book } from './book.model';
import { User } from './user.model';

export interface Issue {
  id: number;
  book: Book;
  reader: User;
  employee: User;
  issueDate: string; // или Date, если будет преобразование
  returnDate?: string; // опционально
}

export interface IssueCreateDTO {
  bookId: number;
  readerId: number;
  employeeId: number;
  issueDate: string;
  returnDate?: string;
}
