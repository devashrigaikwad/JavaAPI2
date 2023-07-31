package com.task.taskservice.repository;
import com.task.taskservice.model.Task;
import com.task.taskservice.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByDueDateBeforeAndStatusNot(Date currentDate, TaskStatus status);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCompletedDateBetween(Date startDate, Date endDate);

    long countByStatus(TaskStatus status);

}

