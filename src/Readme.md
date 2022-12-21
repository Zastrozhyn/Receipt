# Receipt writer
This is a service for displaying a finished check on the console, calculating discounts depending on the discount card and whether the product is promotional.

## Technology stack:

- Spring core
- Stream API
- Java 17
- Gradle
- JUnit
- Git

## Design patterns and principles used:

- SOLID
- MVC
- Builder
- Factory
- Decorator

The application is launched from the console with input parameters. If there are no input parameters, the data is read from the "product.txt" file. The service outputs the finished check to the console, as well as writing it to the "output.txt" file. Exceptions are processed in case of entering a non-existent product or discount card.