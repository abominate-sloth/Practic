package com.library.service;

import com.library.model.Issue;
import com.library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class IssueService {

    @Autowired // Внедряет репозиторий
    private IssueRepository issueRepository;

    // Получить все выдачи
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // Получить выдачу по ID
    public Issue getIssueById(int id) {
        return issueRepository.findById(id).orElse(null);
    }

    // Получить выдачи по книге
    public List<Issue> getIssuesByBook(int bookId) {
        return issueRepository.findByBookId(bookId);
    }

    // Получить выдачи по читателю
    public List<Issue> getIssuesByReader(int readerId) {
        return issueRepository.findByReaderId(readerId);
    }

    // Получить выдачи по сотруднику
    public List<Issue> getIssuesByEmployee(int employeeId) {
        return issueRepository.findByEmployeeId(employeeId);
    }

    // Сохранить выдачу
    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    // Удалить выдачу по ID
    public void deleteIssue(int id) {
        issueRepository.deleteById(id);
    }
}
