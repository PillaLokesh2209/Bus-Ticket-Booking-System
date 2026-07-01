# Bus Ticket Booking System

An advanced, desktop-based GUI transit management system engineered in Java utilizing the **Swing** and **AWT** framework components. This system addresses complex operational requirements such as client metadata profiling, interactive grid-mapped layout selection configurations, double-booking validation logic checks, payment tracking audits, and transaction cancellation cancellation lifecycles.

---

## Project Description

Managing short-haul transit seating structures manually often results in scheduling inefficiencies, administrative overhead, and synchronization faults. This application provides an integrated, real-time platform where customers can check seat properties, verify routes, input personal identification parameters, and execute programmatic reservations instantly. 

Designed on sound **Object-Oriented Programming (OOP)** principles, the application decouples data containers from the interface render layout engine, assuring a system framework that can be expanded easily to link to enterprise relational databases like MySQL.

---

## System Capability Blueprint

* **Dynamic Seat Inventory Tracking:** Simulates a 30-passenger luxury coach bus interface layout, assigning structural attributes (Window, Middle, Aisle) to specific nodes dynamically at initialization.
* **State-Driven UI Visual Architecture:** Controls button interactive rendering flags smoothly across runtime state transitions:
    *  **White Background:** Unreserved nodes ready to accept interactive client registration commands.
    *  **Green Background:** Temporary volatile selection buffer stack tracking uncommitted seat targets.
    *  **Red Background:** Finalized transactions that are locked down and protected against data mutation.
* **Intelligent Transaction Validation Engine:** Restricts illegal system entries, including:
    * Multi-node booking attempts with empty tracking selection inputs.
    * Matching Source and Destination values across travel parameters.
    * Real-time locking on committed nodes to prevent overlapping assignments.
* **Dual Execution Management:** Supports single-click transactional rollbacks to automatically free up locked reservation slots back into the public inventory pool and handle mock refund records.

---

##  Software Component Model Breakdown

The software architecture divides the domain space into dedicated classes to follow object isolation boundaries:

### 1. `BusTicketBookingOOP` (The Master Controller Frame)
Inherits from `JFrame`. It builds the window layout, mounts action listeners onto components, buffers real-time selections inside tracking sets (`HashSet`), and orchestrates communication paths between panels.

### 2. `Ticket` (The Core Ledger Class)
Acts as an immutable data snapshot layer compiling travel metadata (`route`, `date`, `busName`, `timing`), seat allocation groups, passenger profiles, and payment histories into a printable digital receipt.

### 3. `Customer` (The Profile Entity)
An encapsulated data model storing traveler metadata:
* Passenger Name
* Verified Phone Contact
* Gender Designation
* Physical Home Address

### 4. `Payment` (The Financial Audit Record)
Enforces structural tracking by bundling the payment processor mode selected (e.g., PhonePe, Debit Card, Cash) alongside a unique alphanumeric transactional trace token.

### 5. `Seat` (The Encapsulated Array Node)
Defines individual layout metrics. By hiding structural statuses (`booked`, `type`) from global scopes and only exposing access methods, it guarantees strict encapsulation.

---

## 📊 Application Processing Pipeline
