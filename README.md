# Cinema Ticket Booking System

A professional Java-based cinema ticket booking system with advanced features including multiple seat types, payment processing, booking management, and real-time statistics tracking.

## Features

### Core Functionality
- **Smart Seat Management**: Three tier seating system (Regular, VIP, Premium) with dynamic pricing
- **Multiple Ticket Types**: Adult, Child, Senior, and Student pricing options
- **Payment Processing**: Support for Cash, Credit Card, Debit Card, and E-Wallet payments
- **Movie Ratings**: Age restriction system (U, P13, PG18) with detailed movie information
- **Booking Cancellation**: Cancel bookings with automatic refund calculation
- **Real-time Statistics**: Track total bookings, revenue, and occupancy rates

### User Experience
- **Interactive Menu System**: Clean, menu-driven interface for easy navigation
- **Beautiful UI**: Professional box-drawing characters and formatted displays
- **Real-time Seat Availability**: Visual seat map showing available and booked seats
- **Detailed Tickets**: Comprehensive ticket printing with all booking details
- **Error Handling**: Robust validation and user-friendly error messages

### Technical Features
- **Exception Handling**: Custom exceptions for booking, payment, and validation errors
- **Type Safety**: Enums for ticket types, seat types, payment methods, and movie ratings
- **Input Validation**: Comprehensive validation on all user inputs
- **Revenue Tracking**: Automatic calculation of total revenue and statistics
- **Modular Design**: Clean separation of concerns with dedicated classes

## Project Structure

### Core Classes
- `Main.java` - Interactive menu system and user interface
- `CinemaSystem.java` - Central system managing all bookings and operations
- `CinemaHall.java` - Cinema hall with seat layout and availability tracking
- `Movie.java` - Movie details including title, genre, duration, and rating
- `Seat.java` - Individual seat with type, status, and customer tracking
- `Ticket.java` - Comprehensive ticket with payment and timestamp details
- `Payment.java` - Payment processing with transaction tracking

### Enums
- `TicketType.java` - Adult, Child, Senior, Student ticket categories
- `SeatType.java` - Regular (1.0x), VIP (1.5x), Premium (2.0x) pricing tiers
- `PaymentMethod.java` - Cash, Credit Card, Debit Card, E-Wallet options
- `MovieRating.java` - U, P13, PG18 age restriction classifications

### Exceptions
- `BookingException.java` - Booking-related error handling
- `InvalidInputException.java` - Input validation error handling
- `PaymentException.java` - Payment processing error handling

## Pricing Structure

**Base Ticket Prices:**
- Adult: RM 15.00
- Child: RM 10.00
- Senior: RM 12.00
- Student: RM 12.00

**Seat Type Multipliers:**
- Regular: x1.0 (base price)
- VIP: x1.5 (50% premium)
- Premium: x2.0 (100% premium)

*Example: Adult ticket in Premium seat = RM 15.00 × 2.0 = RM 30.00*

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Shahrulzaman/Ticket-System.git
   ```

2. Open the project in your Java IDE (IntelliJ IDEA, Eclipse, NetBeans)

3. Ensure Java 8 or higher is installed

4. Run `Main.java` to start the application

5. Follow the interactive menu to:
   - View available shows and seats
   - Book tickets with preferred seat type
   - Process payments
   - Cancel bookings
   - View statistics

## Usage Example

```
1. Select "View Available Shows" to see all movies
2. Choose "Book Ticket" and select a hall
3. View the seat map and choose your seat
4. Enter customer details and select ticket type
5. Choose payment method to complete booking
6. Receive detailed ticket with transaction ID
```

## System Requirements

- **Java Version**: Java 8 or higher
- **Memory**: Minimum 256 MB RAM
- **Storage**: Minimal (uses in-memory ArrayList storage)
- **OS**: Cross-platform (Windows, macOS, Linux)

## Key Highlights

✓ Professional exception handling and error management
✓ Type-safe implementation using Java enums
✓ Comprehensive input validation
✓ Real-time seat availability tracking
✓ Automated pricing calculation based on seat type
✓ Transaction ID generation for payments
✓ Booking cancellation with refund processing
✓ Statistical reporting and revenue tracking
✓ Clean, documented, and maintainable code

## Future Enhancements

- Database integration for persistent storage
- Email/SMS ticket confirmation
- Online booking web interface
- QR code ticket generation
- Multiple showtime scheduling
- Loyalty program integration
- Admin dashboard for management

## Author

Developed as an Object-Oriented Programming project demonstrating professional Java development practices.
