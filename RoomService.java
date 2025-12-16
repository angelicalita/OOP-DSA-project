// For using dynamic arrays and lists
import java.util.ArrayList;
import java.util.List;

// Service class that manages all room-related operations
public class RoomService 
{
    // List of all rooms
    private List<Room> rooms;

    // Constructor receives a list of rooms (usually loaded from CSV)
    public RoomService(List<Room> rooms) 
    {
        this.rooms = rooms;
    }

    // Creates a new room and adds it to the list
    public Room createRoom(int unitNumber, int unitCapacity, String unitStatus, 
                           String bookingID, double price) 
    {
        // Prevent adding duplicate rooms with the same unit number
        if (roomExist(unitNumber)) 
        {
            System.out.println("Room already exists.");
            return null;
        }

        // Validate room status before creating room
        if (!isValidStatus(unitStatus)) 
        {
            System.out.println("Invalid room status: " + unitStatus);
            return null;
        }

        // Create new Room object and add to the list
        Room r = new Room(unitNumber, unitCapacity, unitStatus, bookingID, price);
        rooms.add(r); 
        return r;
    }

    // Returns the list of all rooms
    public List<Room> getRooms() 
    {
        return rooms;
    }

    // Finds a room by its unit number
    public Room findRoom(int unitNumber) 
    {
        for (Room r : rooms) 
        {
            if (r.getUnitNumber() == unitNumber) 
            {
                return r;
            }
        }
        return null;
    }

    // Checks if a room exists by unit number
    public boolean roomExist(int unitNumber) 
    {
        return findRoom(unitNumber) != null;
    }

    // Returns a list of all available rooms
    public List<Room> getAvailableRooms() 
    {
        List<Room> availableRooms = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getUnitStatus().equalsIgnoreCase("Available")) 
            {
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    // Returns a list of all occupied rooms
    public List<Room> getOccupiedRooms() 
    {
        List<Room> occupiedRooms = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getUnitStatus().equalsIgnoreCase("Occupied")) 
            {
                occupiedRooms.add(r);
            }
        }
        return occupiedRooms;
    }

    // Updates the status of a room
    public boolean updateRoomStatus(int unitNumber, String newStatus) 
    {
        // Validate the new status
        if (!isValidStatus(newStatus)) 
        {
            System.out.println("Invalid status: " + newStatus);
            return false;
        }

        // Find the room and update its status
        Room r = findRoom(unitNumber);
        if (r != null) {
            r.setUnitStatus(newStatus);
            return true;
        }
        return false;
    }

    // Deletes a room by unit number
    public boolean deleteRoom(int unitNumber) 
    {
        Room found = findRoom(unitNumber);
        if (found != null) {
            rooms.remove(found);
            return true;
        }
        return false;
    }

    // Validates allowed room statuses
    private boolean isValidStatus(String status) 
    {
        return status.equalsIgnoreCase("Available") ||
               status.equalsIgnoreCase("Occupied")  ||
               status.equalsIgnoreCase("Maintenance");
    }

    // Converts all rooms to a 2D Object array for JTable display in UI
    public Object[][] getRoomsForTable() {
        Object[][] data = new Object[rooms.size()][5];
        for (int i = 0; i < rooms.size(); i++) {
            data[i] = rooms.get(i).toStringDisplay();
        }
        return data;
    }
}
