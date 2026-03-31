import java.util.*;

class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    private int reservationId;
    private double basePrice;

    public Reservation(int reservationId, double basePrice) {
        this.reservationId = reservationId;
        this.basePrice = basePrice;
    }

    public int getReservationId() {
        return reservationId;
    }

    public double getBasePrice() {
        return basePrice;
    }
}

class AddOnServiceManager {
    private Map<Integer, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(int reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public double calculateTotalCost(Reservation reservation) {
        double total = reservation.getBasePrice();
        List<AddOnService> services = serviceMap.get(reservation.getReservationId());

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }
        return total;
    }

    public void displayServices(int reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Selected Services:");
        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " : ₹" + s.getPrice());
        }
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        Reservation r1 = new Reservation(101, 2000);

        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService breakfast = new AddOnService("Breakfast", 300);
        AddOnService spa = new AddOnService("Spa", 500);

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(101, wifi);
        manager.addService(101, breakfast);
        manager.addService(101, spa);

        manager.displayServices(101);

        double totalCost = manager.calculateTotalCost(r1);
        System.out.println("Total Cost: ₹" + totalCost);
    }
}