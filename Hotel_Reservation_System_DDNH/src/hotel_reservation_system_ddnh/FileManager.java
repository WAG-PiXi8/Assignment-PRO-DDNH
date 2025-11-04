/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_reservation_system_ddnh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class FileManager {
    private static final String CUSTOMERS_FILE = "test/customers.txt";
    private static final String HOTELS_FILE = "test/hotels.txt";
    private static final String ROOMS_FILE = "test/rooms.txt";
    private static final String RESERVATIONS_FILE = "test/reservations.txt";
    
    
    
    public static void saveData(CustomerManager c, HotelManager h, ReservationManager r) {
        saveCustomers(c.getAllCustomers());
        saveHotels(h.getAllHotels());
        saveRooms(h.getAllRooms());
        saveReservations(r.getAllReservations());
    }
    
    public static void loadData(CustomerManager c, HotelManager h, ReservationManager r) {
        List<Customer> customers = loadCustomers();
        customers.forEach(c::addCustomer);
        
        List<Hotel> hotels = loadHotels();
        hotels.forEach(h::addHotel);
        
        List<Room> rooms = loadRooms();
        for (Room room : rooms) {
            h.addRoomToHotel(room.getHotelId(), room);
        }
        
        List<Reservation> reservations = loadReservations();
        reservations.forEach(room -> {
            room.calculateTotalAmount(h.getAllRooms());
            r.addReservation(room);
        });
    }
    
    //Save groups:
    
    private static void saveCustomers(List<Customer> customers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customers) {
                pw.println(c.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving custommers: " + e.getMessage());
        }
    }
    
    private static void saveHotels(List<Hotel> hotels) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(HOTELS_FILE))) {
            for (Hotel h : hotels) {
                pw.println(h.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving hotels: " + e.getMessage());
        }
    }

    private static void saveRooms(List<Room> rooms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            for (Room r : rooms) {
                pw.println(r.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }

    private static void saveReservations(List<Reservation> reservations) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RESERVATIONS_FILE))) {
            for (Reservation r : reservations) {
                pw.println(r.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }
    
    //Load groups:
    private static List<Customer> loadCustomers() {
        List<Customer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4){
                    list.add(new Customer(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
        return list;
    }
    
    private static List<Hotel> loadHotels() {
        List<Hotel> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(HOTELS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    list.add(new Hotel(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading hotels: " + e.getMessage());
        }
        return list;
    }

    private static List<Room> loadRooms() {
        List<Room> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ROOMS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new Room(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }
        return list;
    }
    
    private static List<Reservation> loadReservations() {
        List<Reservation> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    Reservation r = new Reservation(parts[0], parts[1], LocalDate.parse(parts[2]),
                    LocalDate.parse(parts[3]), new ArrayList<String>());
                
                    r.setStatus(parts[4]);
                    String[] roomIDs = parts[5].split(";");
                    for (String room : roomIDs) {
                        if(!room.isEmpty()) r.addRoomId(room);
                    }
                    r.setTotalAmount(Double.parseDouble(parts[6]));
                    list.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
        return list;
    }
}
