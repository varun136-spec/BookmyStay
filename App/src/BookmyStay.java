abstract class Room
{
    String roomType;
    double price;

    Room(String roomType, double price)
    {
        this.roomType = roomType;
        this.price = price;
    }

    abstract void displayDetails();
}

class StandardRoom extends Room
{
    StandardRoom()
    {
        super("Standard Room", 2000);
    }

    void displayDetails()
    {
        System.out.println("Room Type: " + roomType + " | Price: Rs." + price);
    }
}

class DeluxeRoom extends Room
{
    DeluxeRoom()
    {
        super("Deluxe Room", 3500);
    }

    void displayDetails()
    {
        System.out.println("Room Type: " + roomType + " | Price: Rs." + price);
    }
}

class SuiteRoom extends Room
{
    SuiteRoom()
    {
        super("Suite Room", 5000);
    }

    void displayDetails()
    {
        System.out.println("Room Type: " + roomType + " | Price: Rs." + price);
    }
}

class RoomInventory
{
    java.util.HashMap<String, Integer> inventory;

    RoomInventory()
    {
        inventory = new java.util.HashMap<String, Integer>();
    }

    void registerRoom(String roomType, int count)
    {
        inventory.put(roomType, count);
    }

    int getAvailability(String roomType)
    {
        return inventory.get(roomType);
    }
}

class SearchService
{
    RoomInventory inventory;

    SearchService(RoomInventory inventory)
    {
        this.inventory = inventory;
    }

    void searchRooms(Room[] rooms)
    {
        System.out.println("\nAvailable Rooms:");
        System.out.println("--------------------------");

        for (int i = 0; i < rooms.length; i++)
        {
            int available = inventory.getAvailability(rooms[i].roomType);

            if (available > 0)   // filter unavailable rooms
            {
                rooms[i].displayDetails();
                System.out.println("Available: " + available);
                System.out.println("--------------------------");
            }
        }
    }
}

public class BookmyStay
{
    public static void main(String[] args)
    {
        System.out.println("===== Welcome to BookMyStay =====");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 5);
        inventory.registerRoom("Deluxe Room", 2);
        inventory.registerRoom("Suite Room", 0);

        // Create room objects
        Room standard = new StandardRoom();
        Room deluxe = new DeluxeRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = {standard, deluxe, suite};

        // Search service
        SearchService search = new SearchService(inventory);

        // Guest searches for rooms
        search.searchRooms(rooms);
    }
}