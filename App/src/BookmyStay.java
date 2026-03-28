import java.util.*;

class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();
    private Set<String> allocatedRooms = new HashSet<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public String allocateRoom(String roomType) {
        if (!isAvailable(roomType)) return null;

        String roomId = UUID.randomUUID().toString();

        while (allocatedRooms.contains(roomId)) {
            roomId = UUID.randomUUID().toString();
        }

        allocatedRooms.add(roomId);
        inventory.put(roomType, inventory.get(roomType) - 1);

        return roomId;
    }
}

class BookingService {
    private Queue<BookingRequest> requestQueue = new LinkedList<>();
    private InventoryService inventoryService;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void addRequest(BookingRequest request) {
        requestQueue.add(request);
    }

    public void processRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        BookingRequest request = requestQueue.poll();

        if (inventoryService.isAvailable(request.roomType)) {
            String roomId = inventoryService.allocateRoom(request.roomType);
            System.out.println("Booking Confirmed for " + request.customerName +
                    " | Room Type: " + request.roomType +
                    " | Room ID: " + roomId);
        } else {
            System.out.println("Booking Failed for " + request.customerName +
                    " | No rooms available");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        bookingService.addRequest(new BookingRequest("Varun", "Single"));
        bookingService.addRequest(new BookingRequest("Rahul", "Double"));
        bookingService.addRequest(new BookingRequest("Ankit", "Single"));

        bookingService.processRequest();
        bookingService.processRequest();
        bookingService.processRequest();
    }
}