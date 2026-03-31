import java.util.*;

class Reservation {
    private int reservationId;
    private String guestName;
    private int rooms;

    public Reservation(int reservationId, String guestName, int rooms) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.rooms = rooms;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRooms() {
        return rooms;
    }
}

class BookingHistory {
    private Map<Integer, Reservation> bookings = new HashMap<>();

    public void addReservation(Reservation r) {
        bookings.put(r.getReservationId(), r);
    }

    public Reservation getReservation(int id) {
        return bookings.get(id);
    }

    public void removeReservation(int id) {
        bookings.remove(id);
    }

    public boolean exists(int id) {
        return bookings.containsKey(id);
    }
}

class CancellationService {

    private int availableRooms = 5;

    // Each reservation has its own stack (better design)
    private Map<Integer, Stack<String>> rollbackMap = new HashMap<>();

    public void confirmBooking(Reservation r, BookingHistory history) {

        if (r.getRooms() > availableRooms) {
            System.out.println("Booking failed: Not enough rooms.");
            return;
        }

        availableRooms -= r.getRooms();
        history.addReservation(r);

        Stack<String> stack = new Stack<>();

        for (int i = 1; i <= r.getRooms(); i++) {
            String roomId = "Room-" + (availableRooms + i);
            stack.push(roomId);
        }

        rollbackMap.put(r.getReservationId(), stack);

        System.out.println("Booking confirmed for " + r.getGuestName());
        System.out.println("Available Rooms: " + availableRooms);
    }

    public void cancelBooking(int reservationId, BookingHistory history) {

        // ✅ Validation
        if (!history.exists(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        Reservation r = history.getReservation(reservationId);
        Stack<String> stack = rollbackMap.get(reservationId);

        System.out.println("Cancelling booking for " + r.getGuestName());

        // ✅ LIFO rollback
        while (stack != null && !stack.isEmpty()) {
            String room = stack.pop();
            System.out.println("Releasing " + room);
        }

        // ✅ Inventory restoration
        availableRooms += r.getRooms();

        // ✅ Remove from system
        history.removeReservation(reservationId);
        rollbackMap.remove(reservationId);

        System.out.println("Cancellation successful.");
        System.out.println("Available Rooms after rollback: " + availableRooms);
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        CancellationService service = new CancellationService();

        Reservation r1 = new Reservation(101, "Varun", 2);
        Reservation r2 = new Reservation(102, "Rahul", 1);

        service.confirmBooking(r1, history);
        service.confirmBooking(r2, history);

        service.cancelBooking(101, history);

        service.cancelBooking(999, history);
    }
}