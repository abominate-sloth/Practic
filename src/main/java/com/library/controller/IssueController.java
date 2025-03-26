package com.library.controller;

import com.library.dto.*;
import com.library.model.Issue;
import com.library.service.IssueService;
import com.library.service.BookService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public IssueController(IssueService issueService, BookService bookService, UserService userService) {
        this.issueService = issueService;
        this.bookService = bookService;
        this.userService = userService;
    }

    // Фильтрация выдач
    @GetMapping
    public ResponseEntity<List<IssueResponseDTO>> filterIssues(
            @RequestParam(required = false) Integer bookId,
            @RequestParam(required = false) Integer readerId,
            @RequestParam(required = false) Integer employeeId,
            @RequestParam(required = false) Date issueDate,
            @RequestParam(required = false) Date returnDate) {

        List<Issue> issues = issueService.filterIssues(bookId, readerId, employeeId, issueDate, returnDate);
        List<IssueResponseDTO> dtos = issues.stream()
                .map(this::convertToResponseDTO)
                .toList(); // Изменено с collect(Collectors.toList())
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Получить выдачу по ID
    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDTO> getIssueById(@PathVariable Integer id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            return new ResponseEntity<>(convertToResponseDTO(issue), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Создать выдачу
    @PostMapping
    public ResponseEntity<IssueResponseDTO> createIssue(@RequestBody IssueRequestDTO issueDTO) {
        Issue issue = convertToEntity(issueDTO);
        Issue createdIssue = issueService.saveIssue(issue);
        return new ResponseEntity<>(convertToResponseDTO(createdIssue), HttpStatus.CREATED);
    }

    // Обновить выдачу
    @PutMapping("/{id}")
    public ResponseEntity<IssueResponseDTO> updateIssue(
            @PathVariable Integer id,
            @RequestBody IssueRequestDTO issueDTO) {

        Issue existingIssue = issueService.getIssueById(id);
        if (existingIssue != null) {
            Issue issue = convertToEntity(issueDTO);
            issue.setId(id);
            Issue updatedIssue = issueService.saveIssue(issue);
            return new ResponseEntity<>(convertToResponseDTO(updatedIssue), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Удалить выдачу
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Integer id) {
        issueService.deleteIssue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Методы преобразования
    private Issue convertToEntity(IssueRequestDTO dto) {
        Issue issue = new Issue();
        issue.setBook(bookService.getBookById(dto.getBookId()));
        issue.setReader(userService.getUserById(dto.getReaderId()));
        issue.setEmployee(userService.getUserById(dto.getEmployeeId()));
        issue.setIssueDate(dto.getIssueDate());
        issue.setReturnDate(dto.getReturnDate());
        return issue;
    }

    private IssueResponseDTO convertToResponseDTO(Issue issue) {
        BookSimpleDTO bookDTO = new BookSimpleDTO(issue.getBook().getId(), issue.getBook().getTitle());
        UserSimpleDTO readerDTO = new UserSimpleDTO(issue.getReader().getId(), issue.getReader().getUsername());
        UserSimpleDTO employeeDTO = new UserSimpleDTO(issue.getEmployee().getId(), issue.getEmployee().getUsername());

        return new IssueResponseDTO(
                issue.getId(),
                bookDTO,
                readerDTO,
                employeeDTO,
                issue.getIssueDate(),
                issue.getReturnDate()
        );
    }
}