import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { Review, ReviewCreateDTO} from '../../models/review.model';
import { Book } from '../../models/book.model';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {
  reviewId?: number;
  reviewData: ReviewCreateDTO = {
    bookId: 0,
    userId: 0,
    rating: 1,
    comment: ''
  };
  books: Book[] = [];
  users: User[] = [];
  isLoading = false;
  errorMessage = '';

  constructor(
    private readonly reviewService: ReviewService,
    private readonly route: ActivatedRoute,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.loadBooksAndUsers();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.reviewId = +id;
      this.loadReview(this.reviewId);
    }
  }

  loadBooksAndUsers(): void {
    this.isLoading = true;

    this.reviewService.getBooks().subscribe({
      next: (books) => {
        this.books = books;
        this.loadUsers();
      },
      error: (err) => {
        console.error('Failed to load books:', err);
        this.isLoading = false;
        this.errorMessage = 'Failed to load books';
      }
    });
  }

  loadUsers(): void {
    this.reviewService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Failed to load users:', err);
        this.isLoading = false;
        this.errorMessage = 'Failed to load users';
      }
    });
  }

  loadReview(id: number): void {
    this.isLoading = true;

    this.reviewService.getReview(id).subscribe({
      next: (review) => {
        this.reviewData = {
          bookId: review.book.id,
          userId: review.user.id,
          rating: review.rating,
          comment: review.comment
        };
        this.isLoading = false;
      },
      error: (err) => {
        console.error(`Failed to load review ${id}:`, err);
        this.isLoading = false;
        this.errorMessage = 'Failed to load review';
      }
    });
  }

  saveReview(): void {
    if (this.isLoading) return;

    this.isLoading = true;

    const operation = this.reviewId
      ? this.reviewService.updateReview(this.reviewId, this.reviewData)
      : this.reviewService.createReview(this.reviewData);

    operation.subscribe({
      next: () => {
        this.router.navigate(['/reviews']);
      },
      error: (err) => {
        console.error('Failed to save review:', err);
        this.isLoading = false;
        this.errorMessage = 'Failed to save review';
      }
    });
  }
}
