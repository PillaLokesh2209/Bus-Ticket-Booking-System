# Bus Ticket Booking System (OOP)

A desktop-based Bus Ticket Booking application built in Java using the **Swing** framework. This project demonstrates core Object-Oriented Programming (OOP) concepts such as Encapsulation, Association, and Component-driven GUI design.

## Features

* **Route Selection:** Choose source/destination cities, departure dates, bus operators, and travel timings.
* **Interactive Seat Layout:** A grid of 30 dynamic seats showing real-time window, middle, and aisle availability.
* **Dynamic State Selection:** * **Green:** Seats currently selected by the user.
    * **Red:** Confirmed bookings (disabled from re-selection).
    * **White:** Open/available seats.
* **Customer & Payment Validation:** Integrates passenger details along with transaction IDs for absolute tracking.
* **Real-time Output:** Detailed instant generation of booking receipts and cancellation logs.

## Application Walkthrough

1. Before Booking (Seat Selection)
Select your route, enter passenger info, and click on desired seats. The interface updates to show your tentative selection in green.
2. After Booking Confirmation
Upon clicking **Book Ticket**, selected seats lock into a red state (booked) and a structured ticket confirmation summary displays at the bottom.
3. Ticket Cancellation
Clicking **Cancel Booking** frees the locked seats, returns them to the available pool, and processes a mock automated refund acknowledgment.

 Object-Oriented Programming (OOP) Architecture

The application is structured into distinct domain classes to follow modular object architecture:
1.  **`Ticket`**: Manages trip specifications, linked customer info, payment logging, and text receipts.
2.  **`Customer`**: Encapsulates passenger data (Name, Phone, Gender, Address).
3.  **`Payment`**: Captures method structures and unique transactional track references.
4.  **`Seat`**: Handles individual seat states, identifying seat index numbers and custom seat properties (Window/Aisle/Middle).

