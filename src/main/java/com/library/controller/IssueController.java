package com.library.controller;

import com.library.model.Issue;
import com.library.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Указывает, что это контроллер, который возвращает данные в формате JSON
@RequestMapping("/api/issues") // Базовый путь для всех методов в этом контроллере
public class IssueController {

    @Autowired // Внедряет сервис для работы с выдачами
    private IssueService issueService;

    // Получить все выдачи
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // Получить выдачу по ID
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable int id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Получить выдачи по книге
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Issue>> getIssuesByBook(@PathVariable int bookId) {
        List<Issue> issues = issueService.getIssuesByBook(bookId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // Получить выдачи по читателю
    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<Issue>> getIssuesByReader(@PathVariable int readerId) {
        List<Issue> issues = issueService.getIssuesByReader(readerId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // Получить выдачи по сотруднику
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Issue>> getIssuesByEmployee(@PathVariable int employeeId) {
        List<Issue> issues = issueService.getIssuesByEmployee(employeeId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // Создать новую выдачу
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.saveIssue(issue);
        return new ResponseEntity<>(createdIssue, HttpStatus.CREATED);
    }

    // Обновить существующую выдачу
    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable int id, @RequestBody Issue issue) {
        Issue existingIssue = issueService.getIssueById(id);
        if (existingIssue != null) {
            issue.setId(id); // Убедимся, что ID обновляемой выдачи совпадает с переданным
            Issue updatedIssue = issueService.saveIssue(issue);
            return new ResponseEntity<>(updatedIssue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить выдачу по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable int id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            issueService.deleteIssue(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
