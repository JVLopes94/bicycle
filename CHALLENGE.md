# Backend Software Engineering Challenge: Bicycle Fleet Management System

## Problem Statement:

You are tasked with designing and implementing a backend service for a Bicycle Fleet Management System. The system will be used by a bicycle rental service that manages a fleet of bicycles and users who rent them. The system will allow for creating, renting, returning, and managing the bicycles, while tracking their status and location in real-time.

Your goal is to design a scalable and efficient system that can handle multiple users renting and returning bicycles concurrently. The system should support the following key features:

This challenge focuses on the backend aspects of the system, but candidates should be prepared to discuss architectural choices, database design, and API design in detail.

## Key Requirements:

### Bicycle Model:
    Each bicycle has the following attributes:
        id (string) – unique identifier for the bicycle.
        model (string) – the model or make of the bicycle (e.g., "Mountain Bike", "Road Bike").
        status (enum) – whether the bicycle is available, rented, or under maintenance.
        location (string) – the current location of the bicycle (e.g., "Location A", "Location B").
        last_maintenance_date (datetime) – the date when the bicycle was last serviced.

### User Model:
    Each user has the following attributes:
        id (string) – unique identifier for the user.
        name (string) – the user's full name.
        email (string) – the user's email address.
        membership_start_date (datetime) – the date the user started using the service.
        active_rentals (list of rented bicycles) – a list of bicycles the user currently has rented.

### Rental Service:
    A user can rent a bicycle if it is available and not under maintenance.
    A user can return a bicycle at any time. Upon returning, the bicycle's status is set to "available", and its location is updated to the user’s return location.
    The system must track when bicycles were rented and returned, including the user and the timestamps for each action.

### Maintenance Service:
    A bicycle can be marked as "under maintenance".
    When a bicycle is under maintenance, it is unavailable for rent until it is serviced and its status is set to "available" again.

### Fleet Management System:
    Allow the management of the bicycle fleet. The system should be able to:
        Add new bicycles to the fleet.
        Remove bicycles from the fleet.
        List bicycles, filtering by status (available, rented, under maintenance).
        Get bicycle details based on their id.

## Challenge Requirements:

    API Design: Design a RESTful API to interact with the system. Implement the following endpoints:
        POST /bicycles - Add a new bicycle to the fleet.
        GET /bicycles/{id} - Get details of a specific bicycle by ID.
        PUT /bicycles/{id}/maintenance - Mark a bicycle as under maintenance.
        PUT /bicycles/{id}/available - Mark a bicycle as available after maintenance.
        POST /rentals - Rent a bicycle (user provides user ID and bicycle ID).
        PUT /rentals/{rental_id}/return - Return a rented bicycle (update location and availability).

    Database Design:
        Design the appropriate database schema for the system. Use SQL or NoSQL (depending on your preference), and consider relationships between users, bicycles, and rentals.
        Focus on scalability and efficient querying (e.g., querying for available bikes, rentals by user, etc.).

    Concurrency Handling:
        Ensure that concurrent rental requests do not allow the same bicycle to be rented by two different users at the same time.
        Handle race conditions (e.g., when multiple users attempt to rent the same bicycle simultaneously).

    Scalability and Performance:
        Design the system to handle a large number of bicycles and users, with efficient queries for bike availability and rental history.
        Optimize the endpoints for performance, particularly for high-traffic requests like listing available bicycles.

    Testing:
        Write unit tests for the core logic of bicycle rentals and maintenance tracking.
        Implement integration tests for API endpoints.

## Bonus Challenges (Optional):

    Real-Time Location Tracking:
        Extend the system to support real-time bicycle location tracking using a WebSocket API. The location should update in real-time when the user rents or returns a bicycle.

    User Analytics:
        Provide analytics endpoints that give insights into:
            The number of active rentals for each user.
            The average rental time for bicycles.
            The bicycles that have been rented the most.

    Caching:
        Implement caching for frequently accessed data, like the list of available bicycles, to reduce database load.

    Security:
        Implement token-based authentication (e.g., JWT) to secure API endpoints and restrict access to authorized users only.
        Handle edge cases where invalid input or unauthorized actions are attempted (e.g., renting an unavailable bicycle).

## Evaluation Criteria:

    Correctness:
        Does the system function as expected, with no bugs or errors?

    Scalability:
        How well would the system scale to handle thousands or millions of bicycles and users?

    Code Quality:
        Is the code well-organized, readable, and maintainable? Is it modular and easy to extend?

    Efficiency:
        Are database queries optimized? Are race conditions handled effectively?

    Testing:
        Are unit tests and integration tests comprehensive and cover edge cases?

## Technology Stack Suggestions:

    Backend Framework: Django, Flask, Express, FastAPI, etc.
    Database: PostgreSQL, MySQL, MongoDB, or other suitable database systems.
    Authentication: JWT, OAuth 2.0.
    Caching: Redis (for caching frequently accessed data).
