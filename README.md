# Blood Bank Management System

A comprehensive web application for managing blood bank operations with full CRUD (Create, Read, Update, Delete) functionality, built using **Java Servlets with MVC Architecture**.

## Features

- **Blood Inventory Management**: Track blood units by type, quantity, expiry date, and status
- **Donor Management**: Maintain donor records with contact information and donation history
- **Blood Request Management**: Handle blood requests from hospitals and patients
- **Modern UI**: Beautiful, responsive interface with tabbed navigation
- **MVC Architecture**: Clean separation of concerns (Model-View-Controller)

## Tech Stack

- **Backend**: Java Servlets (Java 11+)
- **Frontend**: JSP (JavaServer Pages) with JSTL
- **Build Tool**: Maven
- **Data Storage**: JSON file-based storage (easily upgradeable to database)
- **Server**: Apache Tomcat or any Servlet container

## Architecture

This project follows the **MVC (Model-View-Controller)** pattern:

- **Model**: `com.bloodbank.model` - Entity classes (BloodInventory, Donor, BloodRequest)
- **View**: `src/main/webapp/WEB-INF/views` - JSP pages for presentation
- **Controller**: `com.bloodbank.controller` - Servlets handling HTTP requests
- **Service**: `com.bloodbank.service` - Business logic layer
- **DAO**: `com.bloodbank.dao` - Data Access Object for persistence

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Apache Tomcat 9.0+ (or any Servlet 4.0 compatible container)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Installation & Setup

### Option 1: Using Maven Tomcat Plugin (Recommended for Development)

1. **Clone or navigate to the project directory**
   ```bash
   cd blood-bank-system
   ```

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the application**
   ```bash
   mvn tomcat7:run
   ```

4. **Access the Application**
   Open your browser and navigate to: `http://localhost:8080/bloodbank`

### Option 2: Deploy to Tomcat Server

1. **Build the WAR file**
   ```bash
   mvn clean package
   ```

2. **Copy the WAR file to Tomcat**
   - Copy `target/blood-bank-system-1.0.0.war` to `$CATALINA_HOME/webapps/`
   - Or rename it to `bloodbank.war`

3. **Start Tomcat**
   ```bash
   $CATALINA_HOME/bin/startup.sh  # Linux/Mac
   $CATALINA_HOME/bin/startup.bat  # Windows
   ```

4. **Access the Application**
   Open your browser and navigate to: `http://localhost:8080/bloodbank`

### Option 3: Using IDE (IntelliJ IDEA / Eclipse)

1. **Import as Maven Project**
   - IntelliJ: File → Open → Select `pom.xml`
   - Eclipse: File → Import → Maven → Existing Maven Projects

2. **Configure Tomcat Server**
   - Add Tomcat server configuration
   - Set deployment artifact to the WAR file
   - Run the application

## Project Structure

```
blood-bank-system/
├── pom.xml                                    # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bloodbank/
│   │   │       ├── controller/               # Servlets (Controllers)
│   │   │       │   ├── InventoryServlet.java
│   │   │       │   ├── DonorServlet.java
│   │   │       │   ├── RequestServlet.java
│   │   │       │   └── HomeServlet.java
│   │   │       ├── model/                    # Model classes
│   │   │       │   ├── BloodInventory.java
│   │   │       │   ├── Donor.java
│   │   │       │   └── BloodRequest.java
│   │   │       ├── service/                  # Service layer
│   │   │       │   └── BloodBankService.java
│   │   │       └── dao/                      # Data Access Object
│   │   │           └── BloodBankDAO.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml                   # Web application configuration
│   │       │   └── views/                    # JSP views
│   │       │       ├── inventory-list.jsp
│   │       │       ├── inventory-form.jsp
│   │       │       ├── donor-list.jsp
│   │       │       ├── donor-form.jsp
│   │       │       ├── request-list.jsp
│   │       │       └── request-form.jsp
│   │       ├── css/
│   │       │   └── styles.css               # Stylesheet
│   │       └── index.jsp                     # Home page redirect
│   └── test/                                 # Test classes (optional)
├── data/                                     # JSON data storage (auto-created)
│   └── bloodbank.json                        # Database file
└── README.md
```

## URL Mappings

### Servlets (Controllers)
- `/` - HomeServlet (redirects to inventory)
- `/inventory` - InventoryServlet (CRUD operations for blood inventory)
- `/donors` - DonorServlet (CRUD operations for donors)
- `/requests` - RequestServlet (CRUD operations for blood requests)

### Actions
- `GET /inventory` - List all inventory items
- `GET /inventory?action=edit&id={id}` - Show edit form
- `GET /inventory?action=delete&id={id}` - Delete inventory item
- `POST /inventory?action=create` - Create new inventory item
- `POST /inventory?action=update` - Update existing inventory item

Same pattern applies for `/donors` and `/requests`

## Usage

### Managing Blood Inventory
1. Navigate to the "Blood Inventory" tab
2. Click "+ Add Blood Unit" to add new blood units
3. Fill in the form with blood type, quantity, expiry date, and status
4. Use "Edit" to modify existing entries
5. Use "Delete" to remove entries

### Managing Donors
1. Navigate to the "Donors" tab
2. Click "+ Add Donor" to register new donors
3. Enter donor information including name, contact details, blood type, and age
4. Track last donation dates
5. Edit or delete donor records as needed

### Managing Blood Requests
1. Navigate to the "Blood Requests" tab
2. Click "+ New Request" to create a blood request
3. Enter patient information, required blood type, quantity, and urgency level
4. Update request status (pending, fulfilled, cancelled)
5. Manage requests through their lifecycle

## Data Model

### Blood Inventory
```java
- id: Long
- bloodType: String (A+, A-, B+, B-, AB+, AB-, O+, O-)
- quantity: Integer (in ml)
- expiryDate: LocalDate
- donorId: Long (optional)
- dateAdded: String
- status: String (available, reserved, expired)
```

### Donor
```java
- id: Long
- name: String
- email: String
- phone: String
- bloodType: String
- age: Integer
- lastDonationDate: LocalDate (optional)
- address: String
- dateRegistered: String
```

### Blood Request
```java
- id: Long
- patientName: String
- bloodType: String
- quantity: Integer
- hospital: String
- urgency: String (normal, urgent, critical)
- status: String (pending, fulfilled, cancelled)
- dateRequested: String
- dateFulfilled: String (optional)
```

## MVC Pattern Implementation

### Model Layer
- **Purpose**: Represents data structures and business entities
- **Location**: `com.bloodbank.model`
- **Classes**: BloodInventory, Donor, BloodRequest

### View Layer
- **Purpose**: Presentation layer for user interface
- **Location**: `src/main/webapp/WEB-INF/views`
- **Technology**: JSP with JSTL tags

### Controller Layer
- **Purpose**: Handles HTTP requests and coordinates between Model and View
- **Location**: `com.bloodbank.controller`
- **Classes**: Servlets extending HttpServlet

### Service Layer
- **Purpose**: Business logic and transaction management
- **Location**: `com.bloodbank.service`
- **Classes**: BloodBankService

### DAO Layer
- **Purpose**: Data persistence and retrieval
- **Location**: `com.bloodbank.dao`
- **Classes**: BloodBankDAO

## Dependencies

- **javax.servlet-api** (4.0.1) - Servlet API
- **javax.servlet.jsp-api** (2.3.3) - JSP API
- **jstl** (1.2) - JSP Standard Tag Library
- **jackson-databind** (2.15.2) - JSON processing
- **junit** (4.13.2) - Testing framework

## Future Enhancements

- Database integration (MySQL, PostgreSQL, or H2)
- User authentication and authorization
- Search and filter functionality
- Reports and analytics
- Email notifications
- Blood compatibility checking
- Inventory alerts for low stock or expiring units
- RESTful API endpoints
- Spring Framework migration

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `pom.xml`:
```xml
<configuration>
    <port>8081</port>
</configuration>
```

### Data File Location
The data file is created in the `data/` directory relative to where the application runs. For deployed WAR files, this will be in the Tomcat working directory.

## License

MIT
