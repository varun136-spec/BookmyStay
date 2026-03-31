import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private int rooms;
    private double amount;

    public Reservation(int reservationId, String guestName, int rooms, double amount) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.rooms = rooms;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }
}

class InvalidBookingValidator {

    public static void validate(Reservation reservation) throws InvalidBookingException {

        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (reservation.getRooms() <= 0) {
            throw new InvalidBookingException("Number of rooms must be greater than 0.");
        }

        if (reservation.getAmount() <= 0) {
            throw new InvalidBookingException("Amount must be greater than 0.");
        }
    }
}

class BookingService {
    private int availableRooms = 5;

    public void confirmBooking(Reservation reservation) throws InvalidBookingException {

        InvalidBookingValidator.validate(reservation);

        if (reservation.getRooms() > availableRooms) {
            throw new InvalidBookingException("Not enough rooms available.");
        }

        availableRooms -= reservation.getRooms();

        System.out.println("Booking Confirmed for " + reservation.getGuestName());
        System.out.println("Remaining Rooms: " + availableRooms);
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {
            Reservation r1 = new Reservation(101, "Varun", 2, 2500);
            service.confirmBooking(r1);

            Reservation r2 = new Reservation(102, "", 1, 2000);
            service.confirmBooking(r2);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System continues running safely...");
    }
}