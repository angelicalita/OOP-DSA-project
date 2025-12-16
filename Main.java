// Ensures Swing components are created and updated on the Event Dispatch Thread
import javax.swing.SwingUtilities;

// Main class – program entry point
public class Main {

    // Main method where the program starts running
    public static void main(String[] args) {

        // File path for rooms CSV data
        String roomPath = "DB/rooms.csv";

        // File path for bookings CSV data
        String bookingPath = "DB/booking.csv";

        // Load room data from CSV file and pass it to RoomService
        RoomService roomService = new RoomService(ReadRooms.readRooms(roomPath));

        // Load booking data from CSV file and pass it to BookingService
        BookingService bookingService = new BookingService(ReadBooking.readBookings(bookingPath));

        // Start the Swing UI on the Event Dispatch Thread (required for Swing safety)
        SwingUtilities.invokeLater(() -> {

            // Create and display the main application window
            new MainLayout(roomService, bookingService);
        });
    }
}
