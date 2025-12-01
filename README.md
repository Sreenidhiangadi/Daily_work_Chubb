# FlightBookingUsingMicroservices

A complete microservices-based Flight Booking Platform built with **Spring Boot WebFlux**, **Spring Cloud**, **Kafka**, and **MongoDB**.  
Each service runs independently with its own database and communicates through **API Gateway**, **Eureka**, **OpenFeign**, and **Kafka events**.

---

##  Key Features
- User Registration & Login (Admin/User)
- Add / Update Flights (Admin)
- Search Flights by Route
- Reactive Flight Booking with PNR generation
- Booking History (User)
- Cancel Booking with seat release
- Email Notifications (Booking Confirm / Cancel)
- Kafka-based asynchronous communication
- API Gateway routing
- Config Server for centralized configs
- Eureka for service discovery
- Reactive programming using WebFlux + MongoDB

---

##  Tech Stack

| Component | Technology |
|----------|------------|
| Backend Framework | Spring Boot WebFlux |
| Microservices | Spring Cloud, Eureka, Config Server |
| API Gateway | Spring Cloud Gateway |
| Database | MongoDB (Reactive) |
| Messaging | Apache Kafka |
| Inter-Service Calls | OpenFeign |
| Tests | JUnit 5, Mockito, StepVerifier |
| Language | Java 17 |

---

##  What I Learned & Implemented
- Reactive programming vs traditional MVC
- Building cloud-native microservices
- Service discovery & dynamic routing with Eureka + Gateway
- Kafka event-driven communication patterns
- WebFlux non-blocking APIs
- Using Feign for inter-service communication
- Distributed configs using Config Server
- Managing multiple MongoDB databases (per service)
- Designing booking workflow with PNR logic
- Handling async email notifications

---
## 🗄️ ER Diagram

```mermaid
erDiagram
    USER ||--o{ TICKET : books
    FLIGHT ||--o{ TICKET : includes
    TICKET ||--o{ PASSENGER : contains

    USER {
        string id
        string name
        string gender
        int age
        string email
        string password
        Role role
    }

    FLIGHT {
        string id
        string airline
        string fromPlace
        string toPlace
        datetime departureTime
        datetime arrivalTime
        int price
        int totalSeats
        int availableSeats
    }

    TICKET {
        string id
        string pnr
        string userId
        string departureFlightId
        string returnFlightId
        FlightType tripType
        datetime bookingTime
        string seatsBooked
        double totalPrice
        boolean canceled
    }

    PASSENGER {
        string id
        string name
        string gender
        int age
        string seatNumber
        string mealPreference
        string ticketId
    }



