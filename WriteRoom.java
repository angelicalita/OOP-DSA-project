// For writing text to files
import java.io.BufferedWriter;
import java.io.FileWriter;

// For working with lists of Room objects
import java.util.List;

// Utility class to write Room objects to CSV files
public class WriteRoom 
{   
    // Creates a new empty room CSV file with only the header (overwrites if exists)
    public static void writeRoom(String Path)
    {
        try(BufferedWriter write = new BufferedWriter(new FileWriter(Path))) 
        {   
            // Write CSV header
            write.write("UnitNumber,UnitCapacity,UnitStatus,BookingID,BasePrice");
            write.newLine();
            
        } catch (Exception e) 
        {
            // Exception handling (currently empty)
            // Could log or print error here
        }
    }

    // Saves the entire list of rooms to the CSV file (overwrites existing content)
    public static void saveRoom(String Path, List<Room> rooms)
    {
        String line;
        try(BufferedWriter write = new BufferedWriter(new FileWriter(Path))) 
        {
            // Write CSV header
            write.write("UnitNumber,UnitCapacity,UnitStatus,BookingID,BasePrice");
            write.newLine();

            // Write each room as a CSV row
            for(Room r : rooms)
            {
                line = r.getUnitNumber() + "," +
                    r.getUnitCapacity() + "," +
                    r.getUnitStatus() + "," +
                    r.getBookingID() + "," +
                    r.getBasePrice() + "," +  // <-- duplicated basePrice? might be a typo
                    r.getBasePrice();

                write.write(line);
                write.newLine();
            }
            write.flush(); // Ensure all data is written
        } catch (Exception e) 
        {
            // Exception handling (currently empty)
            // Could log or print error here
        }
    }
    
    // Appends a single room to the CSV file without overwriting existing content
    public static void appendRoom(String Path, Room room)
    {
        boolean appendMode = true; // Open file in append mode
        String line;

        try(BufferedWriter write = new BufferedWriter(new FileWriter(Path, appendMode))) 
        {
            // Format the room as a CSV line
            line = room.getUnitNumber() + "," +
                   room.getUnitCapacity() + "," +
                   room.getUnitStatus() + "," +
                   room.getBookingID() + "," +
                   room.getBasePrice() + "," +  // <-- duplicated basePrice? might be a typo
                   room.getBasePrice();

            // Write the line to the file
            write.write(line);
            write.newLine();
            write.flush(); // Ensure data is written immediately
                
        } catch (Exception e) 
        {
            // Exception handling (currently empty)
            // Could log or print error here
        }
    }
}
