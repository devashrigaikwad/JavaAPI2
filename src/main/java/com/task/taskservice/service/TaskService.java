package com.task.taskservice.service;

import com.task.taskservice.model.Task;
import com.task.taskservice.model.TaskStatistics;
import com.task.taskservice.model.TaskStatus;

import java.util.Date;
import java.util.List;

public interface TaskService {
    Task createTask(Task task, Long userId);

    Task updateTask(Long taskId, Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    Task assignTaskToUser(Long taskId, Long userId);

    List<Task> getTasksByUserId(Long userId);

    Task setTaskProgress(Long taskId, int progress);

    List<Task> getOverdueTasks();

    List<Task> getTasksByStatus(TaskStatus status);

    List<Task> getCompletedTasksByDateRange(Date startDate, Date endDate);

    TaskStatistics getTaskStatistics();

    List<Task> getTasksInPriorityOrder();
}
