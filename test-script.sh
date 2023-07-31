#!/bin/bash

# Run the Spring Boot application
mvn spring-boot:run &
APP_PID=$!

# Wait for the application to start
sleep 15

# Create a task
CREATE_TASK_RESPONSE=$(curl -X POST -H "Content-Type: application/json" -d '{"title":"Sample Task","description":"This is a sample task.","dueDate":"2023-08-15"}' http://localhost:8080/api/tasks)
TASK_ID=$(echo $CREATE_TASK_RESPONSE | jq -r '.id')
echo "Task created with ID: $TASK_ID"

# Update the task
curl -X PUT -H "Content-Type: application/json" -d '{"title":"Updated Task","description":"This task has been updated.","dueDate":"2023-08-20","status":"Completed"}' http://localhost:8080/api/tasks/$TASK_ID

# Assign the task to a user
curl -X POST -H "Content-Type: application/json" -d '12345' http://localhost:8080/api/tasks/$TASK_ID/assign

# Get all tasks
curl http://localhost:8080/api/tasks

# Get the task by ID
curl http://localhost:8080/api/tasks/$TASK_ID

# Get tasks for a specific user
curl http://localhost:8080/api/tasks/users/12345/tasks

# Set task progress
curl -X PUT -H "Content-Type: application/json" -d '50' http://localhost:8080/api/tasks/$TASK_ID/progress

# Get overdue tasks
curl http://localhost:8080/api/tasks/overdue

# Get tasks by status
curl http://localhost:8080/api/tasks/status/COMPLETED

# Get completed tasks by date range
curl "http://localhost:8080/api/tasks/completed?startDate=2023-08-01&endDate=2023-08-31"

# Get task statistics
curl http://localhost:8080/api/tasks/statistics

# Get priority tasks
curl http://localhost:8080/api/tasks/priority

# Stop the Spring Boot application
kill -9 $APP_PID
