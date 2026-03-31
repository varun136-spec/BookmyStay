import java.util.*;

class BookingRequest {
    private String guestName;
    private int rooms;

    public BookingRequest(String guestName, int rooms) {
        this.guestName = guestName;
        this.rooms = rooms;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRooms() {
        return rooms;
    }
}

class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
        notify(); // wake up waiting threads
    }

    public synchronized BookingRequest getRequest() {
        while (queue.isEmpty()) {
            try {
                wait(); // wait until request comes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}

class BookingProcessor {
    private int availableRooms = 5;

    public synchronized void processBooking(BookingRequest request) {

        System.out.println(Thread.currentThread().getName() +
                " processing " + request.getGuestName());

        if (request.getRooms() <= availableRooms) {
            availableRooms -= request.getRooms();
            System.out.println("Booking confirmed for " + request.getGuestName() +
                    " | Rooms left: " + availableRooms);
        } else {
            System.out.println("Booking failed for " + request.getGuestName() +
                    " | Not enough rooms");
        }
    }
}

class Worker extends Thread {
    private BookingQueue queue;
    private BookingProcessor processor;

    public Worker(BookingQueue queue, BookingProcessor processor, String name) {
        super(name);
        this.queue = queue;
        this.processor = processor;
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            BookingRequest req = queue.getRequest();
            processor.processBooking(req);
        }
    }
}

public class BookmyStay {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        BookingProcessor processor = new BookingProcessor();

        Worker t1 = new Worker(queue, processor, "Thread-1");
        Worker t2 = new Worker(queue, processor, "Thread-2");

        t1.start();
        t2.start();

        queue.addRequest(new BookingRequest("Varun", 2));
        queue.addRequest(new BookingRequest("Rahul", 2));
        queue.addRequest(new BookingRequest("Anita", 2));
        queue.addRequest(new BookingRequest("Kiran", 1));
    }
}