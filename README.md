# E-Commerce Platform

## Table of Contents
- [Note](#note)
- [Introduction](#introduction)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Database Schema & ERD](#database-schema--erd)
- [Project Structure](#project-structure)
- [Detailed Module Descriptions](#detailed-module-descriptions)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

---

## Note

> **Project Context & Learning Focus**
> 
> This E-Commerce platform represents my first comprehensive Spring Boot project, designed as a learning exercise to explore and implement various aspects of modern backend development. The primary goal was to gain hands-on experience with multiple technologies and frameworks rather than achieving production-ready optimization.
> 
> **Key Learning Objectives:**
> - **Framework Mastery**: Deep dive into Spring Boot 3.4, Spring Security, Spring Data JPA, and Spring Data MongoDB
> - **Multi-Database Architecture**: Implementation of hybrid data storage (PostgreSQL, MongoDB, Redis)
> - **Security Implementation**: JWT authentication, role-based access control, and custom security filters
> - **Performance Concepts**: Caching strategies, aspect-oriented programming, and auditing
> - **API Design**: RESTful endpoints, DTOs, mappers, and proper error handling
> 
> **Technical Scope:**
> While this project demonstrates a wide range of Spring Boot capabilities and best practices, it prioritizes breadth of learning over depth of optimization. Some areas may benefit from further refinement in terms of performance, scalability, and production readiness.
> 
> **Future Enhancements:**
> This foundation provides an excellent base for implementing advanced features such as microservices architecture, event-driven design, advanced caching strategies, and comprehensive testing suites.

---

## Introduction

This project is a comprehensive E-Commerce platform built with Spring Boot 3.4 and Java 21. It implements a multi-role system supporting **Admin**, **Customer**, **Seller**, and **Support** users, each with distinct permissions and capabilities.

### Core Business Logic
- **Customers** can browse products, add items to cart, place orders, and review products
- **Sellers** can list products, manage inventory, create offers, and track sales
- **Support** staff can handle customer inquiries, manage reports, and oversee order processing
- **Admins** have full system access for user management and platform oversight

### Architecture Highlights
- **Hybrid Database Strategy**: PostgreSQL for relational data (users, products, payments), MongoDB for document storage (orders, reviews), and Redis for high-performance caching (shopping carts)
- **Security**: JWT-based authentication with role-based access control
- **Performance**: Redis caching for cart operations and frequently accessed data
- **Auditing**: Comprehensive logging with aspect-oriented programming and MongoDB auditing

---

## Features

### 🔐 Authentication & Authorization
- **JWT-based authentication** with secure token management
- **Role-based access control** with granular permissions for each user type
- **Custom security filters** and exception handling
- **Password encryption** using BCrypt

### 👥 User Management
- **Multi-role registration**: Customer, Seller, Support, and Admin accounts
- **Profile management** with personal and business information
- **Address management** for shipping and billing
- **Balance and loyalty points** tracking for customers

### 🛍️ Product & Catalog Management
- **Product catalog** with categories (Electronics, Clothes, Books, Toys, Furniture, Groceries, Health, Beauty, Sports, Games)
- **Inventory management** with real-time stock tracking
- **Product offers and discounts** with time-based validity
- **Product ratings and reviews** system

### 🛒 Shopping Experience
- **Shopping cart** stored in Redis for fast access
- **Order processing** with status tracking (Ongoing, Delivered, Cancelled)
- **Payment processing** with balance validation
- **Order history** and tracking

### 💳 Payment System
- **Virtual balance system** for customers
- **Payment validation** and transaction processing
- **Payment cancellation** and refund handling
- **Transaction history** tracking

### 📊 Reporting & Support
- **Customer reports** for product issues and complaints
- **Support ticket system** for customer service
- **Order monitoring** and management
- **Analytics and reporting** capabilities

### ⚡ Performance & Caching
- **Redis caching** for shopping carts and frequently accessed data
- **Database optimization** with proper indexing and queries
- **Aspect-oriented logging** for comprehensive audit trails

### 🔍 Advanced Features
- **MongoDB auditing** for document change tracking
- **Multi-database architecture** for optimal data storage
- **RESTful API** design with proper HTTP methods
- **Error handling** and validation throughout the application

---

## Tech Stack

### 🚀 Core Framework
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.4** - Rapid application development framework
- **Spring Security 6.2** - Comprehensive security framework
- **Spring Data JPA** - Data access layer for relational databases
- **Spring Data MongoDB** - NoSQL data access layer
- **Spring Data Redis** - In-memory data store access

### 🗄️ Database & Storage
- **PostgreSQL 15+** - Primary relational database for users, products, payments
- **MongoDB 6+** - Document database for orders and reviews
- **Redis 7+** - In-memory cache for shopping carts and session data

### 🔧 Development Tools
- **Maven 3.8+** - Build automation and dependency management
- **Lombok 1.18.36** - Reduces boilerplate code with annotations
- **MapStruct 1.6.3** - Type-safe object mapping between DTOs and entities
- **JJWT 0.12.6** - JSON Web Token implementation for authentication

### 🛡️ Security & Validation
- **Spring Security** - Authentication, authorization, and security features
- **JWT (JSON Web Tokens)** - Stateless authentication mechanism
- **BCrypt** - Password hashing and verification
- **Custom Security Filters** - Application-specific security logic

### 📊 Monitoring & Logging
- **Spring AOP 6.2** - Aspect-oriented programming for cross-cutting concerns
- **MongoDB Auditing** - Automatic document change tracking
- **Custom Logging Aspects** - Application-specific logging implementation

### 🏗️ Architecture Patterns
- **Repository Pattern** - Data access abstraction layer with JPA, MongoDB, and Redis repositories
- **DTO Pattern** - Data transfer objects for API responses and internal communication
- **Mapper Pattern** - Object transformation between entities and DTOs using MapStruct
- **Service Layer Pattern** - Business logic encapsulation with dependency injection
- **Factory Pattern** - User creation based on role type (Customer, Seller, Support, Admin)
- **Strategy Pattern** - Role-based service selection for user operations
- **Template Method Pattern** - Common user operations with role-specific implementations
- **Builder Pattern** - Entity creation with fluent interfaces (Lombok @Builder)
- **Observer Pattern** - Event-driven auditing and logging with AOP
- **Singleton Pattern** - Spring-managed beans and configuration classes
- **Dependency Injection** - Constructor-based and field-based injection patterns

---

## Getting Started

### Prerequisites

- **Java 21** 
- **Maven 3.8+** - Or use the included `mvnw` wrapper
- **PostgreSQL 15+** - [Download here](https://www.postgresql.org/download/)
- **MongoDB 6+** - [Download here](https://www.mongodb.com/try/download/community)
- **Redis 7+** - [Download here](https://redis.io/download)

### 🐳 Docker Setup (Coming Soon)

> **Note:** Docker Compose configuration is planned for future releases to simplify the setup process.

### Manual Setup

#### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/E-Commerce.git
cd E-Commerce
```

#### 2. Database Configuration

**PostgreSQL Setup:**
```sql
-- Create database and user
CREATE DATABASE ecommerce;
CREATE USER alshoki WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE ecommerce TO alshoki;
```

**MongoDB Setup:**
```bash
# Start MongoDB service
mongod --dbpath /path/to/data/db
```

**Redis Setup:**
```bash
# Start Redis server
redis-server
```

#### 3. Application Configuration

The application is pre-configured for local development. Key settings in `src/main/resources/application.properties`:

```properties
# Database connections
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.data.mongodb.uri=mongodb://localhost:27017/ECommerce
spring.redis.host=localhost
spring.redis.port=6379

# JWT Configuration
ecommerce.app.jwtSecret=your-secret-key
ecommerce.app.jwtExpirationMs=86400000
```

#### 4. Run the Application

**Using Maven Wrapper (Recommended):**
```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
.\mvnw.cmd spring-boot:run
```

**Using IDE:**
- Open the project in IntelliJ IDEA, Eclipse, or VS Code
- Run `ECommerceApplication.java` as a Spring Boot application

**Using Maven (if installed globally):**
```bash
mvn spring-boot:run
```

#### 5. Verify Installation

- **Application**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health (if actuator is added)
- **API Documentation**: Available through controller endpoints

### 🐳 Future Docker Setup

> **Planned Features:**
> - `Dockerfile` for application containerization
> - `docker-compose.yml` for multi-service orchestration
> - Pre-configured database containers
> - Environment-specific configurations
> - One-command startup: `docker-compose up`

### Troubleshooting

**Common Issues:**
- **Port conflicts**: Ensure ports 8080, 5432, 27017, 6379 are available
- **Database connection**: Verify PostgreSQL, MongoDB, and Redis are running
- **Java version**: Ensure Java 21 is installed and `JAVA_HOME` is set
- **Maven issues**: Use the included `mvnw` wrapper for consistent builds

---

## Database Schema & ERD

### 🗄️ Relational Database (PostgreSQL)

#### Core Tables

| Table | Description | Key Fields |
|-------|-------------|------------|
| **users** | Base user table with inheritance | `id`, `username`, `email`, `password`, `role`, `created_at` |
| **customers** | Customer-specific data | `id`, `balance`, `amount_spent`, `loyalty_points`, `personal_address_id` |
| **sellers** | Seller-specific data | `id`, `business_name`, `tax_id`, `shipping_address_id` |
| **support_team** | Support staff data | `id` (inherits from users) |
| **admins** | Admin user data | `id` (inherits from support) |
| **products** | Product catalog | `id`, `name`, `description`, `price`, `quantity`, `category`, `owner_id` |
| **offers** | Product discounts | `id`, `discount`, `start_date`, `end_date`, `seller_id`, `product_id` |
| **payments** | Payment transactions | `id`, `payment_date`, `order_id`, `card_holder_id`, `amount` |
| **reports** | Customer reports | `id`, `user_id`, `product_id`, `reported_user_id`, `title`, `description` |
| **address** | Address information | `id`, `address_line1`, `city`, `state`, `country`, `zip_code` |

#### Junction Tables

| Table | Purpose | Relationships |
|-------|---------|---------------|
| **customer_purchased_products** | Many-to-many: Customers ↔ Products | `customer_id`, `product_id` |
| **customer_wishlist_products** | Many-to-many: Customers ↔ Wishlist | `customer_id`, `product_id` |

### 📄 Document Database (MongoDB)

#### Collections

| Collection | Description | Document Structure |
|------------|-------------|-------------------|
| **orders** | Order documents | `id`, `userId`, `productId`, `quantity`, `status`, `paymentId` |
| **reviews** | Product reviews | `id`, `userId`, `productId`, `rating`, `comment`, `createdAt` |

### ⚡ Cache Database (Redis)

#### Data Structures

| Structure | Purpose | Key Format |
|-----------|---------|------------|
| **Shopping Carts** | User cart data | `cart:{userId}` |
| **Session Data** | User sessions | `session:{sessionId}` |
| **Cache** | Frequently accessed data | `cache:{entity}:{id}` |

### 🔗 Entity Relationships

#### Inheritance Hierarchy
```
User (Base)
├── Customer
│   └── Seller
└── Support
    └── Admin
```

#### Key Relationships
- **User ↔ Address**: One-to-One (personal address)
- **Seller ↔ Address**: One-to-One (shipping address)
- **Product ↔ Seller**: Many-to-One (owner)
- **Product ↔ Offer**: One-to-One (current offer)
- **Customer ↔ Product**: Many-to-Many (purchased/wishlist)
- **Order ↔ Payment**: One-to-One (payment record)
- **Report ↔ Customer**: Many-to-One (reporter)
- **Report ↔ Product**: Many-to-One (reported product)

### 📊 Entity Relationship Diagram (ERD)

> **Note:** For a visual ERD diagram, you can:
> 1. Use [dbdiagram.io](https://dbdiagram.io) to create a professional diagram
> 2. Use [draw.io](https://draw.io) for a free diagram tool
> 3. Use Mermaid syntax for GitHub-compatible diagrams
> 
> **Recommended:** Upload your ERD image to GitHub Issues and reference it here:
> ```markdown
> ![ERD Diagram](https://user-images.githubusercontent.com/your-image-url.png)
> ```

### 🔍 Database Features

#### PostgreSQL Features
- **Inheritance**: JOINED strategy for user hierarchy
- **Entity Graphs**: Optimized query loading with `@NamedEntityGraph`
- **Auditing**: Automatic `@CreatedDate`, `@CreatedBy` tracking
- **Cascade Operations**: Proper relationship management

#### MongoDB Features
- **Document Storage**: Flexible schema for orders and reviews
- **Auditing**: Automatic change tracking with `@EnableMongoAuditing`
- **Indexing**: Optimized queries for document retrieval

#### Redis Features
- **Caching**: High-performance data access
- **Session Storage**: User session management
- **Cart Storage**: Fast shopping cart operations

---

## Project Structure

### 📁 Main Application Structure

```
src/main/java/com/example/ECommerce/
├── 📄 ECommerceApplication.java          # Main Spring Boot application class
├── 🔧 Config/                           # Configuration classes
│   ├── AppStartUp.java                  # Data initialization on startup
│   ├── Config.java                      # JPA auditing configuration
│   └── RedisConfiguration.java          # Redis cache configuration
├── 🎮 Controllers/                      # REST API endpoints
│   ├── AddressController.java           # Address management endpoints
│   ├── CartController.java              # Shopping cart operations
│   ├── ErrorController.java             # Global error handling
│   ├── OfferController.java             # Product offers management
│   ├── OrderController.java             # Order processing endpoints
│   ├── PaymentController.java           # Payment operations
│   ├── ProductController.java           # Product catalog endpoints
│   ├── ReportController.java            # Customer reports handling
│   ├── ReviewController.java            # Product reviews
│   ├── UserController.java              # User management endpoints
│   └── RolesControllers/                # Role-specific controllers
│       ├── AdminController.java         # Admin operations
│       ├── CustomerController.java      # Customer operations
│       ├── SellerController.java        # Seller operations
│       └── SupportController.java       # Support operations
├── 📄 Documents/                        # MongoDB document entities
│   ├── Order.java                      # Order document structure
│   └── Review.java                     # Review document structure
├── 📋 DTOs/                            # Data Transfer Objects
│   ├── AddressDTO.java                 # Address data transfer
│   ├── CartDTO.java                    # Cart data transfer
│   ├── OfferDTO.java                   # Offer data transfer
│   ├── OrderDTO.java                   # Order data transfer
│   ├── PaymentDTO.java                 # Payment data transfer
│   ├── ProductDTO.java                 # Product data transfer
│   ├── ProductProfileDTO.java          # Detailed product data
│   ├── ReportDTO.java                  # Report data transfer
│   ├── ReviewDTO.java                  # Review data transfer
│   ├── RoleBasedDTO/                   # Role-specific DTOs
│   │   ├── AdminDTO.java              # Admin profile data
│   │   ├── CustomerDTO.java           # Customer profile data
│   │   ├── SellerDTO.java             # Seller profile data
│   │   ├── SupportDTO.java            # Support profile data
│   │   └── UserDTO.java               # Base user data
│   └── UserRegisterationDTO.java       # User registration data
├── 🏗️ Entities/                        # JPA entity classes
│   ├── Address.java                    # Address entity
│   ├── Offer.java                      # Offer entity
│   ├── Payment.java                    # Payment entity
│   ├── Product.java                    # Product entity
│   ├── Report.java                     # Report entity
│   ├── User.java                       # Base user entity
│   └── SubEntities/                    # User role entities
│       ├── Admin.java                  # Admin entity
│       ├── Customer.java               # Customer entity
│       ├── Seller.java                 # Seller entity
│       └── Support.java                # Support entity
├── 🔢 Enums/                           # Enumeration classes
│   ├── Categories.java                 # Product categories
│   ├── Country.java                    # Country enumeration
│   ├── Report_Category.java            # Report categories
│   ├── Roles.java                      # User roles
│   └── STATUS.java                     # Order status
├── 🔄 Mappers/                         # Object mapping classes
│   ├── AddressMapper.java              # Address entity-DTO mapping
│   ├── AdminMapper.java                # Admin entity-DTO mapping
│   ├── CartMapper.java                 # Cart entity-DTO mapping
│   ├── CustomerMapper.java             # Customer entity-DTO mapping
│   ├── OfferMapper.java                # Offer entity-DTO mapping
│   ├── OrderMapper.java                # Order entity-DTO mapping
│   ├── PaymentMapper.java              # Payment entity-DTO mapping
│   ├── ProductMapper.java              # Product entity-DTO mapping
│   ├── ProfilesMapper.java             # Profile entity-DTO mapping
│   ├── ReportMapper.java               # Report entity-DTO mapping
│   ├── ReviewMapper.java               # Review entity-DTO mapping
│   ├── SellerMapper.java               # Seller entity-DTO mapping
│   ├── SupportMapper.java              # Support entity-DTO mapping
│   └── UserMapper.java                 # User entity-DTO mapping
├── 🗄️ Repositories/                    # Data access layer
│   ├── JPA/                           # PostgreSQL repositories
│   │   └── RoleBasedRepositories/      # Role-specific repositories
│   │       ├── AddressRepository.java  # Address data access
│   │       ├── AdminRepository.java    # Admin data access
│   │       ├── CustomerRepository.java # Customer data access
│   │       ├── OfferRepository.java    # Offer data access
│   │       ├── PaymentRepository.java  # Payment data access
│   │       ├── ProductRepository.java  # Product data access
│   │       ├── ReportRepository.java   # Report data access
│   │       ├── SellerRepository.java   # Seller data access
│   │       ├── SupportRepository.java  # Support data access
│   │       └── UserRepository.java     # User data access
│   ├── Mongo/                          # MongoDB repositories
│   │   ├── OrderRepository.java        # Order document access
│   │   └── ReviewRepository.java       # Review document access
│   └── Redis/                          # Redis repositories
│       └── CartRepository.java         # Cart data access
├── 🔐 SecurityConfig/                  # Security configuration
│   ├── CustomFilters/                  # Custom security filters
│   │   └── UsernamePasswordJsonFilter.java # JSON authentication filter
│   ├── JWT/                           # JWT implementation
│   │   ├── AuthEntryPointJwt.java     # JWT authentication entry point
│   │   ├── AuthTokenFilter.java       # JWT token filter
│   │   └── JwtUtils.java              # JWT utility functions
│   ├── SecurityConfig.java             # Main security configuration
│   ├── SecurityExceptions/             # Security exception handling
│   │   └── NoAuthenticationProvider.java # Custom authentication exception
│   └── SecurityServices/               # Security service implementations
│       ├── UserDetailsImp.java         # User details implementation
│       └── UserDetailsServiceImp.java  # User details service
├── ⚙️ Services/                        # Business logic layer
│   ├── AddressService.java             # Address management logic
│   ├── CartService.java                # Shopping cart logic
│   ├── OfferService.java               # Offer management logic
│   ├── OrderService.java               # Order processing logic
│   ├── PaymentsService.java            # Payment processing logic
│   ├── ProductService.java             # Product management logic
│   ├── ReportService.java              # Report handling logic
│   ├── ReviewService.java              # Review management logic
│   ├── SupportService.java             # Support operations logic
│   └── UserServices/                   # User-specific services
│       ├── AdminService.java           # Admin operations
│       ├── CustomerService.java        # Customer operations
│       ├── SellerService.java          # Seller operations
│       ├── SupportService.java         # Support operations
│       └── UserService.java            # Base user operations
└── 🔍 Aspects/                         # Aspect-oriented programming
    └── LoggingAspect.java              # Cross-cutting logging concerns
```

### 📁 Resource Structure

```
src/main/resources/
├── application.properties               # Application configuration
└── static/                            # Static resources (if any)
```

### 📁 Test Structure

```
src/test/java/com/example/ECommerce/
└── ECommerceApplicationTests.java      # Main application tests
```

### 🏗️ Architecture Layers

| Layer | Purpose | Key Components |
|-------|---------|----------------|
| **Controllers** | REST API endpoints | Handle HTTP requests/responses |
| **Services** | Business logic | Process business rules and operations |
| **Repositories** | Data access | Database operations and queries |
| **Entities** | Data models | JPA entities and MongoDB documents |
| **DTOs** | Data transfer | API request/response objects |
| **Mappers** | Object transformation | Convert between entities and DTOs |
| **Security** | Authentication/Authorization | JWT, roles, permissions |
| **Configuration** | App setup | Database, cache, security config |

---

## Detailed Module Descriptions

### 🔐 Authentication & Security Module

#### **SecurityConfig**
- **Purpose**: Central security configuration for the entire application
- **Key Features**:
  - JWT token-based authentication
  - Role-based access control with hierarchy (ADMIN → SUPPORT → SELLER → CUSTOMER)
  - Custom JSON authentication filter for login
  - Stateless session management
  - Method-level security with `@PreAuthorize`
- **Key Classes**:
  - `SecurityConfig.java`: Main security configuration
  - `JwtUtils.java`: JWT token generation and validation
  - `AuthTokenFilter.java`: JWT token processing filter
  - `UsernamePasswordJsonFilter.java`: Custom JSON login filter

#### **UserDetails Services**
- **Purpose**: Spring Security integration for user authentication
- **Key Features**:
  - Custom user details implementation
  - Role-based authority management
  - Password encoding with BCrypt
- **Key Classes**:
  - `UserDetailsImp.java`: Custom user details implementation
  - `UserDetailsServiceImp.java`: User details service

### 👥 User Management Module

#### **UserService**
- **Purpose**: Central user management with role-based operations
- **Key Features**:
  - Multi-role user registration (Customer, Seller, Support, Admin)
  - Role-based service delegation
  - User profile management
  - User deletion and updates
- **Business Logic**:
  - Validates email/username uniqueness
  - Delegates to role-specific services
  - Handles user role transitions

#### **Role-Specific Services**
- **CustomerService**: Customer-specific operations (balance, purchases, wishlist)
- **SellerService**: Seller operations (product management, offers)
- **SupportService**: Support staff operations (report handling)
- **AdminService**: Administrative operations (user management)

### 🛍️ Product Management Module

#### **ProductService**
- **Purpose**: Comprehensive product catalog management
- **Key Features**:
  - Product CRUD operations
  - Category-based filtering
  - Price range queries
  - Seller-specific product management
  - Product profile with detailed information
- **Business Logic**:
  - Inventory management
  - Seller ownership validation
  - Category-based search and filtering
  - Price-based product discovery

#### **OfferService**
- **Purpose**: Product discount and offer management
- **Key Features**:
  - Time-based offer creation
  - Active offer tracking
  - Seller-specific offer management
  - Product-offer relationships
- **Business Logic**:
  - Offer validation (date ranges, conflicts)
  - Discount calculation
  - Active offer identification

### 🛒 Shopping & Cart Module

#### **CartService**
- **Purpose**: Redis-based shopping cart management
- **Key Features**:
  - High-performance cart operations
  - Product quantity management
  - Cart persistence in Redis
  - User-specific cart isolation
- **Business Logic**:
  - Add/remove products from cart
  - Quantity validation
  - Cart total calculation
  - Cart cleanup operations

#### **OrderService**
- **Purpose**: Order processing and management
- **Key Features**:
  - Order creation and validation
  - Payment integration
  - Order status tracking
  - Multi-database order storage
- **Business Logic**:
  - Stock validation
  - Payment processing
  - Address validation
  - Order status management (ONGOING, DELIVERED, CANCELLED)

### 💳 Payment Module

#### **PaymentsService**
- **Purpose**: Payment processing and transaction management
- **Key Features**:
  - Virtual balance system
  - Payment validation
  - Transaction history
  - Refund processing
- **Business Logic**:
  - Balance validation
  - Payment amount calculation
  - Transaction recording
  - Payment cancellation and refunds

### 📊 Reporting & Support Module

#### **ReportService**
- **Purpose**: Customer report and issue management
- **Key Features**:
  - Multi-category reporting (SCAM_PRODUCT, ILLEGAL_PRODUCT, etc.)
  - Report status tracking
  - Support staff assignment
  - Report resolution workflow
- **Business Logic**:
  - Report categorization
  - Support staff assignment
  - Report resolution tracking
  - User and product reporting

### ⭐ Reviews Module

#### **ReviewService**
- **Purpose**: Product review and rating system
- **Key Features**:
  - Product rating (1-5 stars)
  - Review comments
  - User-specific reviews
  - Product review aggregation
- **Business Logic**:
  - Review validation
  - Rating calculation
  - Review moderation
  - Product rating updates

### 🗄️ Data Access Module

#### **Repository Layer**
- **JPA Repositories**: PostgreSQL data access with optimized queries
- **MongoDB Repositories**: Document storage for orders and reviews
- **Redis Repositories**: High-performance caching and cart storage
- **Key Features**:
  - Entity graph optimization
  - Custom query methods
  - Auditing support
  - Transaction management

### 🔄 Object Mapping Module

#### **Mapper Classes**
- **Purpose**: Entity-DTO transformation using MapStruct
- **Key Features**:
  - Type-safe object mapping
  - Custom mapping logic
  - Bidirectional transformations
  - Performance optimization
- **Key Mappers**:
  - `ProductMapper`: Product entity-DTO mapping
  - `UserMapper`: User entity-DTO mapping
  - `OrderMapper`: Order entity-DTO mapping
  - `ReviewMapper`: Review entity-DTO mapping

### 🔍 Monitoring & Logging Module

#### **LoggingAspect**
- **Purpose**: Cross-cutting logging concerns
- **Key Features**:
  - Controller call logging
  - Service method logging
  - Exception logging
  - User activity tracking
- **Business Logic**:
  - Automatic logging of all controller calls
  - User identification in logs
  - Exception tracking
  - Performance monitoring

### 🏗️ Configuration Module

#### **AppStartUp**
- **Purpose**: Application initialization and data seeding
- **Key Features**:
  - Database initialization
  - Sample data creation
  - User role setup
  - Product catalog seeding
- **Business Logic**:
  - Creates initial admin users
  - Seeds product catalog
  - Sets up test data
  - Configures initial offers

#### **RedisConfiguration**
- **Purpose**: Redis cache configuration
- **Key Features**:
  - Cache manager setup
  - Serialization configuration
  - Connection factory setup
  - Cache expiration policies

---

## API Endpoints

### 🔐 Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/register` | Register a new user | ❌ |
| `POST` | `/api/v1/login` | Login and receive JWT | ❌ |
| `GET` | `/api/v1/hello` | Health check endpoint | ❌ |

### 👥 User Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/user/{id}` | Get user profile | ✅ | Owner/Admin |
| `PUT` | `/api/v1/user/{id}` | Update user profile | ✅ | Owner/Admin |
| `DELETE` | `/api/v1/user/{id}` | Delete user account | ✅ | Owner/Admin |
| `GET` | `/api/v1/role/{role}` | Get users by role | ✅ | Admin |

### 🛍️ Product Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/products` | Get all products | ❌ | - |
| `GET` | `/api/v1/products/{category}` | Get products by category | ❌ | - |
| `GET` | `/api/v1/product/{id}` | Get product details | ❌ | - |
| `GET` | `/api/v1/product/{id}/profile` | Get detailed product profile | ❌ | - |
| `POST` | `/api/v1/product` | Add new product | ✅ | Customer (Seller) |
| `PUT` | `/api/v1/product/{id}` | Update product | ✅ | Owner |
| `DELETE` | `/api/v1/product/{id}` | Delete product | ✅ | Owner/Support |
| `GET` | `/api/v1/seller/{id}/products` | Get seller's products | ❌ | - |
| `GET` | `/api/v1/product/price/` | Get products by price range | ❌ | - |
| `GET` | `/api/v1/product/category/{category}/price/` | Get products by category and price | ❌ | - |
| `GET` | `/api/v1/product/category/{category}/offer` | Get products with offers by category | ❌ | - |

### 🛒 Shopping Cart Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/cart` | Get user's cart | ✅ | Customer |
| `PATCH` | `/api/v1/cart/product/{product_id}` | Add product to cart | ✅ | Customer |
| `PATCH` | `/api/v1/cart/product/{product_id}/remove` | Remove product from cart | ✅ | Customer |
| `DELETE` | `/api/v1/cart` | Clear entire cart | ✅ | Customer |

### 📦 Order Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `POST` | `/api/v1/order/{product_id}` | Place an order | ✅ | Customer |
| `GET` | `/api/v1/orders` | Get all orders | ✅ | Support |
| `GET` | `/api/v1/order/{id}` | Get specific order | ✅ | Owner/Support |
| `GET` | `/api/v1/orders/{id}` | Get orders by product | ✅ | Owner/Support |
| `GET` | `/api/v1/order/user/{id}` | Get user's orders | ✅ | Owner/Support |
| `DELETE` | `/api/v1/order/{id}` | Cancel order | ✅ | Owner/Support |

### 💳 Payment Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/payment/{id}` | Get payment details | ✅ | Owner/Admin |
| `GET` | `/api/v1/user/{id}/payments` | Get user's payment history | ✅ | Owner/Admin |

### 🎯 Offer Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `POST` | `/api/v1/offer` | Create new offer | ✅ | Customer (Seller) |
| `GET` | `/api/v1/offers` | Get all offers | ❌ | - |
| `GET` | `/api/v1/offer/{id}` | Get specific offer | ❌ | - |
| `PUT` | `/api/v1/offer/{id}` | Update offer | ✅ | Owner |
| `DELETE` | `/api/v1/offer/{id}` | Delete offer | ✅ | Owner/Support |
| `GET` | `/api/v1/product/{id}/offers` | Get offers for product | ❌ | - |
| `GET` | `/api/v1/user/{id}/offers` | Get user's offers | ✅ | Owner/Admin |
| `GET` | `/api/v1/getActiveOffers` | Get active offers | ❌ | - |

### ⭐ Review Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `POST` | `/api/v1/review` | Add product review | ✅ | Customer |
| `GET` | `/api/v1/review/{id}` | Get specific review | ❌ | - |
| `PUT` | `/api/v1/review/{id}` | Update review | ✅ | Owner |
| `DELETE` | `/api/v1/review/{id}` | Delete review | ✅ | Owner/Support |
| `GET` | `/api/v1/reviews/{product_id}` | Get product reviews | ❌ | - |
| `GET` | `/api/v1/user/{id}/reviews` | Get user's reviews | ✅ | Owner/Admin |

### 📊 Report Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `POST` | `/api/v1/report` | Create new report | ✅ | Customer |
| `GET` | `/api/v1/report/{id}` | Get specific report | ✅ | Owner/Support |
| `PUT` | `/api/v1/report/{id}` | Update report | ✅ | Owner |
| `DELETE` | `/api/v1/report/{id}` | Delete report | ✅ | Owner/Support |
| `GET` | `/api/v1/reports/user/{id}` | Get user's reports | ✅ | Support |
| `GET` | `/api/v1/reports/product/{id}` | Get product reports | ✅ | Owner/Support |
| `GET` | `/api/v1/reports/forUser/{id}` | Get reports about user | ✅ | Support |
| `PUT` | `/api/v1/report/resolve/{id}` | Resolve report | ✅ | Support |

### 👤 Customer Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/customers` | Get all customers | ✅ | Admin/Seller |
| `GET` | `/api/v1/customer/byBalance` | Get customers by balance | ✅ | Admin/Seller |
| `GET` | `/api/v1/customer/byBalanceReverse` | Get customers by balance (reverse) | ✅ | Admin/Seller |
| `GET` | `/api/v1/customer/inBetweenBalance` | Get customers by balance range | ✅ | Admin |
| `GET` | `/api/v1/product/{productId}/buyers` | Get product buyers | ✅ | Admin/Seller |

### 🛠️ Support Management Endpoints

| Method | Endpoint | Description | Auth Required | Roles |
|--------|----------|-------------|---------------|-------|
| `GET` | `/api/v1/supports` | Get all support staff | ✅ | Admin |
| `GET` | `/api/v1/support/{id}` | Get support staff details | ✅ | Admin |

### 📝 Request/Response Examples

#### **User Registration**
```json
POST /api/v1/register
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "isBusinessAccount": true
}
```

#### **Product Creation**
```json
POST /api/v1/product
{
  "name": "iPhone 15 Pro",
  "description": "Latest iPhone model",
  "price": 999.99,
  "quantity": 10,
  "category": "ELECTRONICS"
}
```

#### **Order Placement**
```json
POST /api/v1/order/123?quantity=2
Authorization: Bearer <jwt_token>
```

#### **Review Submission**
```json
POST /api/v1/review
{
  "productId": 123,
  "rating": 5,
  "comment": "Excellent product, highly recommended!"
}
```

### 🔒 Security & Authorization

- **JWT Token**: Required for authenticated endpoints
- **Role-Based Access**: Different permissions for different user roles
- **Method Security**: `@PreAuthorize` annotations for fine-grained control
- **Ownership Validation**: Users can only access their own resources

### 📊 Response Formats

All endpoints return consistent JSON responses:
- **Success**: `200 OK` with data payload
- **Error**: `400 Bad Request` with error message
- **Unauthorized**: `401 Unauthorized`
- **Forbidden**: `403 Forbidden`

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements and bug fixes.

---

## License

[MIT License](LICENSE) 