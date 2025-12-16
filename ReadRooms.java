// For reading files line by line
import java.io.*;

// For using dynamic arrays
import java.util.ArrayList;
import java.util.List;

// Utility class to read rooms from a CSV file
public class ReadRooms 
{
    // Reads rooms from a CSV file and returns a List of Room objects
    // TODO: handle cases where CSV is not properly structured (less than 5 columns)
    public static List<Room> readRooms(String filePath) 
    {
        // List to store all room objects
        List<Room> rooms = new ArrayList<>();

        // Try-with-resources ensures BufferedReader is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        { 
            String line;      // Current line being read
            String[] data;    // Split CSV fields
            boolean firstLine = true; // Skip header line

            while ((line = br.readLine()) != null) 
            {
                // Skip the first line (header)
                if (firstLine) 
                {        
                    firstLine = false;
                    continue;
                }

                // Trim whitespace
                line = line.trim();

                // Skip empty lines or lines containing spaces or hyphens
                if (line.isEmpty() || line.contains(" ") || line.contains("-")) continue;

                // Split CSV line into fields
                data = line.split(",");

                // Skip lines with insufficient data
                if (data.length < 5) continue; // Expected 5 columns: unitNumber, capacity, status, bookingID, basePrice

                // Trim whitespace from each field
                for (int i = 0; i < data.length; i++) 
                {
                    data[i] = data[i].trim();
                }

                // Parse room data
                int unitNumber = Integer.parseInt(data[0]);
                int unitCapacity = Integer.parseInt(data[1]);
                String unitStatus = data[2];
                String bookingID = data[3].isEmpty() ? null : data[3]; // If bookingID is empty, set as null
                int basePrice = Integer.parseInt(data[4]);

                // Create Room object and add to the list
                Room room = new Room(unitNumber, unitCapacity, unitStatus, bookingID, basePrice);
                rooms.add(room);
            }

            // If CSV only contains header, return empty list
            if (rooms.isEmpty()) 
            {
                return rooms;
            }
        
        } catch (IOException e) 
        {
            // Print exception if reading file fails
            e.printStackTrace();
        }

        // Return the list of rooms
        return rooms;
    }

    // Displays rooms in a formatted console table
    public static void Display(String FilePath) 
    {
        // Read rooms from CSV
        List<Room> rooms = readRooms(FilePath);

        if(rooms.isEmpty())
        {
            System.out.println("Room list is empty");
        }
        else
        {
            // Print table header
            System.out.printf("%-12s %-15s %-15s %-10s%n",
                "unitNumber", "capacity", "status", "basePrice");

            // Print each room in formatted row
            for (Room r : rooms) 
            {
                System.out.printf("%-12d %-15d %-15s %-10d%n",
                        r.getUnitNumber(),
                        r.getUnitCapacity(),
                        r.getUnitStatus(),
                        r.getBasePrice());
            }
        }
    }
}
