package com.task.taskservice.service;

import com.task.taskservice.model.Task;
import com.task.taskservice.model.TaskStatistics;
import com.task.taskservice.model.TaskStatus;
import com.task.taskservice.repository.TaskRepository;
import com.task.taskservice.user.User;
import com.task.taskservice.user.UserRepository;
import com.task.taskservice.util.PriorityComparator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(Task task, Long userId) {
        if (userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                task.setUser(userOptional.get());
            } else {
                throw new RuntimeException("User not found with ID: " + userId);
            }
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setStatus(task.getStatus());

            if (task.getStatus() == TaskStatus.COMPLETED) {
                existingTask.setCompletedDate(new Date());
            } else {
                existingTask.setCompletedDate(null);
            }

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElse(null);
    }

    @Override
    public Task assignTaskToUser(Long taskId, Long userId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                task.setUser(userOptional.get());
                return taskRepository.save(task);
            } else {
                throw new RuntimeException("User not found with ID: " + userId);
            }
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public Task setTaskProgress(Long taskId, int progress) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setProgress(progress);
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    @Override
    public List<Task> getOverdueTasks() {
        Date currentDate = new Date();
        return taskRepository.findByDueDateBeforeAndStatusNot(currentDate, TaskStatus.COMPLETED);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getCompletedTasksByDateRange(Date startDate, Date endDate) {
        return taskRepository.findByCompletedDateBetween(startDate, endDate);
    }

    @Override
    public TaskStatistics getTaskStatistics() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);
        double percentageCompleted = 0.0;

        if (totalTasks > 0) {
            percentageCompleted = (double) completedTasks / totalTasks * 100.0;
        }

        return new TaskStatistics(totalTasks, completedTasks, percentageCompleted);
    }

    @Override
    public List<Task> getTasksInPriorityOrder() {
        List<Task> allTasks = taskRepository.findAll();
        PriorityQueue<Task> priorityQueue = new PriorityQueue<>(new PriorityComparator());
        priorityQueue.addAll(allTasks);

        List<Task> tasksInPriorityOrder = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            tasksInPriorityOrder.add(priorityQueue.poll());
        }
        return tasksInPriorityOrder;
    }
}

