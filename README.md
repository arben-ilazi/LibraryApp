# **Library Management System**

This is a Java-based Library Management System (LMS) project developed using Spring Boot framework to efficiently manage library operations. The system provides functionalities for librarians to manage books, borrowers, transactions, and more.

## **Features**

- **Book Management**: Add, remove, update, and search for books in the library inventory.
- **Loan Management**: Register new loan, update loan information, search loan and delete loan.
- **Author Management**: Add author, search, update and delete.
- **Genre Management**: Add genre, search, update and delete.


## **Technologies Used**

- IntelliJ IDEA
- Java
- Spring Boot
- Xampp
- SQL (for database management)
  

## **Getting Started**

To run the Library Management System project on your local machine, follow these steps:

1. **Clone the Repository**: `git clone https://github.com/arben-ilazi/LibraryApp.git`
2. **Database Configuration**: Set up a MySQL database and configure the connection properties in `application.properties` file.
3. **Build and Run**: Navigate to the project directory and run `./gradlew bootRun` to start the Spring Boot application.

## **Usage**

Once the application is running, you can access it using Postman or Insomnia and perform various tasks that it offers.
## **Contributing**

Contributions are welcome! If you'd like to contribute to the development of this project, please fork the repository and submit a pull request with your changes.


## **Acknowledgments**

- This project was developed as part of a Java programming course.

## **Endpoints**

### Authors

- **Get all authors**
  - Method: GET
  - Endpoint: `http://localhost:8080/authors`
  - Response:
    ```json
    [
        {
            "authorId": 1,
            "name": "Stephan Curry",
            "biography": "Something better"
        },
        {
            "authorId": 2,
            "name": "Fernando Torres",
            "biography": "Something cool”
        }
    ]
    ```

- **Get author by ID**
  - Method: GET
  - Endpoint: `http://localhost:8080/authors/{id}`
  - Response:
    ```json
    {
        "authorId": 1,
        "name": "Stephan Curry",
        "biography": "Something better"
    }
    ```

- **Create author**
  - Method: POST
  - Endpoint: `http://localhost:8080/authors`
  - Request Body:
    ```json
    {
        "name": "Bedjet Pacolli",
        "biography": "The Fighter"
    }
    ```

- **Update author**
  - Method: PUT
  - Endpoint: `http://localhost:8080/authors/{id}`
  - Request Body:
    ```json
    {
        "biography": "The Fighter"
    }
    ```

- **Delete author**
  - Method: DELETE
  - Endpoint: `http://localhost:8080/authors/{id}`

### Genres

- **Get all genres**
  - Method: GET
  - Endpoint: `http://localhost:8080/genres`
  - Response:
    ```json
    [
        {
            "genreId": 2,
            "genreName": "Novel"
        },
        {
            "genreId": 4,
            "genreName": "Thriller"
        }
    ]
    ```

- **Get genre by ID**
  - Method: GET
  - Endpoint: `http://localhost:8080/genres/{id}`
  - Response:
    ```json
    {
        "genreId": 18,
        "genreName": "Romance"
    }
    ```

- **Create genre**
  - Method: POST
  - Endpoint: `http://localhost:8080/genres`
  - Request Body:
    ```json
    {
        "genreName": "Horror"
    }
    ```

- **Update genre**
  - Method: PUT
  - Endpoint: `http://localhost:8080/genres/{id}`
  - Request Body:
    ```json
    {
        "genreName": "Horror"
    }
    ```

- **Delete genre**
  - Method: DELETE
  - Endpoint: `http://localhost:8080/genres/{id}`

### Books

- **Get all books**
  - Method: GET
  - Endpoint: `http://localhost:8080/books`
  - Response:
    ```json
    {
        "bookId": 10,
        "title": "Love",
        "author": {
            "authorId": 11,
            "name": "Isaac Asimov",
            "biography": "The Fighter"
        },
        "genre": {
            "genreId": 16,
            "genreName": "Roman"
        }
    }
    ```

- **Get book by ID**
  - Method: GET
  - Endpoint: `http://localhost:8080/books/{id}`
  - Response:
    ```json
    {
        "bookId": 10,
        "title": "Smore",
        "author": {
            "authorId": 11,
            "name": "Isaac Asimov",
            "biography": "The Fighter"
        },
        "genre": {
            "genreId": 16,
            "genreName": "Roman"
        }
    }
    ```

- **Create book**
  - Method: POST
  - Endpoint: `http://localhost:8080/books`
  - Request Body:
    ```json
    {
        "title": "Neo",
        "author": {
            "authorId": 13
        },
        "genre": {
            "genreId": 8
        }
    }
    ```

- **Update book**
  - Method: PUT
  - Endpoint: `http://localhost:8080/books/{id}`
  - Request Body:
    ```json
    {
        "title": "Win",
        "author": {
            "authorId": 5
        },
        "genre": {
            "genreId": 2
        }
    }
    ```

- **Delete book**
  - Method: DELETE
  - Endpoint: `http://localhost:8080/books/{id}`

### Members

- **Get all members**
  - Method: GET
  - Endpoint: `http://localhost:8080/members`
  - Response:
    ```json
    {
        "memberId": 1,
        "name": "Smoth Bujkovski",
        "email": "Smoth@gmail.com",
        "phone": "+389 70 3213213",
        "address": "Rr. Brace Gjunovski"
    }
    ```

- **Get member by ID**
  - Method: GET
  - Endpoint: `http://localhost:8080/members/{id}`
  - Response:
    ```json
    {
        "memberId": 1,
        "name": "Smoth Bujkovski",
        "email": "Smoth@gmail.com",
        "phone": "+389 70 3213213",
        "address": "Rr. Brace Gjunovski"
    }
    ```

- **Create member**
  - Method: POST
  - Endpoint: `http://localhost:8080/members`
  - Request Body:
    ```json
    {
        "name": "Nazif Destani",
        "email": "Nazif@gmail.com",
        "phone": "+389 72 9173562",
        "address": "Rr Bb 522"
    }
    ```

- **Update member**
  - Method: PUT
  - Endpoint: `http://localhost:8080/members/{id}`
  - Request Body:
    ```json
    {
        "name": "Blerim Destani",
        "email": "T@gmail.com",
        "phone": "+389 70 775798",
        "address": "Rr. bb 2"
    }
    ```

- **Delete member**
  - Method: DELETE
  - Endpoint: `http://localhost:8080/members/{id}`

### Loans

- **Get all loans**
  - Method: GET
  - Endpoint: `http://localhost:8080/loans`
  - Response:
    ```json
    {
        "loanId": 7,
        "loanDate": "14-12-2022",
        "dueDate": "20-05-2023",
        "book": {
            "bookId": 10,
            "title": "Tayna",
            "author": {
                "authorId": 11,
                "name": "Isaac Asimov",
                "biography": "The Fighter"
            },
            "genre": {
                "genreId": 16,
                "genreName": "Roman"
            }
        }
    }
    ```

- **Get loan by ID**
  - Method: GET
  - Endpoint: `http://localhost:8080/loans/{id}`
  - Response:
    ```json
    {
        "loanId": 11,
        "loanDate": "14-12-2022",
        "dueDate": "20-05-2023",
        "book": {
            "bookId": 10,
            "title": "Tayna",
            "author": {
                "authorId": 11,
                "name": "Isaac Asimov",
                "biography": "The Fighter"
            },
            "genre": {
                "genreId": 16,
                "genreName": "Roman"
            }
        }
    }
    ```

- **Create loan**
  - Method: POST
  - Endpoint: `http://localhost:8080/loans`
  - Request Body:
    ```json
    {
        "loanDate": "14-12-2007",
        "dueDate": "20-05-2022",
        "book": {
            "bookId": 11
        },
        "member": {
            "memberId": 8
        }
    }
    ```

- **Update loan**
  - Method: PUT
  - Endpoint: `http://localhost:8080/loans/{id}`
  - Request Body:
    ```json
    {
        "loanDate": "14-12-2023",
        "dueDate": "20-05-2024",
        "book": {
            "bookId": 11
        },
        "member": {
            "memberId": 8
        }
    }
    ```

- **Delete loan**
  - Method: DELETE
  - Endpoint: `http://localhost:8080/loans/{id}`

  
