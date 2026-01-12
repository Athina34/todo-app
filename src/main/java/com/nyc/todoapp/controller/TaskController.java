package com.nyc.todoapp.controller;

import com.nyc.todoapp.model.Task;
import com.nyc.todoapp.model.User;
import com.nyc.todoapp.repository.UserRepository;
import com.nyc.todoapp.repository.TaskRepository;
import com.nyc.todoapp.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, UserRepository userRepository, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String listTasks(Model model, Principal principal,
                            @RequestParam(required = false) String category,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Στατιστικά Dashboard (Innovation Feature)
        model.addAttribute("totalPending", taskRepository.countByUserAndCompleted(user, false));
        model.addAttribute("completedToday", taskRepository.countByUserAndCompleted(user, true));
        model.addAttribute("highPriority", taskRepository.countByUserAndPriorityAndCompleted(user, "High", false));
        model.addAttribute("dueToday", taskRepository.countByUserAndDueDateAndCompleted(user, LocalDate.now(), false));

        List<Task> tasks;
        if (category != null && !category.isEmpty() && dueDate != null) {
            tasks = taskRepository.findByUserAndCategoryContainingAndDueDate(user, category, dueDate);
        } else if (category != null && !category.isEmpty()) {
            tasks = taskRepository.findByUserAndCategoryContaining(user, category);
        } else if (dueDate != null) {
            tasks = taskRepository.findByUserAndDueDate(user, dueDate);
        } else {
            tasks = taskRepository.findByUser(user);
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("category", category);
        model.addAttribute("dueDate", dueDate);
        return "tasks/index";
    }

    @PostMapping
    public String saveTask(@ModelAttribute("task") Task task, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        taskService.saveTask(task);
        return "redirect:/tasks"; // ΔΙΟΡΘΩΣΗ: Πάντα redirect στο URL
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/task-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTaskCompletion(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskService.saveTask(task);
        }
        return "redirect:/tasks";
    }
}