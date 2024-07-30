# **Notification-Service**

## **Description**

This service is responsible for sending notifications to users.

---

## **Key Features**

- **Interaction with Other Services**: By sending messages through Kafka, the service can receive messages from other
  services. Even if one of the services is down, data will not be lost.

---

## **Technologies**

- **Spring Boot**: The primary framework for developing the service.
- **Kafka**: Messaging system used for sending and receiving notifications.
- **Docker**: Containerization of the service for easy deployment.

---

## **Installation Guide**

### **Prerequisites**

Ensure Docker is installed on your system.

### **Steps to Install and Run**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd notification-service
   
2. **Build the Docker Image**
   Create a Docker image for your service:
    ```bash
   docker build -t notification-service .
   
3. **Run Docker Compose**
   If you have a docker-compose.yml, start it to deploy all necessary services:
   ```bash
   docker-compose up
   
4. **Access the Application**
Once the Docker containers are running, access the application at http://localhost:8000.

5. **Configure Application Properties**
After installation, configure the application properties (e.g., application.properties or application.yml) to set environment-specific parameters such as email server settings.
 
---

## **Additional Information**

- **Security Best Practices**: Make sure to regularly update your dependencies and monitor for security vulnerabilities.
- **Monitoring and Logging**: Consider setting up monitoring and logging tools to keep track of service performance and issues.
- **Scalability**: For production environments, ensure that you have proper scaling strategies in place for your service and associated dependencies.
- **Integration with IAM-Service**: The Notification Service should be used in conjunction with the IAM-Service. Ensure that both services are correctly configured and connected. IAM-Service has its own Dockerfile for installation.

---

## **Conclusion**

This service provides robust notification functionalities, integrated with Kafka for reliable message handling. It ensures that notifications are sent effectively, even in the event of service interruptions.

---
