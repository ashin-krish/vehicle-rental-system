# Vehicle Rental Management System

A Java-based vehicle rental management system built using JDBC, MySQL, layered architecture, Java 8 features, database transactions, and comprehensive unit testing. This is a backend-focused learning project demonstrating core Java concepts and enterprise development practices.

---

## Overview

The Vehicle Rental Management System is a console-based application designed to manage the complete lifecycle of vehicle rentals. It handles customer registration, vehicle inventory management, and rental transactions with ACID-compliant database operations.

**What the application does:**
- Registers and manages customers with email validation
- Maintains a vehicle inventory with availability tracking
- Creates and processes vehicle rentals with automatic status updates
- Tracks active and completed rentals with transaction integrity

**Why it was built:**
This project serves as a learning platform to practice core Java concepts, Object-Oriented Programming principles, modern Java 8 features, and enterprise-grade database operations. It emphasizes layered architecture, separation of concerns, and transaction management.

**Architecture:**
The system follows a strict layered architecture:
```
Console UI Layer
       ↓
Service Layer (Business Logic)
       ↓
Repository Layer (Data Access)
       ↓
JDBC
       ↓
MySQL Database
```

---

## Features

### Customer Management
- **Customer Registration** - Register new customers with name, email, and phone
- **Email Validation** - Validates email format using regex patterns
- **Duplicate Prevention** - Prevents duplicate email registrations
- **Customer Retrieval** - Retrieve all registered customers
- **Customer Existence Checks** - Verify customer existence by ID or email

### Vehicle Management
- **Vehicle Registration** - Register vehicles with registration number, brand, model, type, and daily rental price
- **Vehicle Retrieval** - Retrieve all vehicles from inventory
- **Availability Tracking** - Check vehicle availability status
- **Vehicle Status Updates** - Update vehicle status (AVAILABLE, RENTED, MAINTENANCE)
- **Search by Brand** - Filter vehicles by manufacturer brand
- **Search by Type** - Filter vehicles by type (e.g., sedan, SUV)
- **Sort by Price** - Sort vehicles by daily rental price in ascending order
- **Available Vehicle Filtering** - Retrieve only available vehicles for rental
- **Registration Number Validation** - Prevent duplicate vehicle registrations

### Rental Management
- **Create Rental** - Process vehicle rental with automatic status updates
- **Return Vehicle** - Handle vehicle returns with transaction management
- **Rental History** - View all rentals with dates
- **Active Rentals** - View currently active rentals (not yet returned)
- **Transaction Management** - Ensures consistency across rental and vehicle status updates
- **Automatic Date Tracking** - Records rental date and return date

### User Interface
- **Console-Based Menu System** - Intuitive menu-driven interface
- **Customer Management Menu** - Dedicated interface for customer operations
- **Vehicle Management Menu** - Comprehensive vehicle inventory interface
- **Rental Management Menu** - Interface for rental and return operations
- **Input Validation** - Handles invalid inputs gracefully
- **Error Handling** - Displays meaningful error messages for all scenarios

### Testing
- **JUnit 5 Testing** - Comprehensive unit test suite
- **Mockito Mocking** - Isolates service layer from repository layer
- **Service Layer Testing** - Validates business logic correctness
- **Success/Failure Scenarios** - Tests both valid and invalid input handling
- **Dependency Mocking** - Repository methods mocked to test services in isolation

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 25 | Application development |
| Java 8+ Features | Streams, lambdas, method references, text blocks |
| JDBC | Database connectivity |
| MySQL | Data persistence |
| Maven | Build and dependency management |
| JUnit 5 | Unit testing framework |
| Mockito 5.19.0 | Mocking dependencies for isolated testing |
| Git | Version control |

---

## Architecture

### Layered Design

The application is structured in distinct layers with clear separation of concerns:

```
┌─────────────────────────────────┐
│      Console UI Layer           │
│ (User interaction & I/O)        │
└────────────────┬────────────────┘
                 │
┌─────────────────▼────────────────┐
│      Service Layer              │
│ (Business Logic & Validation)   │
└────────────────┬────────────────┘
                 │
┌─────────────────▼────────────────┐
│      Repository Layer           │
│ (Data Access & SQL)             │
└────────────────┬────────────────┘
                 │
┌─────────────────▼────────────────┐
│      JDBC                       │
└────────────────┬────────────────┘
                 │
┌─────────────────▼────────────────┐
│      MySQL Database             │
└─────────────────────────────────┘
```

### Layer Responsibilities

**UI Layer** (`ui/` package)
- Displays menus to users
- Collects and validates user input
- Calls appropriate Service methods
- Displays results and error messages
- Manages application flow and menu navigation

**Service Layer** (`service/` package)
- Implements business logic
- Validates business rules (e.g., email format, duplicate prevention)
- Coordinates between UI and Repository layers
- Manages rental workflows
- Handles database transactions for multi-step operations
- Wraps repository exceptions

**Repository Layer** (`repository/` package)
- Executes SQL queries via JDBC
- Maps database rows to Java objects
- Handles connection management
- Provides data access methods
- Throws `DataAccessException` for database errors

**Model Layer** (`model/` package)
- Represents business entities
- Contains domain validation logic
- Defines enums (e.g., `VehicleStatus`)

**Utility Layer** (`util/` package)
- `DatabaseConnection` - Manages JDBC connections and loads database configuration

---

## Project Structure

```
vehicle-rental-system/
├── pom.xml                                    # Maven configuration
│
├── src/
│   ├── main/
│   │   ├── java/com/ashin/vehiclerental/
│   │   │   ├── Main.java                    # Application entry point
│   │   │   │
│   │   │   ├── model/
│   │   │   │   ├── Customer.java            # Customer entity
│   │   │   │   ├── Vehicle.java             # Vehicle entity with status enum
│   │   │   │   └── Rental.java              # Rental entity
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── CustomerService.java     # Customer business logic
│   │   │   │   ├── VehicleService.java      # Vehicle business logic
│   │   │   │   └── RentalService.java       # Rental & transaction logic
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── CustomerRepository.java  # Customer data access
│   │   │   │   ├── VehicleRepository.java   # Vehicle data access
│   │   │   │   └── RentalRepository.java    # Rental data access
│   │   │   │
│   │   │   ├── ui/
│   │   │   │   ├── ConsoleUI.java           # UI formatting utilities
│   │   │   │   ├── CustomerUI.java          # Customer UI menu
│   │   │   │   ├── VehicleUI.java           # Vehicle UI menu
│   │   │   │   └── RentalUI.java            # Rental UI menu
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   └── DataAccessException.java # Custom database exception
│   │   │   │
│   │   │   └── util/
│   │   │       └── DatabaseConnection.java  # JDBC connection management
│   │   │
│   │   └── resources/
│   │       └── db.properties                # Database configuration
│   │
│   └── test/
│       └── java/com/ashin/vehiclerental/
│           ├── model/
│           │   ├── CustomerTest.java
│           │   ├── RentalTest.java
│           │   └── vehicleTest.java
│           └── service/
│               ├── CustomerServiceTest.java # Service layer unit tests
│               └── VehicleServiceTest.java  # Service layer unit tests
│
└── target/                                   # Compiled classes and test results
```

---

## Database

### Tables

The application uses three primary tables in the MySQL database (`vehicle_rental`):

**customers**
```sql
CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL
);
```
Stores customer information with unique email validation.

**vehicles**
```sql
CREATE TABLE vehicles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registration_number VARCHAR(50) UNIQUE NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price_per_day INT NOT NULL,
    vehicle_status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE') NOT NULL
);
```
Maintains vehicle inventory with status tracking and pricing.

**rentals**
```sql
CREATE TABLE rentals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);
```
Records rental transactions with optional return dates (NULL = active rental).

### Entity Relationships

```
Customer
   │
   └──────────┐
              │
         ┌────▼─────┐
         │  Rental  │
         └────┬─────┘
              │
              └──────────┐
                         │
                      Vehicle
```

A Customer has many Rentals, and a Vehicle is referenced by many Rentals. A Rental connects one Customer to one Vehicle for a specific time period.

---

## Rental Transaction Flow

### Renting a Vehicle

The rental process ensures data consistency across multiple database updates using ACID transactions:

```
User initiates rental
         │
         ▼
Validate customer exists (Service)
         │
         ▼
Validate vehicle exists (Service)
         │
         ▼
Validate vehicle is AVAILABLE (Service)
         │
         ▼
BEGIN TRANSACTION
         │
         ▼
INSERT rental record (Repository)
         │
         ▼
UPDATE vehicle status → RENTED (Repository)
         │
         ▼
All successful? 
    ├─ YES → COMMIT
    └─ NO → ROLLBACK
```

**Key aspects:**
- Connection's autocommit is disabled
- Both insert and status update must succeed
- If either operation fails, the entire transaction is rolled back
- Database remains consistent if an error occurs mid-transaction

### Returning a Vehicle

Similarly, the return process is transactional:

```
User initiates return
         │
         ▼
BEGIN TRANSACTION
         │
         ▼
UPDATE rental record with return_date (Repository)
         │
         ▼
UPDATE vehicle status → AVAILABLE (Repository)
         │
         ▼
All successful?
    ├─ YES → COMMIT
    └─ NO → ROLLBACK
```

**Benefits:**
- Prevents orphaned rental records
- Ensures vehicles become available again only when rental is complete
- Maintains referential integrity
- Provides automatic recovery on failure

---

## Java 8 Features Used

### Stream API

The application extensively uses Java 8 Streams for filtering and transforming collections:

**Filter Operations**
```java
// Get available vehicles
List<Vehicle> availableVehicles = vehicles.stream()
    .filter(vehicle -> vehicle.getVehicleStatus() == Vehicle.VehicleStatus.AVAILABLE)
    .collect(Collectors.toList());

// Get active rentals (null return date)
List<Rental> activeRentals = allRentals.stream()
    .filter(rental -> rental.getReturnDate() == null)
    .collect(Collectors.toList());
```

**Sorting with Comparator**
```java
// Sort vehicles by price (ascending)
List<Vehicle> sortedByPrice = vehicles.stream()
    .sorted(Comparator.comparing(Vehicle::getPricePerDay))
    .collect(Collectors.toList());
```

**Method References**
```java
// Using method references instead of lambda expressions
.sorted(Comparator.comparing(Vehicle::getPricePerDay))
```

### Lambda Expressions

Lambda expressions are used throughout for concise functional programming:

```java
// Filter with lambda
.filter(vehicle -> vehicle.getBrand() == brand)

// Stream terminal operations
.collect(Collectors.toList());
```

### Text Blocks

Multi-line SQL queries use Java 13+ text blocks for readability:

```java
String query = """
    SELECT * FROM rentals
    WHERE customer_id = ?
    AND vehicle_id = ?
    AND return_date IS NULL
""";
```

---

## Exception Handling

### Custom Exception Architecture

The application defines a custom `DataAccessException` to uniformly handle database-related errors:

```
Repository Layer
       │
       ├─ SQLException ─┐
       └─ IOException ──┤
                        │
                        ▼
              DataAccessException
                        │
                        ▼
              Service Layer
                        │
                        ▼
              UI Layer (Display to user)
```

**Benefits:**
- Checked exceptions (SQLException, IOException) are wrapped into an unchecked RuntimeException
- Simplifies error handling in service and UI layers
- Provides meaningful error messages
- Preserves stack trace and original exception as cause

**Example:**
```java
public List<Vehicle> getAllVehicles() {
    try {
        // JDBC operations
    } catch (SQLException | IOException e) {
        throw new DataAccessException("Failed to Load Vehicle", e);
    }
}
```

---

## Testing

### Test Framework

The project uses **JUnit 5** for unit testing and **Mockito** for mocking dependencies.

### Testing Strategy

**Service Layer Testing**
- Services are tested in isolation using mocked repositories
- Mockito provides controlled test data without accessing the database
- Tests focus on business logic validation

**Example Test:**
```java
@Test
void customerRegisterValid() {
    // Create mock repository
    CustomerRepository mockRepository = mock(CustomerRepository.class);
    
    // Configure mock behavior
    when(mockRepository.existByEmail("ash@gmail.com")).thenReturn(false);
    
    // Create service with mocked dependency
    CustomerService customerService = new CustomerService(mockRepository);
    
    // Execute test
    Customer customer = new Customer("Ashin", "ash@gmail.com", "1234567890");
    customerService.registerCustomer(customer);
    
    // Verify method was called
    verify(mockRepository).saveCustomer(customer);
}
```

### Test Coverage

**CustomerServiceTest** (5 tests)
- Customer registration validation
- Email uniqueness checks
- Customer existence verification
- Invalid registration scenarios

**VehicleServiceTest** (13 tests)
- Vehicle registration validation
- Registration number uniqueness
- Vehicle availability checks
- Vehicle existence verification
- Status update operations
- Search and filter operations

**Model Tests**
- Customer model validation
- Vehicle model validation
- Rental model validation

---

## Setup and Installation

### Prerequisites

- **Java Development Kit (JDK) 25** or higher
- **Maven 3.6.0** or higher
- **MySQL 8.0** or higher
- **Git** (for version control)

### Clone Repository

```bash
git clone <repository-url>
cd vehicle-rental-system
```

### Database Setup

1. **Create MySQL Database:**
   ```bash
   mysql -u root -p
   CREATE DATABASE vehicle_rental;
   ```

2. **Create Tables:**
   
   Execute the following SQL to create the required tables:
   
   ```sql
   CREATE TABLE customers (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       phone VARCHAR(20) NOT NULL
   );
   
   CREATE TABLE vehicles (
       id INT PRIMARY KEY AUTO_INCREMENT,
       registration_number VARCHAR(50) UNIQUE NOT NULL,
       brand VARCHAR(100) NOT NULL,
       model VARCHAR(100) NOT NULL,
       type VARCHAR(50) NOT NULL,
       price_per_day INT NOT NULL,
       vehicle_status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE') NOT NULL
   );
   
   CREATE TABLE rentals (
       id INT PRIMARY KEY AUTO_INCREMENT,
       customer_id INT NOT NULL,
       vehicle_id INT NOT NULL,
       rental_date DATE NOT NULL,
       return_date DATE,
       FOREIGN KEY (customer_id) REFERENCES customers(id),
       FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
   );
   ```

3. **Configure Database Connection:**
   
   Update `src/main/resources/db.properties`:
   ```properties
   url=jdbc:mysql://localhost:3306/vehicle_rental
   user=<your-mysql-username>
   password=<your-mysql-password>
   ```
   
   **Note:** Use environment variables or a local properties file (not in version control) for sensitive credentials in production.

### Build Project

```bash
mvn clean compile
```

### Run Tests

```bash
mvn test
```

Expected output: 34 tests pass (with 1 known test failure due to pre-existing backend bug in string comparison)

### Run Application

```bash
mvn exec:java -Dexec.mainClass="com.ashin.vehiclerental.Main"
```

Or compile and run directly:
```bash
mvn package
java -cp target/vehicle-rental-system-1.0-SNAPSHOT.jar:. com.ashin.vehiclerental.Main
```

---

## Example Application Flow

### Typical User Interaction

```
Start Application
        │
        ▼
    ┌───────────────────────────┐
    │  MAIN MENU                │
    │ 1. Customer Management    │
    │ 2. Vehicle Management     │
    │ 3. Rental Management      │
    │ 4. Exit                   │
    └───────────────────────────┘
        │
        ├─→ Register Customer
        │       │
        │       ├─ Enter name, email, phone
        │       ├─ Service validates email format
        │       ├─ Service checks for duplicate email
        │       └─ Customer saved to database
        │
        ├─→ Register Vehicle
        │       │
        │       ├─ Enter registration, brand, model, type, price
        │       ├─ Service checks registration uniqueness
        │       ├─ Vehicle created with AVAILABLE status
        │       └─ Vehicle saved to database
        │
        ├─→ Rent Vehicle
        │       │
        │       ├─ Enter customer ID and vehicle ID
        │       ├─ Service validates both exist
        │       ├─ Service verifies vehicle is AVAILABLE
        │       ├─ BEGIN TRANSACTION
        │       ├─ Insert rental record
        │       ├─ Update vehicle status → RENTED
        │       ├─ COMMIT
        │       └─ Success message
        │
        ├─→ Return Vehicle
        │       │
        │       ├─ Enter customer ID and vehicle ID
        │       ├─ BEGIN TRANSACTION
        │       ├─ Update rental with return_date
        │       ├─ Update vehicle status → AVAILABLE
        │       ├─ COMMIT
        │       └─ Success message
        │
        └─→ Exit
                │
                ▼
            Application Ends
```

---

## Learning Outcomes

This project demonstrates proficiency in:

### Core Java Concepts
- **Object-Oriented Programming** - Classes, inheritance, encapsulation, polymorphism
- **Collections Framework** - Lists, ArrayList, usage patterns
- **Exception Handling** - Custom exceptions, try-catch-finally, exception propagation

### Java 8+ Features
- **Stream API** - Filter, map, sorted, collect operations
- **Lambda Expressions** - Functional programming paradigm
- **Method References** - `Vehicle::getPricePerDay` syntax
- **Comparator** - Sorting collections with `Comparator.comparing()`
- **Text Blocks** - Multi-line string literals with triple quotes

### Database & JDBC
- **JDBC Fundamentals** - Connection management, PreparedStatement usage
- **SQL Queries** - SELECT, INSERT, UPDATE operations
- **ResultSet Processing** - Mapping database rows to Java objects
- **Exception Handling** - Wrapping SQL exceptions
- **Connection Pooling Concepts** - Proper resource management

### Database Transactions
- **ACID Properties** - Atomicity through transaction management
- **Commit & Rollback** - Managing transaction boundaries
- **Isolation** - Database connection handling

### Layered Architecture
- **Service/Repository Pattern** - Clear separation of concerns
- **Dependency Injection** - Constructor-based injection for testing
- **Business Logic Isolation** - Keeping business rules in Service layer

### Unit Testing
- **JUnit 5** - Test annotations, assertions, lifecycle
- **Mockito** - Creating mocks, configuring behavior with `when()`
- **Test Isolation** - Testing services with mocked repositories
- **Test Scenarios** - Success and failure cases

### MySQL
- **Database Design** - Schema creation, relationships, constraints
- **SQL Operations** - CRUD operations, foreign keys, enums
- **Data Integrity** - UNIQUE constraints, referential integrity

### Maven
- **Project Configuration** - pom.xml structure
- **Dependency Management** - Managing libraries and versions
- **Build Lifecycle** - Clean, compile, test, package phases

### Git & Version Control
- **Repository Management** - Cloning, committing, pushing
- **Branch Management** - Working with branches (if applicable)
- **Collaborative Development** - Preparing code for team environments

---

## Known Issues

### Test Failure

There is a known test failure in `VehicleServiceTest.availableCheckByBrand`:

**Issue:** The `getVehicleByBrand()` and `getVehicleByType()` methods in `VehicleService` use the `==` operator for String comparison instead of `.equals()`. In Java, this compares object references rather than values, causing incorrect filtering.

**Affected Methods:**
- `VehicleService.getVehicleByBrand(String)`
- `VehicleService.getVehicleByType(String)`

**Workaround:** The UI layer mitigates this issue by avoiding these specific methods. For production use, these methods should be corrected to use `.equals()` for string comparison.

---

## Future Improvements

### Features
- **Pagination** - Display large datasets in pages
- **Advanced Search** - Combine multiple search criteria
- **Rental Duration Calculation** - Automatic cost calculation
- **DTOs (Data Transfer Objects)** - Separate API objects from entities
- **Input Validation Framework** - Centralized validation logic

### Architecture
- **Spring Boot REST API** - Convert to RESTful web service
- **Dependency Injection Framework** - Replace manual DI with Spring DI
- **Logging Framework** - Add SLF4J and Logback for structured logging

### Testing
- **Integration Testing** - Test with real database
- **Test Containers** - Docker-based database for tests
- **Code Coverage** - Use JaCoCo to measure test coverage

### Security & Performance
- **Authentication & Authorization** - User login and role-based access
- **Database Connection Pooling** - HikariCP for connection management
- **Query Optimization** - Indexes and performance tuning

### Deployment
- **Docker** - Containerize application and database
- **CI/CD Pipeline** - GitHub Actions or GitLab CI
- **Environment Configuration** - Environment-specific settings

### Frontend
- **Web Interface** - Spring MVC or modern web framework
- **Mobile Application** - Android/iOS client

---

## License

This project is provided as-is for educational purposes.

---

## Author

Developed as a learning project to practice Java, database design, layered architecture, and professional software development practices.

---

**Last Updated:** August 2026
