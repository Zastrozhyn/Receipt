# Receipt writer
This is a service for displaying a finished check on the console, calculating discounts depending on the discount card and whether the product is promotional.

3 versions are available: 

 - The "embadded_DB" branch implements the storage of products directly in the program code.
 - Branch "PostgreSQL" - data is stored and processed in PostgreSQL
 - The "REST" branch is a rest application based on Spring Boot with the following endpoints:
   
1)http://localhost:8080/products - you can get information about products
   
2)http://localhost:8080/products/create - create a receipt by transferring products and a discount card
   
3)http://localhost:8080/cards - information about discount cards
   Exceptions were also handled (absence of a product or a discount card) using
   ExceptionControllerAdviser.

## Technology stack:
- Spring core
- Spring JDBC
- Spring Boot
- Stream API
- Java 17
- Gradle
- JUnit
- Git
- PostgreSql

## Design patterns and principles used:

- SOLID
- MVC
- Builder
- Factory
- Decorator

The application is launched from the console with input parameters. If there are no input parameters, the data is read from the "product.txt" file. The service outputs the finished check to the console, as well as writing it to the "output.txt" file. Exceptions are processed in case of entering a non-existent product or discount card.