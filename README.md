# Citronix - Lemon Farm Management Application

## Project Overview

Citronix is a comprehensive Spring Boot application designed to manage lemon farms, providing farmers with a robust tool to track farm production, harvests, and sales. The application offers detailed management of farms, fields, trees, harvests, and sales, with a focus on optimizing tree productivity based on age.

## Key Features

- **Farm Management**: Create, modify, and view farm details
- **Field Management**: Associate fields with farms, ensure area constraints
- **Tree Management**: 
  - Track tree planting dates, age, and field association
  - Calculate tree productivity based on age
- **Harvest Management**: 
  - Track seasonal harvests
  - Record harvest dates and quantities
- **Sales Management**: 
  - Record sales with date, unit price, and associated harvest
  - Calculate revenue

## Technical Stack

- **Backend**: Spring Boot
- **Architecture**: Layered Architecture (Controller, Service, Repository, Entity)
- **Data Validation**: Spring Annotations
- **Testing**: JUnit and Mockito
- **Additional Tools**: 
  - Lombok
  - Builder Pattern
  - MapStruct

## Prerequisites

- Java 17 or higher
- Maven
- Git
- Postman (recommended for API testing)

## Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/mohamedtergui1/Citronix.git
   cd citronix
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   - Base URL: `http://localhost:8080`
   - Swagger UI : `http://localhost:8080/swagger-ui.html`

## Database Preparation

The application requires a database with at least 5 records in each table. Ensure your database is configured in `application.properties` or `application.yml`.

## Key Constraints

- Minimum field size: 0.1 hectares (1,000 m²)
- Maximum field size: 50% of total farm area
- Maximum 10 fields per farm
- Tree density: 100 trees per hectare
- Tree planting period: March to May
- Tree productive life: Up to 20 years

## API Endpoints

Main endpoints include:
- Farm Management
- Field Management
- Tree Management
- Harvest Management
- Sales Management

Use Postman or Swagger for comprehensive API documentation and testing.

## Running Tests

Execute unit tests:
```bash
mvn test
```

## Deployment

The project generates an executable JAR file:
```bash
mvn package
java -jar target/citronix-0.0.1-SNAPSHOT.jar
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


