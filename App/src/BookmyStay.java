import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

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

class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    int availableRooms;
    Map<Integer, Reservation> bookings;

    public SystemState(int availableRooms, Map<Integer, Reservation> bookings) {
        this.availableRooms = availableRooms;
        this.bookings = bookings;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "bookings.dat";

    // Save system state
    public static void save(SystemState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load system state
    public static SystemState load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) in.readObject();
            System.out.println("System state loaded successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return new SystemState(5, new HashMap<>());
        }
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        // 🔄 Simulate system startup (recovery)
        SystemState state = PersistenceService.load();

        int availableRooms = state.availableRooms;
        Map<Integer, Reservation> bookings = state.bookings;

        // Add new booking
        Reservation r1 = new Reservation(101, "Varun", 2);

        if (r1.getRooms() <= availableRooms) {
            bookings.put(r1.getReservationId(), r1);
            availableRooms -= r1.getRooms();
            System.out.println("Booking confirmed for " + r1.getGuestName());
        } else {
            System.out.println("Not enough rooms.");
        }

        System.out.println("Available Rooms: " + availableRooms);

        // 🔄 Simulate system shutdown (save state)
        SystemState newState = new SystemState(availableRooms, bookings);
        PersistenceService.save(newState);
    }
}