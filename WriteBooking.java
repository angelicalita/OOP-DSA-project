// For writing text to files
import java.io.BufferedWriter;
import java.io.FileWriter;

// For formatting LocalDate objects
import java.time.format.DateTimeFormatter;

// For working with lists of bookings
import java.util.List;

// Utility class to write Booking objects to CSV files
public class WriteBooking {
    // Formatter for dates in yyyy-MM-dd format
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Creates a new CSV file with only the header (overwrites if exists)
    public static void writeBooking(String filePath) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            write.write("BookingID,Surname,MiddleName,FirstName,PartySize,StartDate,EndDate,Status");
            write.newLine();
        } catch (Exception e) {
            // Print any errors that occur while creating the file
            e.printStackTrace();
        }
    }

    // Saves the entire list of bookings to the CSV file (overwrites existing content)
    public static void saveBooking(String filePath, List<Booking> bookings) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            write.write("BookingID,Surname,MiddleName,FirstName,PartySize,StartDate,EndDate,Status");
            write.newLine();

            // Write each booking as a CSV row
            for (Booking b : bookings) {
                String line = b.getBookingID() + "," +
                              b.getSurname() + "," +
                              b.getMiddleName() + "," +
                              b.getFirstName() + "," +
                              b.getPartySize() + "," +
                              b.getStartDate().format(formatter) + "," +
                              b.getEndDate().format(formatter) + "," +
                              b.getStatus();
                write.write(line);
                write.newLine();
            }
        } catch (Exception e) {
            // Print any errors that occur while writing the file
            e.printStackTrace();
        }
    }

    // Appends a single booking to the CSV file without overwriting existing content
    public static void appendBooking(String filePath, Booking b) {
        boolean appendMode = true; // Open file in append mode
        try (BufferedWriter write = new BufferedWriter(new FileWriter(filePath, appendMode))) {
            // Format the booking as a CSV line
            String line = b.getBookingID() + "," +
                          b.getSurname() + "," +
                          b.getMiddleName() + "," +
                          b.getFirstName() + "," +
                          b.getPartySize() + "," +
                          b.getStartDate().format(formatter) + "," +
                          b.getEndDate().format(formatter) + "," +
                          b.getStatus();
            // Write the line to the file
            write.write(line);
            write.newLine();
        } catch (Exception e) {
            // Print any errors that occur while appending
            e.printStackTrace();
        }
    }
}
