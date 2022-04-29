# Rent A Car Application

RentACar is a layered Architecture Back-end Application. It's developed as a starter/basics project for Spring
framework, back-end development and Java clean code exercises.

### Notes:

- Enabled caching and implemented it in Controller's getAll() methods for increasing performance. getAll() method' s
  response not going to change much, so it is a good method to experiment with. Created **_ApplicationCacheConfig_** in
  core/config to create a cacheManager in it, I created **_ConcurrentMapCaches_** for every data set that I want to
  cache.

## Technologies Used

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-test
- postgresql
- lombok
- modelmapper
- openapi-ui
- spring-boot-starter-cache
- spring-context
