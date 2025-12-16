// Used to get the current date and time
import java.time.LocalDateTime;

// Used to format date into a specific string pattern
import java.time.format.DateTimeFormatter;

// List interface to store bookings
import java.util.List;

// Utility class for generating unique booking IDs
public class GenerateID 
{
    // Generates a booking ID based on booking count and current date
    public static String generateID(List<Booking> bookings)
    {
        // Variable to store the generated ID
        String ID;

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Date format: day-month-year (ddMMyy)
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyy");

        // Create booking ID
        // BKT = Booking Ticket
        // count(bookings) = number of existing bookings
        // now.format(format) = current date
        ID = "BKT-" + count(bookings) + "-" + now.format(format);

        // Return the generated booking ID
        return ID;
    }

    // Counts how many bookings exist in the list
    public static int count(List<Booking> bookings)
    {
        // Counter variable
        int bookingCount = 0;

        // Loop until bookingCount equals the size of the list
        while (bookingCount < bookings.size()) 
        {
            bookingCount++;
        }

        // Return total number of bookings
        return bookingCount;
    }
}
