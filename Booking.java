// Used to represent dates without time (check-in and check-out)
import java.time.LocalDate;

// Booking class represents a single hotel booking record
public class Booking {

    // Unique identifier for the booking
    private String bookingID;

    // Guest's last name
    private String surname;

    // Guest's first name
    private String firstName;

    // Guest's middle name
    private String middleName;

    // Number of people included in the booking
    private int partySize;

    // Booking start date (check-in)
    private LocalDate startDate;

    // Booking end date (check-out)
    private LocalDate endDate;

    // Booking status (PENDING PAYMENT, PAID, REFUNDED)
    private String status;

    // Constructor initializes all booking details
    public Booking(String bookingID, String surname, String firstName,
                   String middleName, int partySize, LocalDate startDate, LocalDate endDate, String status) {

        // Assign constructor parameters to class fields
        this.bookingID = bookingID;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.partySize = partySize;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // ======================
    // Getter methods
    // Used to access private fields safely
    // ======================

    public String getBookingID() { return bookingID; }
    public String getSurname() { return surname; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public int getPartySize() { return partySize; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }

    // ======================
    // Setter methods
    // Used to modify private fields
    // ======================

    public void setBookingID(String bookingID) { this.bookingID = bookingID; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setPartySize(int partySize) { this.partySize = partySize; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setStatus(String status) { this.status = status; }

    // Converts booking data into a String array
    // Used for displaying booking details in a JTable row
    public String[] toStringDisplay() {
        return new String[]{
                bookingID,
                surname,
                middleName,
                firstName,
                String.valueOf(partySize),
                startDate.toString(),
                endDate.toString(),
                status
        };
    }
}
