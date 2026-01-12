package com.nyc.todoapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate dueDate;

    private String priority; // π.χ. High, Medium, Low

    private String category;

    private boolean completed = false; // Οπτική διάκριση ολοκληρωμένων [cite: 33]

    // ΣΥΝΔΕΣΗ ΜΕ ΧΡΗΣΤΗ: Κάθε task ανήκει σε έναν χρήστη (Many Tasks to One User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor για γρήγορη δημιουργία
    public Task(String title, String description, LocalDate dueDate, String priority, String category, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.user = user;
    }
}