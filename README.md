# Document Management System

This project is a Document Management System developed using Java Spring and Spring Boot. It provides a RESTful API for managing documents, posts, and comments.

## Requirements

- Java 8 or higher
- Gradle

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/document-management-system.git

2. Build the project using Gradle:

    ```bash
    cd document-management-system
    ./gradlew build


## Usage
1. Run the application:

    ```bash
    ./gradlew bootRun
2. The application will start on http://localhost:8080.
3. H2 console is accessible on http://localhost:8080/h2-console 

3. Use an API testing tool like cURL, Postman, or any other HTTP client to interact with the API endpoints.

## API Endpoints
* POST /documents: Create a new document.
* GET /documents: Get all documents.
* GET /documents/{documentId}: Download File
* DELETE /documents/{documentId}: Delete Document
* GET /documents/{documentId}/posts: Get posts associated with a document.
* POST /documents/{documentId}/comments: Create a comment for a document.

## Non-Functional Requirements
This application adheres to the following non-functional requirements:

1. **RESTFull API Best Practices**: The system's APIs are designed and implemented following RESTful API best practices, including the use of appropriate HTTP verbs, resource naming conventions, and response codes.
2. **Document Format**: The system supports the PDF format for uploaded documents. Users can upload PDF documents and perform various actions on them.
3. **Separation of Front-End and Back-End**: The front-end part of the system will be developed by a dedicated team. The provided APIs are meant to serve as the back-end functionality to be consumed by the front-end. The APIs provide the necessary functionality for document management, posts, and comments.
4. **Java Spring Implementation**: The system is implemented using Java Spring, which provides a robust framework for building scalable and reliable applications. It leverages the Spring Boot framework for easy setup and configuration.
5. **Git Repository Delivery**: The final deliverable will be shared with the Tech Lead through a Git repository. The repository will contain the complete source code, including the necessary configuration files and documentation.


## Testing
To run the unit tests, execute the following command:

      ./gradlew test

## License
This project is licensed under the MIT License.

      Please review the updated `README.md` file and let me know if there's anything else you