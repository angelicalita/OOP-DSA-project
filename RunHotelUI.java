// For running Swing UI safely on the Event Dispatch Thread
import javax.swing.*;

public class RunHotelUI {
    public static void main(String[] args) {
        try {
            // Load room data from CSV file and create RoomService
            RoomService roomService = new RoomService(ReadRooms.readRooms("DB/rooms.csv"));

            // Load booking data from CSV file and create BookingService
            BookingService bookingService = new BookingService(ReadBooking.readBookings("DB/booking.csv"));

            // Launch the Swing UI on the Event Dispatch Thread (required for Swing thread safety)
            SwingUtilities.invokeLater(() -> {
                // Create the main application window
                MainLayout mainWindow = new MainLayout(roomService, bookingService);

                // Display the window
                mainWindow.setVisible(true);  // <-- THIS shows your window
            });

        } catch (Exception e) {
            // Print any exception to the console for debugging
            e.printStackTrace();  
        }
    }
}
