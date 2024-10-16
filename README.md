## Explore Spring Boot

### Table of Contents
1. [Spring Overview](#Spring-Overview)
2. [Spring Core](#Spring-Core)
3. [Spring JPA (Hibernate)](#Spring-JPA-(Hibernate))
4. [Spring Rest](#Spring-Rest)
5. [Spring Security](#Spring-Security)
6. [Spring MVC](#Spring-MVC)
7. [Spring MVC CRUD](#Spring-MVC-CRUD)

#### Spring Overview
- Make the server load automatically using dev-tools.
- Add basic spring security with specified username & password.
- Add actuator endpoints, exclude some, change prefix path.
- Add some information into "info" endpoint.
- Add custom properties, Edit some spring boot properties.
- Create simple rest controller to handle requests.

#### Spring Core
- Implement dependency injection with constructor, setter, field injection.
	- with java annotation configuration, `@Autowired`, `@Component`.
- Configure `@SpringBootApplication` to add further packages in component scanning process.
- Handle dependency injection process in case of multiple dependencies available.
	- using `@Qualifier`, `@Primary`
- Change bean scope of `TennisPlayer` bean and display it's influence using 'check' endpoint.
- Add initialization and destruction methods to `CricketCoach` bean.
- Configure spring container using Java-Based configuration (`swimCoach` bean).

#### Spring JPA (Hibernate)
- Connect to a database.
- Use `CommandLineRunner` bean.
- Create an entity class `Student`.
- Create an DAO class `StudentDAO`.
- Do CRUD (Create, Read, Update, Delete) operations.
	- write some specific queries for CRUD operations.
	- use Named Parameters in queries.
- Create database table automatically from our app.
	- using `create` value in `application.properties` file.
	- !Warning! everytime you run the app, tables dropped then created.

#### Spring Rest

How to design a REST API to handle database operations (CRUD).

- Setup database and connect to it.
- Create `Employee` POJO class and prepare it as entity.
- Implement `Employee` DAO & Service.
- Create Rest API controller to handle employee operations.
- Make specific Exception and handle it using global exception.
- Create the same project using `JpaRepository`, `Spring Data Rest` techniques.
- Configure and explore some `Spring Data Rest` properties and features.
	- Pagination, Sorting, Resource name.

#### Spring Security

Handling Authentication and Authorization process with help of spring security framework.

- Implement previous section again using `JpaRepository` with services (not provided in previous sections).
- Enable spring security by inject `spring-security` dependency.
	- change default username and password in `application.properties` file.
- Configure authentication with define users credentials (In Memory).
- Configure Authorization using `SecurityFilterChain`.
- Configure authentication with define users credentials (In Database).
	- Auto database access.
	- Customize database access.
- Use `Bcrypt` algorithm in storing user password.

#### Spring MVC

Implement MVC application with `Thymeleaf` template engine.

- Create MVC controller to handle client requests.
- Add `Thymeleaf` template engine dependency to can use with spring MVC.
- Apply some local CSS in our project.
- Perform lifecycle of form process.
	- create method handles client request.
	- create method handle form confirmation.
	- create two html files, one to show form, second to show confirm information.
	- use `Model` as data container.
	- create `Student` class to save returned data in submission form request.
	- know annotations like `@ModelAttribute`,`@RequestParam`.
- Practice several form inputs.
	- text, checkbox, radio, select.
	- avoid hard coding values with help of properties file.
- Do some data validation.
	- required fields, number range, regular expressions.
	- use `@InitBinder` to handle request parameters before binding process.
	- handle passing string to integer field errors.
	- Write validation error messages outside the code in `messages.properties` file.
- Do custom validation.

#### Spring MVC CRUD

- Reuse `EmployeeService` & `EmployeeRepository` from previous projects.
- Add css bootstrap styles from internet.
- Create a logic to get all employees.
- Create a logic to add an employee.
- Create a logic to update an employee.
- Create a logic to delete an employee.
- Add some validation rules.
- Sort results based on first name.
- Make index page redirect to `employees/list` url.
