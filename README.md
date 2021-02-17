# Product Scraper

### Prerequisites
```
Java 11
Working Internet
```

### About
The project is divided into 4 modules:

## product-scraper-core
Contains the domain classes and represent the problem domain.
This module can be reused.

## product-scraper-schema
Schema files for what is to be rendered.
Contain classes which can convert domain objects to schema.

## product-scraper-application
Module which has the logic to connect, parse and create domain objects.

##product-scraper-standalone
The command line runner responsible to interact with the
application and the schema and then print out the output.

### Build Project
Build the project from the parent pom directory.
```
mvn clean install
```
Executable jar will be available under product-scraper-standalone module:
```
product-scraper-standalone/target/product-scraper-standalone-0.0.1-SNAPSHOT.jar
```

### Run the jar
Currently, the application.properties is picked up from inside the jar placed under the following path:

product-scraper-standalone/src/main/resources/application.properties

You may copy and then modify the same file, and run the executable as follows:

```
java -jar jarFileName.jar --spring.config.location=./path/to/your/local/application.properties
```

You can override the source url through command line as follows
```
java -jar -Dsource.url=https://www.google.co.uk/ product-scraper-standalone/target/product-scraper-standalone-0.0.1-SNAPSHOT.jar 
```

### Running the tests
Unit Tests are run as part of the build process

i.e. mvn clean install

### Integration tests
ScraperTest and ScraperServiceIntegrationTest require internet to run,
with more time we could use wiremock so that tests can run without network.

ScraperTest tests the output by fetching the original url and asserting the configured string output

ScraperServiceIntegrationTest fetches the original url but asserts that the correct productsummary has been generated.

Other Integration tests use local resources.

### Modularity
The application has been developed using TDD.

The idea was to keep the core bits in its own module i.e. the bits required to full fill the requirements and to keep the problem space together.

The domain is designed such that it will not allow the user to create an invalid instance.

Errors are logged, and Not all exceptions are rethrown for example it's okay to have no product nutrition.

###Assumption

An empty optional is returned if the product can't be parsed instead of throwing an error.

### Future
Create a Total Service Interface so that multiple implementation can be done to calculate the total and VAT.

Use Mock Server in tests rather than connect to internet.

The configuration for circleCI has been added, but not tested against a circle CI instance.
