package com.task.taskservice.util;

import com.task.taskservice.model.Task;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        int dueDateComparison = task1.getDueDate().compareTo(task2.getDueDate());
        if (dueDateComparison != 0) {
            return dueDateComparison;
        } else {
            return task2.getPriority().compareTo(task1.getPriority());
        }
    }
}

