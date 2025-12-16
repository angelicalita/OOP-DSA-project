// Used for handling dates without time
import java.time.LocalDate;

// Used for parsing date strings in a specific format
import java.time.format.DateTimeFormatter;

// Dynamic array implementation
import java.util.ArrayList;

// List interface for storing bookings
import java.util.List;

// Service class that manages all booking-related operations
public class BookingService {

    // List that stores all booking objects
    private final List<Booking> bookings;

    // Formatter to parse dates in yyyy-MM-dd format
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor receives a list of bookings (usually loaded from CSV)
    public BookingService(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Returns the list of all bookings
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Creates a new booking and adds it to the list.
     * startDateString and endDateString must be in "yyyy-MM-dd" format.
     */
    public Booking createBooking(String bookingID, String surname, String middleName,
                                 String firstName, int partySize, String startDateString,
                                 String endDateString, String status) {

        // Check if booking ID already exists
        if (bookingExists(bookingID)) {
            System.out.println("Booking already exists.");
            return null;
        }

        // Validate booking status before creating booking
        if (!isValidStatus(status)) {
            System.out.println("Invalid booking status: " + status);
            return null;
        }

        try {
            // Convert start date string to LocalDate
            LocalDate startDate = LocalDate.parse(startDateString, formatter);

            // Convert end date string to LocalDate
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            // Create new Booking object
            Booking booking = new Booking(bookingID, surname, firstName, middleName,
                                          partySize, startDate, endDate, status);

            // Add booking to the list
            bookings.add(booking);

            // Return the newly created booking
            return booking;

        } catch (Exception e) {

            // Handle invalid date format errors
            System.out.println("Error parsing dates. Use yyyy-MM-dd format.");
            e.printStackTrace();
            return null;
        }
    }

    // Finds a booking by its booking ID
    public Booking findBooking(String bookingID) {
        for (Booking b : bookings) {
            if (b.getBookingID().equals(bookingID)) {
                return b;
            }
        }
        return null;
    }

    // Checks if a booking with the given ID already exists
    public boolean bookingExists(String bookingID) {
        return findBooking(bookingID) != null;
    }

    // Updates the status of an existing booking
    public boolean updateBookingStatus(String bookingID, String newStatus) {

        // Validate the new status
        if (!isValidStatus(newStatus)) {
            System.out.println("Invalid status: " + newStatus);
            return false;
        }

        // Find the booking to update
        Booking booking = findBooking(bookingID);
        if (booking != null) {

            // Update booking status
            booking.setStatus(newStatus);
            return true;
        }
        return false;
    }

    // Deletes a booking by booking ID
    public boolean deleteBooking(String bookingID) {

        // Find the booking first
        Booking found = findBooking(bookingID);
        if (found != null) {

            // Remove booking from the list
            bookings.remove(found);
            return true;
        }
        return false;
    }

    // Returns a list of all bookings with PAID status
    public List<Booking> getPaidBookings() {
        List<Booking> paid = new ArrayList<>();
        for (Booking b : bookings) {

            // Check if booking status is PAID
            if (b.getStatus().equalsIgnoreCase("PAID")) {
                paid.add(b);
            }
        }
        return paid;
    }

    // Returns a list of all bookings with PENDING PAYMENT status
    public List<Booking> getPendingPayments() {
        List<Booking> pending = new ArrayList<>();
        for (Booking b : bookings) {

            // Check if booking status is PENDING PAYMENT
            if (b.getStatus().equalsIgnoreCase("PENDING PAYMENT")) {
                pending.add(b);
            }
        }
        return pending;
    }

    // Validates allowed booking statuses
    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("PAID")
            || status.equalsIgnoreCase("PENDING PAYMENT")
            || status.equalsIgnoreCase("REFUNDED");
    }
}
