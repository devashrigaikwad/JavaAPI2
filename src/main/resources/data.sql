-- Create sample users
INSERT INTO users (name, id) VALUES ('John Doe', '1');
INSERT INTO users (name, id) VALUES ('Jane Smith', '2');
INSERT INTO users (name, id) VALUES ('Sample User', '3');

-- Insert sample tasks
INSERT INTO tasks (title, description, due_date, status, completed_date, user_id, progress, priority)
VALUES ('Sample Task 1', 'This is a sample task 1.', '2023-08-15', 'COMPLETED', '2023-08-20', 1, 50, 'High');

INSERT INTO tasks (title, description, due_date, status, completed_date, user_id, progress, priority)
VALUES ('Sample Task 2', 'This is a sample task 2.', '2023-08-20', 'IN_PROGRESS', NULL, 2, 25, 'Medium');

INSERT INTO tasks (title, description, due_date, status, completed_date, user_id, progress, priority)
VALUES ('Sample Task 3', 'This is a sample task 3.', '2023-08-25', 'TODO', NULL, NULL, 3, 'Low');

