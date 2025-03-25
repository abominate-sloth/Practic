package com.library.service;

import com.library.model.Issue;
import com.library.repository.IssueRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service // Указывает, что это сервис
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // Получить все выдачи
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // Фильтрация выдач по параметрам
    public List<Issue> filterIssues(Integer bookId, Integer readerId, Integer employeeId, Date issueDate, Date returnDate) {
        return issueRepository.findAll((Specification<Issue>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (bookId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("book").get("id"), bookId));
            }
            if (readerId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("reader").get("id"), readerId));
            }
            if (employeeId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("employee").get("id"), employeeId));
            }
            if (issueDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("issueDate"), issueDate));
            }
            if (returnDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("returnDate"), returnDate));
            }

            return predicate;
        });
    }

    // Сохранить выдачу
    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    // Удалить выдачу по ID
    public void deleteIssue(int id) {
        issueRepository.deleteById(id);
    }

    // Получить выдачу по ID
    public Issue getIssueById(int id) {
        return issueRepository.findById(id).orElse(null);
    }
}