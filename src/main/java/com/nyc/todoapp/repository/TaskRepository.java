package com.nyc.todoapp.repository;

import com.nyc.todoapp.model.Task;
import com.nyc.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndCategoryContaining(User user, String category);
    List<Task> findByUserAndDueDate(User user, LocalDate dueDate);
    List<Task> findByUserAndCategoryContainingAndDueDate(User user, String category, LocalDate dueDate);

    // Μέθοδοι για τα στατιστικά του Dashboard
    long countByUserAndCompleted(User user, boolean completed);
    long countByUserAndPriorityAndCompleted(User user, String priority, boolean completed);
    long countByUserAndDueDateAndCompleted(User user, LocalDate date, boolean completed);
}