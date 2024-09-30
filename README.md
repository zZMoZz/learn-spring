## Explore Spring Boot
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