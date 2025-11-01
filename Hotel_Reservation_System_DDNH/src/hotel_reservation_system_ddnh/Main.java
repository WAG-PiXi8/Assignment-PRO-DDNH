package hotel_reservation_system_ddnh; 

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerManager customerManager = new CustomerManager();
    private static HotelManager hotelManager = new HotelManager();
    private static ReservationManager reservationManager = new ReservationManager(hotelManager, customerManager);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Hotels");
            System.out.println("3. Manage Rooms");
            System.out.println("4. Manage Reservation");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: manageCustomers(); break;
                case 2: manageHotels(); break;
                case 3: manageRooms(); break;
                case 4: manageReservations(); break;
                case 5:
                    running = false;
                    break;
                default: System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void manageCustomers() {
        System.out.println("\n--- Manage Customers ---");
        System.out.println("1. Add Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. List Customers");
        System.out.print("Choose: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Full Name: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Phone: ");
                String phone = scanner.nextLine();
                String id = customerManager.generateCustomerId();
                customerManager.addCustomer(new Customer(id, name, email, phone));
                System.out.println("Added customer with ID: " + id);
                break;
            case 2:
                System.out.print("Customer ID: ");
                String cid = scanner.nextLine();
                Customer customerToUpdate = customerManager.findCustomerById(cid);
                if (customerToUpdate == null) { 
                    System.out.println("Error: Customer ID not found: " + cid); 
                    break; 
                }
                System.out.println("Updating customer: " + customerToUpdate.getFullName());
                System.out.print("New Name (enter to skip): ");
                String newName = scanner.nextLine();
                System.out.print("New Email (enter to skip): ");
                String newEmail = scanner.nextLine();
                System.out.print("New Phone (enter to skip): ");
                String newPhone = scanner.nextLine();
                customerManager.updateCustomer(cid, newName, newEmail, newPhone);
                break;
            case 3:
                System.out.print("Customer ID: ");
                cid = scanner.nextLine();
                customerManager.deleteCustomer(cid);
                break;
            case 4:
                List<Customer> customers = customerManager.getAllCustomers();
                if (customers.isEmpty()) {
                    System.out.println("No customers found in the system.");
                } else {
                    customers.forEach(System.out::println);
                }
                break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void manageHotels() {
        System.out.println("\n--- Manage Hotels ---");
        System.out.println("1. Add Hotel");
        System.out.println("2. Update Hotel");
        System.out.println("3. Delete Hotel");
        System.out.println("4. List Hotels");
        System.out.print("Choose: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Hotel Name: ");
                String name = scanner.nextLine();
                System.out.print("Address: ");
                String address = scanner.nextLine();
                String id = hotelManager.generateHotelId();
                hotelManager.addHotel(new Hotel(id, name, address)); 
                System.out.println("Added hotel with ID: " + id);
                break;
            case 2:
                System.out.print("Hotel ID: ");
                String hid = scanner.nextLine();
                Hotel hotelToUpdate = hotelManager.findHotelById(hid);
                if (hotelToUpdate == null) {
                    System.out.println("Error: Hotel ID not found: " + hid);
                    break;
                }
                System.out.println("Updating hotel: " + hotelToUpdate.getHotelName());
                System.out.print("New Name (enter to skip): ");
                String newName = scanner.nextLine();
                System.out.print("New Address (enter to skip): ");
                String newAddress = scanner.nextLine();
                hotelManager.updateHotel(hid, newName, newAddress);
                break;
            case 3:
                System.out.print("Hotel ID: ");
                hid = scanner.nextLine();
                hotelManager.deleteHotel(hid);
                break;
            case 4:
                List<Hotel> hotels = hotelManager.getAllHotels();
                if (hotels.isEmpty()) {
                    System.out.println("No hotels found in the system.");
                } else {
                    hotels.forEach(System.out::println);
                }
                break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void manageRooms() {
        System.out.println("\n--- Manage Rooms ---");
        System.out.println("1. Add Room");
        System.out.println("2. Update Room");
        System.out.println("3. Delete Room");
        System.out.println("4. List Rooms");
        System.out.print("Choose: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Hotel ID: ");
                String hid = scanner.nextLine();
                if (hotelManager.findHotelById(hid) == null) {
                    System.out.println("Hotel not found.");
                    return;
                }
                System.out.print("Room Number: ");
                String num = scanner.nextLine();
                System.out.print("Room Type (Single/Double/Suite): ");
                String type = scanner.nextLine();
                System.out.print("Price per Night: ");
                double price = getDoubleInput();
                String rid = hotelManager.generateRoomId();
                hotelManager.addRoomToHotel(hid, new Room(rid, num, type, price, hid));
                System.out.println("Added room with ID: " + rid);
                break;
            case 2:
                System.out.print("Room ID: ");
                rid = scanner.nextLine();
                Room roomToUpdate = hotelManager.findRoomById(rid);
                if (roomToUpdate == null) {
                    System.out.println("Error: Room ID not found: " + rid);
                    break; 
                }
                System.out.println("Updating room: " + roomToUpdate.getRoomNumber());
                System.out.print("New Number (enter to skip): ");
                String newNum = scanner.nextLine();
                System.out.print("New Type (enter to skip): ");
                String newType = scanner.nextLine();
                System.out.print("New Price (0 to skip): ");
                double newPrice = getDoubleInput();
                hotelManager.updateRoom(rid, newNum, newType, newPrice);
                break;
            case 3:
                System.out.print("Room ID: ");
                rid = scanner.nextLine();
                hotelManager.deleteRoom(rid);
                break;
            case 4:
                List<Room> rooms = hotelManager.getAllRooms();
                if (rooms.isEmpty()) {
                    System.out.println("No rooms found in the system.");
                } else {
                    rooms.forEach(System.out::println);
                }
                break;
            default: System.out.println("Invalid choice.");
        }
    }

    private static void manageReservations() {
        System.out.println("\n--- Manage Reservations ---");
        System.out.println("1. Create Reservation");
        System.out.println("2. Add Room to Reservation");
        System.out.println("3. Remove Room from Reservation");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. View Reservation Details");
        System.out.println("6. List Reservations");
        System.out.print("Choose: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Customer ID: ");
                String cid = scanner.nextLine();
                System.out.print("Check-in Date (YYYY-MM-DD): ");
                LocalDate checkIn = getDateInput();
                System.out.print("Check-out Date (YYYY-MM-DD): ");
                LocalDate checkOut = getDateInput();
                if (checkIn.isAfter(checkOut)) {
                    System.out.println("Invalid dates.");
                    return;
                }
                String resId = reservationManager.generateReservationId();
                
                List<String> roomIds = new ArrayList<>();
                while (true) {
                    System.out.print("Add Room ID (enter 'done' to finish): ");
                    String rid = scanner.nextLine();
                    if (!reservationManager.isRoomAvailable(rid, checkIn, checkOut)){
                        System.out.println("Room " + rid + " is not available for the dates.");
                        continue;
                    }
                    if (rid.equals("done")) break;
                    roomIds.add(rid);
                }
                Reservation res = new Reservation(resId, cid, checkIn, checkOut, roomIds);
                reservationManager.createReservation(res);
                break;
            case 2:
                System.out.print("Reservation ID: ");
                resId = scanner.nextLine();
                System.out.print("Room ID: ");
                String rid = scanner.nextLine();
                reservationManager.addRoomToReservation(resId, rid);
                break;
            case 3:
                System.out.print("Reservation ID: ");
                resId = scanner.nextLine();
                System.out.print("Room ID: ");
                rid = scanner.nextLine();
                reservationManager.removeRoomFromReservation(resId, rid);
                break;
            case 4:
                System.out.print("Reservation ID: ");
                resId = scanner.nextLine();
                reservationManager.cancelReservation(resId);
                break;
            case 5:
                System.out.print("Reservation ID: ");
                resId = scanner.nextLine();
                Reservation r = reservationManager.findReservationById(resId);
                if (r != null) {
                    Customer c = customerManager.findCustomerById(r.getCustomerId());
                    System.out.println("Details: " + r);
                    System.out.println("Customer: " + (c != null ? c : "Not found"));
                    System.out.print("Rooms: ");
                    for (String id : r.getRoomIds()) {
                        Room room = hotelManager.findRoomById(id);
                        System.out.println(room != null ? room : "Not found");
                    }
                } else {
                    System.out.println("Reservation not found.");
                }
                break;
            case 6:
                reservationManager.getAllReservations().forEach(System.out::println);
                break;
            default: System.out.println("Invalid choice.");
        }
    }
    
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter number.");
            return getIntInput();
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter number.");
            return getDoubleInput();
        }
    }
    
    private static LocalDate getDateInput() {
        try {
            return LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return getDateInput();
        }
    }
}