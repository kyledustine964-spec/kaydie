import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// class object booking
class Booking {
    private int id;
    private String date;
    private String time;
    private String name;
    private String pickup;
    private String dropoff;
    private double distance;
    private double fare;

    // declare object variable
    public Booking(int id, String name, String pickup, String dropoff, double distance) {
        this.id = id;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        this.date = currentDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
        this.time = currentTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        this.name = name;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.distance = distance;
        this.fare = 25.0 + (distance > 1 ? (distance - 1) * 20.0 : 0);
    }

    public int getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }

    public String toString() {
        return id + " | " + date + " | " + time + " | " + name + " | " + pickup + " | " + dropoff + " | " + distance + " km | ₱" + fare;
    }
}

public class BookingSystem {
    private static ArrayList<Booking> bookings = new ArrayList<>(); // create new array list with variable bookings
    private static int nextId = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // infinite loop
        while (true) {
            System.out.println("\nRIDE-HAILING BOOKING SYSTEM");
            System.out.println("\nSystem Menu");
            System.out.println("[a] View All Bookings");
            System.out.println("[b] Add Booking");
            System.out.println("[c] Delete Booking");
            System.out.println("[d] Generate Booking Report");
            System.out.println("[e] Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "a":
                    viewAllBookings();
                    break;
                case "b":
                    addBooking();
                    break;
                case "c":
                    deleteBooking();
                    break;
                case "d":
                    generateReport();
                    break;
                case "e":
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    // method for add booking
    private static void addBooking() {

        // asks for user name
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        // asks for user pick up location
        System.out.print("Enter pick up location: ");
        String pickup = sc.nextLine();

        // asks for user drop off location
        System.out.print("Enter drop off location: ");
        String dropoff = sc.nextLine();

        // asks for distance in kilometers
        System.out.print("Enter distance (km): ");
        double distance = Double.parseDouble(sc.nextLine());
        bookings.add(new Booking(nextId++, name, pickup, dropoff, distance)); // adds to the booking array list the given inputs
        System.out.println("Booking added.");
    }

    // method for deleting a booking
    private static void deleteBooking() {
        System.out.print("Enter booking ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = bookings.removeIf(b -> b.getId() == id);
        if (removed) System.out.println("Booking deleted.");
        else System.out.println("Booking ID not found.");
    }

    // method for generating report
    private static void generateReport() {

        // condition to check if booking array list is empty
        if (bookings.isEmpty()) {
            System.out.println("No bookings to report.");
            return;
        }
        double totalDistance = 0;
        double totalFare = 0;
        for (Booking b : bookings) {
            System.out.println(b);
            totalDistance += b.getDistance();
            totalFare += b.getFare();
        }
        System.out.println("Total bookings: " + bookings.size());
        System.out.println("Total distance: " + totalDistance + " km");
        System.out.println("Total fare collected: ₱" + totalFare);
    }
}
