import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../models/review.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {
  reviews: Review[] = [];

  constructor(private readonly reviewService: ReviewService) {}

  ngOnInit(): void {
    this.loadReviews();
  }

  loadReviews(): void {
    this.reviewService.getReviews().subscribe({
      next: (reviews) => {
        this.reviews = reviews || [];
      },
      error: (err) => {
        console.error('Ошибка загрузки отзывов', err);
        this.reviews = [];
      }
    });
  }

  deleteReview(id: number): void {
    if (confirm('Удалить отзыв?')) {
      this.reviewService.deleteReview(id).subscribe({
        next: () => this.reviews = this.reviews.filter(r => r.id !== id),
        error: (err) => console.error('Ошибка удаления отзыва', err)
      });
    }
  }
}
