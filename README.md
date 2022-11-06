### Project: Student Result Management

## Project Requirement:

We need to create simple Backend for result management for students.
Position of Student will be depending on the number of marks, student with highest
marks will be on number one position.
The Students Results will be posted in JSON format to websocket.
The Passing marks percentage will be 50 and remarks value will be passed/failed
depending on the total marks and obtained marks field values.
Roll Number must be validated from students’ collection with active status and
Grade.
Every time a new request will come, the data will be inserted in mongodb. The
position attribute will be updated accordingly e.g., if there are two records already inserted
in database and one new record came with more marks then the previous users then the
new user will get the first position and the other users will get 2 nd and 3 rd positions
respectively according to their marks.
send the appropriate response message on the websocket.

## Technologies Used:
- Spring Boot Reactive (Java)
- Spring Security (JWT strategy)
- MongoDB
- Reactive Websocket


## High Level Design:


 ![image](https://user-images.githubusercontent.com/63060197/200158254-5760ea2b-dae5-46ee-8c7f-84a27ad72247.png)
 


## API Design:

There are APIs related to student and result have been exposed to perform the operation.

.route(RequestPredicates.POST("/students")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::saveStudent)
.andRoute(RequestPredicates.GET("/students/{rollNumber}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::findStudentByRollNumber)
.andRoute(RequestPredicates.GET("/students")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::findAllStudents)
.andRoute(RequestPredicates.DELETE("/students/{rollNumber}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::deleteStudentByRollNumber)
.andRoute(RequestPredicates.POST("/results")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), studentManagementHandler::saveResult)
.andRoute(RequestPredicates.GET("/students/result/{rollNumber}")


## Database Design:

MongoDB used to store the student and result data.
Two collections namely, Student and Result have been created with below fields:
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "student")
public class Student {
    @Id
    private String _id;
    private String name;
    private int rollNumber;
    private String fatherName;
    private int grade;
    private String status;
    @CreatedDate
    private Instant createdOn;
    @LastModifiedDate
    private Instant updatedOn;
}

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    private String _id;
    @NonNull
    private int totalMarks;
    @NonNull
    private int obtainedMarks;
    @NonNull
    private int rollNumber;
    private int grade;
    private String remarks;
    private int positionInClass;
    private Instant createdOn;
    private Instant updatedOn;
    private Object test;

}


## Security:

JWT authentication and authorization have been used for verification. Although I have manged to have one user but going forward multiple users can be stored and can be authorized.

## Websocket API:

There is WebSocket API which will open a channel which will take the result data and using sink it will publish the data so that the result data can be subscribe by client.
