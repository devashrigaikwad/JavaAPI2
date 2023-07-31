# Task Service API Test

This repository contains a simple test script to test the Task Service API.

## Requirements

- Java 8 or above
- Maven
- Curl
- jq (Command-line JSON processor)

## How to Run the Test

1. Start the Spring Boot application using the following command:

   ```bash
   mvn spring-boot:run

2. ```
   chmod +x test-script.sh
   ./test-script.sh
3. After running the test script, stop the Spring Boot application.
    ```
   ps -ef | grep "mvn spring-boot:run" | grep -v grep | awk '{print $2}' | xargs kill
   ```