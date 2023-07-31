package com.task.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatistics {

    private long totalTasks;
    private long completedTasks;
    private double percentageCompleted;

}

