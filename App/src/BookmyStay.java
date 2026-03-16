abstract class Room
{
    String roomType;
    int availableRooms;

    Room(String roomType, int availableRooms)
    {
        this.roomType = roomType;
        this.availableRooms = availableRooms;
    }

    abstract void displayRoomInfo();
}

class StandardRoom extends Room
{
    StandardRoom(int availableRooms)
    {
        super("Standard Room", availableRooms);
    }

    void displayRoomInfo()
    {
        System.out.println("Room Type: " + roomType);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("----------------------------");
    }
}

class DeluxeRoom extends Room
{
    DeluxeRoom(int availableRooms)
    {
        super("Deluxe Room", availableRooms);
    }

    void displayRoomInfo()
    {
        System.out.println("Room Type: " + roomType);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("----------------------------");
    }
}

public class BookmyStay
{
    public static void main(String[] args)
    {
        System.out.println("=================================");
        System.out.println("       Welcome to BookMyStay     ");
        System.out.println("=================================");

        StandardRoom standard = new StandardRoom(10);
        DeluxeRoom deluxe = new DeluxeRoom(5);

        System.out.println("\nRoom Availability:\n");

        standard.displayRoomInfo();
        deluxe.displayRoomInfo();

        System.out.println("Application Terminated.");
    }
}