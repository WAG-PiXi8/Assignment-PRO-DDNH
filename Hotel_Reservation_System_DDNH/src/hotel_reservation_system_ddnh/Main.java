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
        FileManager.loadData(customerManager, hotelManager, reservationManager);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Hotels");
            System.out.println("3. Manage Rooms");
            System.out.println("4. Manage Reservation");
            System.out.println("5. View Reports");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: manageCustomers(); break;
                case 2: manageHotels(); break;
                case 3: manageRooms(); break;
                case 4: manageReservations(); break;
                case 5: viewReports(); break;
                case 6:
                    FileManager.saveData(customerManager, hotelManager, reservationManager);
                    running = false;
                    break;
                default: System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void manageCustomers() {
        while (true) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            if (choice == 5) break;

            switch (choice) {
                case 1:
                    System.out.print("Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email;
                    while(true){
                        email = scanner.nextLine();
                        if(email.matches("^\\S+@\\S+\\.\\S+$")) break;
                        System.out.print("Invalid email: ");
                    }
                    System.out.print("Phone: ");
                    String phone;
                    while(true){
                        phone = scanner.nextLine();
                        if(phone.matches("\\d+")) break;
                        System.out.print("Invalid phonenumber: ");
                    }
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
                        continue;  
                    }
                    System.out.println("Updating customer: " + customerToUpdate.getFullName());
                    System.out.print("New Name (enter to skip): ");
                    String newName = scanner.nextLine();
                    System.out.print("New Email (enter to skip): ");
                    String newEmail;
                    while(true){
                        newEmail = scanner.nextLine();
                        if(newEmail.matches("^\\S+@\\S+\\.\\S+$")) break;
                        System.out.print("Invalid email: ");
                    }
                    System.out.print("New Phone (enter to skip): ");
                    String newPhone;
                    while(true){
                        newPhone = scanner.nextLine();
                        if(newPhone.matches("\\d+")) break;
                        System.out.print("Invalid phonenumber: ");
                    }
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
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageHotels() {
        while (true) {
            System.out.println("\n--- Manage Hotels ---");
            System.out.println("1. Add Hotel");
            System.out.println("2. Update Hotel");
            System.out.println("3. Delete Hotel");
            System.out.println("4. List Hotels");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            if (choice == 5) break;

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
                        continue;  
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
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageRooms() {
        while (true) {
            System.out.println("\n--- Manage Rooms ---");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. List Rooms");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            if (choice == 5) break;

            switch (choice) {
                case 1:
                    System.out.print("Hotel ID: ");
                    String hid = scanner.nextLine();
                    Hotel hotel = hotelManager.findHotelById(hid);
                    if (hotel == null) {
                        System.out.println("Hotel not found.");
                        continue;  
                    }
                    
                    System.out.print("Room Number: ");
                    String num = scanner.nextLine();
                    System.out.print("Room Type (Single/Double/Suite): ");
                    String type = scanner.nextLine();
                    System.out.print("Price per Night: ");
                    double price = getDoubleInput();
                    String roomId = hotelManager.generateRoomId();
                    Room room = new Room(roomId, num, type, price, hid);
                    hotelManager.addRoomToHotel(hid, room);
                    System.out.println("Added room with ID: " + roomId);
                    break;
                case 2:
                    System.out.print("Room ID: ");
                    String rid = scanner.nextLine();
                    Room roomToUpdate = hotelManager.findRoomById(rid);
                    if (roomToUpdate == null) {
                        System.out.println("Error: Room ID not found: " + rid);
                        continue;  
                    }
                    System.out.println("Updating room: " + roomToUpdate.getRoomNumber());
                    System.out.print("New Number (enter to skip): ");
                    String newNumber = scanner.nextLine();
                    System.out.print("New Type (enter to skip): ");
                    String newType = scanner.nextLine();
                    System.out.print("New Price per Night (enter to skip): ");
                    String priceStr = scanner.nextLine();
                    double newPrice = 0;
                    if (!priceStr.trim().isEmpty()) {
                        try {
                            newPrice = Double.parseDouble(priceStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price, skipping.");
                        }
                    }
                    hotelManager.updateRoom(rid, newNumber, newType, newPrice);
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
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void manageReservations() {
        while (true) {
            System.out.println("\n--- Manage Reservations ---");
            System.out.println("1. Create New Reservation");
            System.out.println("2. Add Room to Reservation");
            System.out.println("3. Remove Room from Reservation");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Reservation Details");
            System.out.println("6. List All Reservations");
            System.out.println("7. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            if (choice == 7) break;

            String resId;
            switch (choice) {
                case 1:
                    System.out.print("Customer ID: ");
                    String cid = scanner.nextLine();
                    Customer cust = customerManager.findCustomerById(cid); 
                    if (cust == null) {
                        System.out.println("Customer not found.");
                        continue;
                    }
                    System.out.print("Check-in Date (YYYY-MM-DD): ");
                    LocalDate checkIn = getDateInput();
                    System.out.print("Check-out Date (YYYY-MM-DD): ");
                    LocalDate checkOut = getDateInput();
                    if (checkIn.isAfter(checkOut)) {
                        System.out.println("Invalid dates.");
                        continue;  
                    }
                    resId = reservationManager.generateReservationId();
                    List<String> roomIds = new ArrayList<>();
                    while (true) {
                        System.out.print("Add Room ID (enter 'done' to finish): ");
                        String rid = scanner.nextLine().trim();
                        if (rid.equalsIgnoreCase("done")) break;
                        Room roomCheck = hotelManager.findRoomById(rid); 
                        if (roomCheck == null) {
                            System.out.println("Room not found: " + rid);
                            continue;
                        }
                        if (!reservationManager.isRoomAvailable(rid, checkIn, checkOut)) {
                            System.out.println("Room " + rid + " is not available for the dates.");
                            continue;
                        }
                        roomIds.add(rid);
                    }
                    if (roomIds.isEmpty()) {
                        System.out.println("No rooms added. Reservation not created.");
                        continue;  
                    }
                    Reservation res = new Reservation(resId, cid, checkIn, checkOut, roomIds);
                    reservationManager.createReservation(res);
                    break;
                case 2:
                    System.out.print("Reservation ID: ");
                    resId = scanner.nextLine();
                    res = reservationManager.findReservationById(resId);
                    if (res == null) {
                        System.out.println("Reservation not found.");
                        continue;  // Sửa
                    } else if (res.getStatus().equals("Cancelled")) {
                        System.out.println("Reservation already cancelled.");
                        continue;  // Sửa
                    }
                    System.out.print("Room ID: ");
                    String rid = scanner.nextLine();
                    Room room = hotelManager.findRoomById(rid);  
                    if (room == null) {
                        System.out.println("Room not found.");
                        continue;
                    }
                    reservationManager.addRoomToReservation(resId, rid);
                    break;
                case 3:
                    System.out.print("Reservation ID: ");
                    resId = scanner.nextLine();
                    res = reservationManager.findReservationById(resId);
                    if (res == null) {
                        System.out.println("Reservation not found.");
                        continue;  // Sửa
                    } else if (res.getStatus().equals("Cancelled")) {
                        System.out.println("Reservation already cancelled.");
                        continue;  // Sửa
                    }
                    System.out.print("Room ID: ");
                    rid = scanner.nextLine();
                    room = hotelManager.findRoomById(rid);  
                    if (room == null) {
                        System.out.println("Room not found.");
                        continue;
                    }
                    reservationManager.removeRoomFromReservation(resId, rid);
                    break;
                case 4:
                    System.out.print("Reservation ID: ");
                    resId = scanner.nextLine();
                    res = reservationManager.findReservationById(resId);
                    if (res == null) {
                        System.out.println("Reservation not found.");
                        continue; 
                    }
                    
                    reservationManager.cancelReservation(resId);
                    break;
                case 5:
                    System.out.print("Reservation ID: ");
                    resId = scanner.nextLine();
                    Reservation r = reservationManager.findReservationById(resId);
                    if (r == null) {
                        System.out.println("Reservation not found.");
                        continue;  
                    }
                    Customer c = customerManager.findCustomerById(r.getCustomerId());
                    System.out.println("Details: " + r);
                    System.out.println("Customer: " + (c != null ? c : "Not found"));
                    System.out.print("Rooms: ");
                    for (String id : r.getRoomIds()) {
                        Room rm = hotelManager.findRoomById(id);
                        System.out.println(rm != null ? rm : "Not found");
                    }
                    break;
                case 6:
                    reservationManager.getAllReservations().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewReports() {
        while (true) {
            System.out.println("\n--- View Reports ---");
            System.out.println("1. Sort Customers by total spent");
            System.out.println("2. Sort Hotels by booking count");
            System.out.println("3. Most popular Room type");
            System.out.println("4. Customer with most Reservations");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();
            int order;

            if (choice == 5) break;

            switch (choice) {
                case 1:
                    System.out.println("\n1. Ascending");
                    System.out.println("2. Descending");
                    System.out.print("Choose: ");
                    do {
                        order = getIntInput();
                        if (order == 1 || order == 2) break;
                        System.out.println("Invalid choice.");
                    } while (true);
                    reservationManager.sortCustomersByTotalSpent(order).forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("\n1. Ascending");
                    System.out.println("2. Descending");
                    System.out.print("Choose: ");
                    do {
                        order = getIntInput();
                        if (order == 1 || order == 2) break;
                        System.out.println("Invalid choice.");
                    } while (true);
                    reservationManager.sortHotelsByBookingCount(order).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Most popular room type: " + reservationManager.findMostPopularRoomType());
                    break;
                case 4:
                    Customer c = reservationManager.findCustomerWithMostReservations();
                    System.out.println("Customer with most reservations: " + (c != null ? c : "None"));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
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