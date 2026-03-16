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

    void updateAvailability(String roomType, int newCount)
    {
        inventory.put(roomType, newCount);
    }

    void displayInventory()
    {
        System.out.println("\nCurrent Room Inventory:");
        System.out.println("----------------------------");

        for (String roomType : inventory.keySet())
        {
            System.out.println("Room Type: " + roomType +
                    " | Available: " + inventory.get(roomType));
        }
    }
}

public class BookmyStay
{
    public static void main(String[] args)
    {
        System.out.println("=================================");
        System.out.println("       Welcome to BookMyStay     ");
        System.out.println("=================================");

        // Initialize inventory system
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.registerRoom("Standard Room", 10);
        inventory.registerRoom("Deluxe Room", 5);
        inventory.registerRoom("Suite Room", 3);

        // Display current inventory
        inventory.displayInventory();

        // Update availability
        inventory.updateAvailability("Standard Room", 8);

        System.out.println("\nAfter Updating Availability:");
        inventory.displayInventory();
    }
}