import {Genre} from '../models/genre.model'; // Импортируем интерфейсы
import {Author} from '../models/author.model'; // Импортируем интерфейсы

export interface Book {
  id: number;
  title: string;
  genreId: number | null; // Идентификатор жанра
  genre?: Genre | null; // Объект жанра (опционально, если нужен для отображения)
  publishYear: number;
  isbn: string;
  copiesAvailable: number;
  authors: Author[]; // Авторы (массив объектов Author)
}


