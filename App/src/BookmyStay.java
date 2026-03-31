import java.util.*;

class Reservation {
    private int reservationId;
    private String guestName;
    private double amount;

    public Reservation(int reservationId, String guestName, double amount) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.amount = amount;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public double getAmount() {
        return amount;
    }
}

class BookingHistory {
    private List<Reservation> historyList = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        historyList.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return historyList;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("Booking History:");
        for (Reservation r : reservations) {
            System.out.println("ID: " + r.getReservationId() +
                    ", Guest: " + r.getGuestName() +
                    ", Amount: ₹" + r.getAmount());
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (Reservation r : reservations) {
            totalRevenue += r.getAmount();
        }

        System.out.println("\n--- Report Summary ---");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        Reservation r1 = new Reservation(101, "Varun", 2500);
        Reservation r2 = new Reservation(102, "Rahul", 3000);
        Reservation r3 = new Reservation(103, "Anita", 1800);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());
    }
}