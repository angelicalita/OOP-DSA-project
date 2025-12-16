// Represents a hotel room with its properties
public class Room
{
    // Room number (unique identifier for the room)
    private int unitNumber;

    // Maximum number of guests the room can accommodate
    private int unitCapacity;

    // Current status of the room (e.g., AVAILABLE, OCCUPIED)
    private String unitStatus;

    // Booking ID associated with the room, if any (null if not booked)
    private String bookingID;

    // Base price of the room
    private double basePrice;

    // Constructor to initialize all room fields
    public Room(int unitNumber, int unitCapacity, String unitStatus, String bookingID, double basePrice) 
    {
        this.unitNumber = unitNumber;
        this.unitCapacity = unitCapacity;
        this.unitStatus = unitStatus;
        this.bookingID = bookingID;
        this.basePrice = basePrice;
    }

    // ======================
    // Getter methods
    // ======================

    public int getUnitNumber() 
    {
        return unitNumber;
    }

    public int getUnitCapacity() 
    {
        return unitCapacity;
    }

    public String getUnitStatus() 
    {
        return unitStatus;
    }

    public String getBookingID() 
    {
        return bookingID;
    }

    public double getBasePrice() 
    {
        return basePrice;
    }

    // ======================
    // Setter methods
    // ======================

    public void setUnitNumber(int unitNumber) 
    {
        this.unitNumber = unitNumber;
    }

    public void setUnitCapacity(int unitCapacity) 
    {
        this.unitCapacity = unitCapacity;
    }

    public void setUnitStatus(String unitStatus) 
    {
        this.unitStatus = unitStatus;
    }

    public void setBookingID(String bookingID) 
    {
        this.bookingID = bookingID;
    }

    public void setBasePrice(double basePrice) 
    {
        this.basePrice = basePrice;
    }

    // Converts room data into a String array for JTable display
    public String[] toStringDisplay() {
        return new String[]{
                String.valueOf(unitNumber),
                String.valueOf(unitCapacity),
                unitStatus,
                bookingID == null ? "" : bookingID, // Display empty string if no booking
                String.valueOf(basePrice)
        };
    }
}
