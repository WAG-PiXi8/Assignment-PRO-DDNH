package hotel_reservation_system_ddnh; 

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CustomerManager customerManager = new CustomerManager();
    private static HotelManager hotelManager = new HotelManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Hotels");
            System.out.println("3. Manage Rooms");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: manageCustomers(); break;
                case 2: manageHotels(); break;
                case 3: manageRooms(); break;
                case 4:
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
}