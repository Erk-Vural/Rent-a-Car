## Rent A Car Application

RentACar is a sophisticated back-end application designed with a layered architecture, serving as a foundational project for Spring framework, back-end development, and Java clean code exercises.

### Crow's Foot Diagram for RentACar
![RentACar Crow's Foot Diagram](rentacar(crow's%20foot%20diagram).png)

### UML Class Diagram for RentACar
![RentACar UML Class Diagram](rentacar(UML%20class%20diagram).png)

### Key Features and Notes:

- **Caching Strategy**: Performance enhancement is achieved through caching, particularly implemented in Controller's `getAll()` methods. Since `getAll()` responses are relatively static, caching proves to be beneficial. The `ApplicationCacheConfig` within `core/config` facilitates the creation of a `cacheManager`, with `ConcurrentMapCaches` assigned for datasets necessitating caching.

### Technologies Utilized:

- **Spring Boot Starter Web**: Facilitates rapid development of web applications with Spring MVC.
- **Spring Boot Starter Data JPA**: Simplifies the implementation of JPA-based repositories.
- **Spring Boot Starter Test**: Supports testing Spring Boot applications.
- **PostgreSQL**: Utilized as the relational database management system for data storage.
- **Lombok**: Aids in reducing boilerplate code in Java classes.
- **ModelMapper**: Simplifies object mapping and conversion between different model types.
- **OpenAPI UI**: Offers a user-friendly interface for exploring and testing the application's APIs.
- **Spring Boot Starter Cache**: Enables caching capabilities within the Spring Boot application.
- **Spring Context**: Provides core Spring framework support.
