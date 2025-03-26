import { Component, OnInit } from '@angular/core';
import { IssueService } from '../../services/issue.service';
import { Issue } from '../../models/issue.model';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-issue-list',
  standalone: true,
  imports: [CommonModule, RouterModule, DatePipe],
  templateUrl: './issue-list.component.html',
  styleUrls: ['./issue-list.component.css']
})
export class IssueListComponent implements OnInit {
  issues: Issue[] = [];

  constructor(private readonly issueService: IssueService) {}

  ngOnInit(): void {
    this.loadIssues();
  }

  loadIssues(): void {
    this.issueService.getIssues().subscribe({
      next: (issues) => this.issues = issues,
      error: (err) => console.error('Ошибка загрузки выдач', err)
    });
  }

  deleteIssue(id: number): void {
    if (confirm('Удалить выдачу?')) {
      this.issueService.deleteIssue(id).subscribe({
        next: () => this.issues = this.issues.filter(i => i.id !== id),
        error: (err) => console.error('Ошибка удаления выдачи', err)
      });
    }
  }
}
