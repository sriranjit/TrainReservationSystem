# Train Ticket Reservation System

## Overview
The Train Ticket Reservation System is a console-based application that allows users to register, book, and manage train tickets. It utilizes Java to provide functionalities like user registration, login, ticket booking, PNR status checking, and ticket cancellation.

### Features
- **User Registration and Login:** Users can register with basic details and receive a system-generated password for future logins.
- **Train Schedule Lookup:** Users can search for trains based on the date of journey, origin, destination, and class type.
- **Ticket Booking:** Users can book tickets if seats are available or opt for Reservation Against Cancellation (RAC) if seats are unavailable.
- **PNR Status:** Users can check the status of their booked tickets using the PNR number.
- **Ticket Cancellation:** Users can cancel their tickets, and any RAC bookings will be automatically confirmed if seats become available.

## Technologies Used
- **Java:** Core programming language for the system.
- **File Handling:** Used for logging transactions.

## How to Run
1. Clone this repository using:
    ```bash
    git clone https://github.com/your-username/train-ticket-reservation-system.git
    ```
2. Navigate to the project directory:
    ```bash
    cd train-ticket-reservation-system
    ```
3. Ensure Java 8 or higher is installed on your system. Compile and run the program:
    ```bash
    javac Main.java
    java Main
    ```
4. Follow the prompts to interact with the system.

## Menu Walkthrough
Upon running the system, you will be presented with the following options:

```bash
1. Book Ticket(s)
2. Check PNR Status
3. Cancel Ticket(s)
4. Go Back
```

### Booking Tickets
```bash
Date of Journey: [Enter date]
Origin Station: [Enter origin]
Destination Station: [Enter destination]
Class Type: [Enter class type]
Train Number: [Select train number]
Number of Passengers: [Enter number of passengers]
Passenger Details:
  Name of Passenger 1: [Enter name]
  Age: [Enter age]
  Gender: [Enter gender]
```
If seats are unavailable, you will be asked:
```bash
Seats unavailable. Would you like to proceed with RAC booking? (y/n)
```
### PNR Status Check
```bash
Enter your PNR Number: [Enter PNR]
PNR Status: [Confirmed/RAC/Cancelled]
```
### Cancelling Tickets
```bash
Enter your PNR Number: [Enter PNR]
Verify to cancel ticket:
Enter your phone number: [Enter phone number]
Enter your password: [Enter password]
```
---
## Author

- **Name:** Sri Ranjit M
- **Email:** [sriranjitmohan26@gmail.com](mailto:sriranjitmohan26@gmail.com)
- **GitHub:** [sriranjit](https://github.com/sriranjit)
