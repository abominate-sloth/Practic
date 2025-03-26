import { Component, OnInit } from '@angular/core';
import { IssueService } from '../../services/issue.service';
import { Issue, IssueCreateDTO} from '../../models/issue.model';
import { Book } from '../../models/book.model';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-issue-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './issue-form.component.html',
  styleUrls: ['./issue-form.component.css']
})
export class IssueFormComponent implements OnInit {
  issueId?: number;
  issueData: IssueCreateDTO = {
    bookId: 0,
    readerId: 0,
    employeeId: 0,
    issueDate: new Date().toISOString().split('T')[0],
    returnDate: undefined
  };
  books: Book[] = [];
  allUsers: User[] = []; // Все пользователи
  readerUsers: User[] = []; // Только читатели
  employeeUsers: User[] = []; // Только сотрудники

  constructor(
    private readonly issueService: IssueService,
    private readonly route: ActivatedRoute,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.loadData();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.issueId = +id;
      this.loadIssue(this.issueId);
    }
  }

  loadData(): void {
    // Загружаем книги
    this.issueService.getBooks().subscribe(books => {
      this.books = books;
    });

    // Загружаем всех пользователей
    this.issueService.getUsers().subscribe(users => {
      this.allUsers = users;

      // Фильтруем читателей (roleId = 3)
      this.readerUsers = this.allUsers.filter(user => user.role?.id != 1);

      this.employeeUsers = this.allUsers.filter(user => user.role?.id === 1);
    });
  }

  loadIssue(id: number): void {
    this.issueService.getIssue(id).subscribe(issue => {
      this.issueData = {
        bookId: issue.book.id,
        readerId: issue.reader.id,
        employeeId: issue.employee.id,
        issueDate: issue.issueDate,
        returnDate: issue.returnDate
      };
    });
  }

  saveIssue(): void {
    const operation = this.issueId
      ? this.issueService.updateIssue(this.issueId, this.issueData)
      : this.issueService.createIssue(this.issueData);

    operation.subscribe({
      next: () => this.router.navigate(['/issues']),
      error: (err) => console.error('Ошибка сохранения выдачи', err)
    });
  }
}
