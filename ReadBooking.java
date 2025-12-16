// For reading files line by line
import java.io.BufferedReader;
import java.io.FileReader;

// For handling dates without time
import java.time.LocalDate;

// For parsing date strings
import java.time.format.DateTimeFormatter;

// List and ArrayList for storing bookings
import java.util.ArrayList;
import java.util.List;

// Utility class to read bookings from a CSV file
public class ReadBooking {

    // Formatter for parsing dates in yyyy-MM-dd format
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Reads bookings from a CSV file and returns a List of Booking objects
    public static List<Booking> readBookings(String filePath) {

        // List to store all bookings read from file
        List<Booking> bookings = new ArrayList<>();

        // Try-with-resources ensures BufferedReader is closed automatically
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean firstLine = true; // Skip header line

            while ((line = read.readLine()) != null) {

                // Skip the first line (header)
                if (firstLine) { firstLine = false; continue; }

                // Trim whitespace
                line = line.trim();

                // Skip empty lines or lines containing spaces or hyphens
                if (line.isEmpty() || line.contains(" ") || line.contains("-")) continue;

                // Split CSV line into fields
                String[] data = line.split(",");

                // Skip lines with insufficient data
                if (data.length < 8) continue;

                // Assign fields to variables
                String bookingID = data[0];
                String surname = data[1];
                String middleName = data[2];
                String firstName = data[3];
                int partySize = Integer.parseInt(data[4]);
                LocalDate startDate = LocalDate.parse(data[5], formatter);
                LocalDate endDate = LocalDate.parse(data[6], formatter);
                String status = data[7];

                // Create new Booking object and add it to the list
                bookings.add(new Booking(bookingID, surname, firstName, middleName, partySize, startDate, endDate, status));
            }

        } catch (Exception e) {
            // Print stack trace if reading or parsing fails
            e.printStackTrace();
        }

        // Return the list of bookings
        return bookings;
    }

    // Displays bookings in a formatted console table
    public static void Display(String filePath) {

        // Read bookings from CSV
        List<Booking> bookings = readBookings(filePath);

        if (bookings.isEmpty()) {
            System.out.println("Booking list is Empty");
        } else {
            // Print table header
            System.out.printf("%10s %10s %10s %10s %10s %15s %15s %s\n",
                              "BookingID", "Surname", "PartySize", "Check-in", "Check-Out", "Status", "MiddleName", "FirstName");

            // Print each booking in formatted row
            for (Booking booking : bookings) {
                System.out.printf("%10s %10s %5d %15s %15s %10s %10s %10s%n",
                                  booking.getBookingID(),
                                  booking.getSurname(),
                                  booking.getPartySize(),
                                  booking.getStartDate(),
                                  booking.getEndDate(),
                                  booking.getStatus(),
                                  booking.getMiddleName(),
                                  booking.getFirstName());
            }
        }
    }
}
