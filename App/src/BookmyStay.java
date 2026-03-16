class Reservation
{
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType)
    {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display()
    {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

class BookingRequestQueue
{
    java.util.Queue<Reservation> queue;

    BookingRequestQueue()
    {
        queue = new java.util.LinkedList<Reservation>();
    }

    void addRequest(Reservation r)
    {
        queue.add(r);
        System.out.println("Booking request added for " + r.guestName);
    }

    void showQueue()
    {
        System.out.println("\nCurrent Booking Queue (FCFS):");
        System.out.println("-------------------------------");

        for (Reservation r : queue)
        {
            r.display();
        }
    }
}

public class BookmyStay
{
    public static void main(String[] args)
    {
        System.out.println("===== BookMyStay Booking Requests =====");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Guests submit booking requests
        Reservation r1 = new Reservation("Rahul", "Standard Room");
        Reservation r2 = new Reservation("Anita", "Deluxe Room");
        Reservation r3 = new Reservation("Karan", "Suite Room");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        // Display queue in arrival order
        requestQueue.showQueue();

        System.out.println("\nRequests are waiting for allocation...");
    }
}