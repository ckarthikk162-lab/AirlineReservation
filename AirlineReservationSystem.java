
import java.util.Scanner;

class Flight {
    int flightNumber;
    String source;
    String destination;
    int seats;

    Flight(int flightNumber, String source, String destination, int seats) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    void displayFlight() {
        System.out.println("Flight No       : " + flightNumber);
        System.out.println("Source          : " + source);
        System.out.println("Destination     : " + destination);
        System.out.println("Available Seats : " + seats);
        System.out.println("-----------------------------");
    }
}

class Passenger {
    String name;
    int age;
    String phone;
    int flightNumber;

    Passenger(String name, int age, String phone, int flightNumber) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.flightNumber = flightNumber;
    }

    void displayPassenger() {
        System.out.println("Passenger Name : " + name);
        System.out.println("Age            : " + age);
        System.out.println("Phone          : " + phone);
        System.out.println("Flight Number  : " + flightNumber);
        System.out.println("-----------------------------");
    }
}

public class AirlineReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static Flight[] flights = {
        new Flight(101, "Hyderabad", "Delhi", 50),
        new Flight(102, "Chennai", "Mumbai", 40),
        new Flight(103, "Bangalore", "Delhi", 45)
    };

    static Passenger[] passengers = new Passenger[100];
    static int passengerCount = 0;

    static void viewFlights() {
        System.out.println("\n===== AVAILABLE FLIGHTS =====");

        for (Flight f : flights) {
            f.displayFlight();
        }
    }

    static void bookTicket() {

        System.out.print("Enter Flight Number: ");
        int flightNo = sc.nextInt();
        sc.nextLine();

        Flight selectedFlight = null;

        for (Flight f : flights) {
            if (f.flightNumber == flightNo) {
                selectedFlight = f;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }

        if (selectedFlight.seats <= 0) {
            System.out.println("No seats available!");
            return;
        }

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        passengers[passengerCount] =
            new Passenger(name, age, phone, flightNo);

        passengerCount++;
        selectedFlight.seats--;

        System.out.println("\nTicket booked successfully!");
        System.out.println("Passenger : " + name);
        System.out.println("Flight    : " + flightNo);
    }

    static void viewBookings() {

        System.out.println("\n===== BOOKED TICKETS =====");

        if (passengerCount == 0) {
            System.out.println("No bookings available.");
            return;
        }

        for (int i = 0; i < passengerCount; i++) {
            passengers[i].displayPassenger();
        }
    }

    static void cancelTicket() {

        if (passengerCount == 0) {
            System.out.println("No bookings available.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Passenger Name to Cancel: ");
        String name = sc.nextLine();

        int index = -1;

        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].name.equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Booking not found!");
            return;
        }

        int flightNo = passengers[index].flightNumber;

        for (Flight f : flights) {
            if (f.flightNumber == flightNo) {
                f.seats++;
                break;
            }
        }

        for (int i = index; i < passengerCount - 1; i++) {
            passengers[i] = passengers[i + 1];
        }

        passengerCount--;
        passengers[passengerCount] = null;

        System.out.println("Ticket cancelled successfully!");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   AIRLINE RESERVATION SYSTEM");
            System.out.println("================================");
            System.out.println("1. View Flights");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewFlights();
                    break;

                case 2:
                    bookTicket();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    cancelTicket();
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using Airline Reservation System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}

