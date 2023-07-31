package com.task.taskservice.controller;

import com.task.taskservice.model.Task;
import com.task.taskservice.model.TaskStatistics;
import com.task.taskservice.model.TaskStatus;
import com.task.taskservice.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestParam(name = "userId", required = false) Long userId) {
        Task createdTask = taskService.createTask(task, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @RequestBody Long userId) {
        Task task = taskService.assignTaskToUser(taskId, userId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/progress")
    public ResponseEntity<Task> setTaskProgress(@PathVariable Long taskId, @RequestBody int progress) {
        Task updatedTask = taskService.setTaskProgress(taskId, progress);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> overdueTasks = taskService.getOverdueTasks();
        return new ResponseEntity<>(overdueTasks, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<Task> tasksByStatus = taskService.getTasksByStatus(status);
        return new ResponseEntity<>(tasksByStatus, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasksByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        List<Task> completedTasks = taskService.getCompletedTasksByDateRange(startDate, endDate);
        return new ResponseEntity<>(completedTasks, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<TaskStatistics> getTaskStatistics() {
        TaskStatistics taskStatistics = taskService.getTaskStatistics();
        return new ResponseEntity<>(taskStatistics, HttpStatus.OK);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<Task>> getPriorityTasks() {
        List<Task> priorityTasks = taskService.getTasksInPriorityOrder();
        return new ResponseEntity<>(priorityTasks, HttpStatus.OK);
    }
}

