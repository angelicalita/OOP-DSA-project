// Swing components (JFrame, JButton, JTable, etc.)
import javax.swing.*;

// Table model for managing JTable data
import javax.swing.table.DefaultTableModel;

// Layout managers (BorderLayout, GridLayout, GridBagLayout)
import java.awt.*;

// Date handling (modern Java time API)
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// List interface for storing rooms and bookings
import java.util.List;

/*
   MainLayout.java
   Swing UI for Hotel Management
   - Displays Rooms and Bookings
   - Add Booking with date pickers
   - View Bookings in JTable
*/

// Main window class that extends JFrame (a window)
public class MainLayout extends JFrame {

    // Service class that provides room-related data
    private RoomService roomService;

    // Service class that provides booking-related data
    private BookingService bookingService;

    // Table to display rooms
    private JTable roomTable;

    // Table to display bookings
    private JTable bookingTable;

    // Model that controls room table data
    private DefaultTableModel roomTableModel;

    // Model that controls booking table data
    private DefaultTableModel bookingTableModel;

    // Formatter for displaying dates consistently in tables
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor receives services and sets up the window
    public MainLayout(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;

        // Window title
        setTitle("Hotel Management System");

        // Window size
        setSize(900, 600);

        // Close the application when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center window on screen
        setLocationRelativeTo(null);

        // Initialize UI components
        initUI();
    }

    // Builds all UI components and layouts
    private void initUI() {

        // Use BorderLayout for main window
        setLayout(new BorderLayout());

        // Sidebar panel for navigation buttons
        JPanel sidebar = new JPanel();

        // GridLayout: 4 rows, 1 column, spacing 5px
        sidebar.setLayout(new GridLayout(4, 1, 5, 5));

        // Sidebar buttons
        JButton btnRooms = new JButton("View Rooms");
        JButton btnBookings = new JButton("View Bookings");
        JButton btnAddBooking = new JButton("Add Booking");
        JButton btnExit = new JButton("Exit");

        // Add buttons to sidebar
        sidebar.add(btnRooms);
        sidebar.add(btnBookings);
        sidebar.add(btnAddBooking);
        sidebar.add(btnExit);

        // Place sidebar on the left
        add(sidebar, BorderLayout.WEST);

        // Main panel that switches views using CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());

        // Place main panel in the center
        add(mainPanel, BorderLayout.CENTER);

        // Column names for rooms table
        String[] roomColumns = {"Unit#", "Capacity", "Status", "BookingID", "Price"};

        // Model for rooms table
        roomTableModel = new DefaultTableModel(roomColumns, 0);

        // Rooms JTable
        roomTable = new JTable(roomTableModel);

        // Scroll pane for rooms table
        JScrollPane roomScroll = new JScrollPane(roomTable);

        // Add rooms view to CardLayout
        mainPanel.add(roomScroll, "ROOMS");

        // Column names for bookings table
        String[] bookingColumns = {"BookingID", "Surname", "Middle", "First", "PartySize", "StartDate", "EndDate", "Status"};

        // Model for bookings table
        bookingTableModel = new DefaultTableModel(bookingColumns, 0);

        // Bookings JTable
        bookingTable = new JTable(bookingTableModel);

        // Scroll pane for bookings table
        JScrollPane bookingScroll = new JScrollPane(bookingTable);

        // Add bookings view to CardLayout
        mainPanel.add(bookingScroll, "BOOKINGS");

        // CardLayout controller
        CardLayout cl = (CardLayout) mainPanel.getLayout();

        // Show rooms table when button is clicked
        btnRooms.addActionListener(e -> {
            populateRoomTable();
            cl.show(mainPanel, "ROOMS");
        });

        // Show bookings table when button is clicked
        btnBookings.addActionListener(e -> {
            populateBookingTable();
            cl.show(mainPanel, "BOOKINGS");
        });

        // Open dialog to add a new booking
        btnAddBooking.addActionListener(e -> openAddBookingDialog());

        // Exit application
        btnExit.addActionListener(e -> System.exit(0));

        // Load data initially
        populateRoomTable();
        populateBookingTable();
    }

    // Loads room data into the rooms table
    private void populateRoomTable() {

        // Clear existing rows
        roomTableModel.setRowCount(0);

        // Get list of rooms from service
        List<Room> rooms = roomService.getRooms();

        // Add each room as a row in the table
        for (Room r : rooms) {
            roomTableModel.addRow(new Object[]{
                    r.getUnitNumber(),
                    r.getUnitCapacity(),
                    r.getUnitStatus(),
                    r.getBookingID(),
                    r.getBasePrice()
            });
        }
    }

    // Loads booking data into the bookings table
    private void populateBookingTable() {

        // Clear existing rows
        bookingTableModel.setRowCount(0);

        // Get list of bookings from service
        List<Booking> bookings = bookingService.getBookings();

        // Add each booking as a row in the table
        for (Booking b : bookings) {
            bookingTableModel.addRow(new Object[]{
                    b.getBookingID(),
                    b.getSurname(),
                    b.getMiddleName(),
                    b.getFirstName(),
                    b.getPartySize(),
                    b.getStartDate().format(dateFormatter),
                    b.getEndDate().format(dateFormatter),
                    b.getStatus()
            });
        }
    }

    // Opens a dialog window for adding a booking
    private void openAddBookingDialog() {

        // Modal dialog attached to main window
        JDialog dialog = new JDialog(this, "Add Booking", true);

        // Dialog size
        dialog.setSize(400, 450);

        // GridBagLayout for flexible form layout
        dialog.setLayout(new GridBagLayout());

        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Text fields for booking details
        JTextField txtBookingID = new JTextField(15);
        JTextField txtSurname = new JTextField(15);
        JTextField txtMiddle = new JTextField(15);
        JTextField txtFirst = new JTextField(15);
        JTextField txtPartySize = new JTextField(15);

        // Spinner for start date selection
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));

        // Spinner for end date selection
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));

        // Dropdown for booking status
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"PENDING PAYMENT", "PAID", "REFUNDED"});

        // Labels for the form
        String[] labels = {"Booking ID:", "Surname:", "Middle Name:", "First Name:", "Party Size:", "Start Date:", "End Date:", "Status:"};

        // Matching input fields
        JComponent[] fields = {txtBookingID, txtSurname, txtMiddle, txtFirst, txtPartySize, startDateSpinner, endDateSpinner, statusBox};

        // Add labels and fields to dialog
        for (int i = 0; i < labels.length; i++) {

            // Label column
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.5;
            gbc.anchor = GridBagConstraints.CENTER;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            dialog.add(lbl, gbc);

            // Input field column
            gbc.gridx = 1;
            gbc.weightx = 0.5;
            gbc.anchor = GridBagConstraints.CENTER;
            dialog.add(fields[i], gbc);
        }

        // Submit button
        JButton btnSubmit = new JButton("Add Booking");

        // Position submit button
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(btnSubmit, gbc);

        // Handle booking submission
        btnSubmit.addActionListener(e -> {
            try {
                // Read input values
                String bookingID = txtBookingID.getText();
                String surname = txtSurname.getText();
                String middle = txtMiddle.getText();
                String first = txtFirst.getText();
                int partySize = Integer.parseInt(txtPartySize.getText());

                // Convert spinner dates to LocalDate
                LocalDate start = LocalDate.parse(new java.text.SimpleDateFormat("yyyy-MM-dd")
                        .format(((java.util.Date) startDateSpinner.getValue())));
                LocalDate end = LocalDate.parse(new java.text.SimpleDateFormat("yyyy-MM-dd")
                        .format(((java.util.Date) endDateSpinner.getValue())));

                // Get selected status
                String status = statusBox.getSelectedItem().toString();

                // Create new Booking object
                Booking b = new Booking(bookingID, surname, first, middle, partySize, start, end, status);

                // Add booking to service list
                bookingService.getBookings().add(b);

                // Refresh table
                populateBookingTable();

                // Close dialog
                dialog.dispose();
            } catch (Exception ex) {

                // Show error if input is invalid
                JOptionPane.showMessageDialog(dialog, "Invalid input: " + ex.getMessage());
            }
        });

        // Center dialog relative to main window
        dialog.setLocationRelativeTo(this);

        // Show dialog
        dialog.setVisible(true);
    }

    // Program entry point
    public static void main(String[] args) {

        // Load room data from CSV
        RoomService roomService = new RoomService(ReadRooms.readRooms("DB/rooms.csv"));

        // Load booking data from CSV
        BookingService bookingService = new BookingService(ReadBooking.readBookings("DB/booking.csv"));

        // Run Swing UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainLayout main = new MainLayout(roomService, bookingService);
            main.setVisible(true);
        });
    }
}
