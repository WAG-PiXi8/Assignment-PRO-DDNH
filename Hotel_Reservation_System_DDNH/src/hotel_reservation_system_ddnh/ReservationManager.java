package hotel_reservation_system_ddnh;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationManager {
    private List<Reservation> reservations = new ArrayList<>();
    private HotelManager hotelManager;
    private CustomerManager customerManager;

    public ReservationManager(HotelManager hotelManager, CustomerManager customerManager) {
        this.hotelManager = hotelManager;
        this.customerManager = customerManager;
    }
    
    public void addReservation(Reservation newReservation){
        reservations.add(newReservation);
    }

    public void createReservation(Reservation reservation) {
        if (!reservation.getRoomIds().isEmpty()) {
            reservation.calculateTotalAmount(hotelManager.getAllRooms());
            reservations.add(reservation);
        } else {
            System.out.println("No rooms added. Reservation not created.");
        }
    }
    
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
    
    public Reservation findReservationById(String reservationId) {
        for (Reservation r : reservations) {
            if (r.getReservationId().equals(reservationId)) return r;
        }
        return null;
    }
    
    public boolean isRoomAvailable(String roomId, LocalDate newCheckIn, LocalDate newCheckOut) {
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed") && r.getRoomIds().contains(roomId)) {
                LocalDate existingCheckIn = r.getCheckInDate();
                LocalDate existingCheckOut = r.getCheckOutDate();
                if (newCheckIn.isBefore(existingCheckOut) && newCheckOut.isAfter(existingCheckIn)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addRoomToReservation(String reservationId, String roomId) {
        Reservation res = findReservationById(reservationId);
        if (isRoomAvailable(roomId, res.getCheckInDate(), res.getCheckOutDate())) {
            res.addRoomId(roomId);
            System.out.println("Room added successfully");
            res.calculateTotalAmount(hotelManager.getAllRooms());
        } else {
            System.out.println("Room not available.");
        }
    }

    public void removeRoomFromReservation(String reservationId, String roomId) {
        Reservation res = findReservationById(reservationId);
        if (res.getRoomIds().contains(roomId)) {
            res.removeRoomId(roomId);
            System.out.println("Room removed successfully.");
            res.calculateTotalAmount(hotelManager.getAllRooms());
        }else{
            System.out.println("Room not found.");
        }
    }

    public void cancelReservation(String reservationId) {
        Reservation res = findReservationById(reservationId);
        if (res != null) {
            res.setStatus("Cancelled");
            System.out.println("Reservation cancelled successfully.");
        }
    }

    public String generateReservationId() {
        int maxId = 0;
        for (Reservation r : reservations) {
             try {
                int idNum = Integer.parseInt(r.getReservationId().substring(3));
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (Exception e) {}
        }
        return "RES" + String.format("%03d", maxId + 1);
    }

    public List<Customer> sortCustomersByTotalSpent(int order) {
        Map<String, Double> spending = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                String customerId = r.getCustomerId();
                double currentSpending = spending.getOrDefault(customerId, 0.0);
                spending.put(customerId, currentSpending + r.getTotalAmount());
            }
        }

        List<Customer> allCustomers = customerManager.getAllCustomers();

        Collections.sort(allCustomers, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                double total1 = spending.getOrDefault(c1.getCustomerId(), 0.0);
                double total2 = spending.getOrDefault(c2.getCustomerId(), 0.0);
                   
                if(order == 1) return Double.compare(total1, total2);
                else return Double.compare(total2, total1);
            }
        });

        return allCustomers;
    }

    public List<Hotel> sortHotelsByBookingCount(int order) {
        Map<String, Integer> bookingCount = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                for (String roomId : r.getRoomIds()) {
                    Room room = hotelManager.findRoomById(roomId);
                    if (room != null) {
                        String hotelId = room.getHotelId();
                        bookingCount.put(hotelId, bookingCount.getOrDefault(hotelId, 0) + 1);
                    }
                }
            }
        }
        
        List<Hotel> allHotels = hotelManager.getAllHotels();
        
        Collections.sort(allHotels, new Comparator<Hotel>(){
            @Override
            public int compare(Hotel h1, Hotel h2){
                int total1 = bookingCount.getOrDefault(h1.getHotelId(), 0);
                int total2 = bookingCount.getOrDefault(h2.getHotelId(), 0);
                
                if (order == 1) return total1 - total2;
                else return total2 - total1;
            }
        });
        return allHotels;
    }

    public String findMostPopularRoomType() {
        Map<String, Integer> typeCount = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                for (String roomId : r.getRoomIds()) {
                    Room room = hotelManager.findRoomById(roomId);
                    if (room != null) {
                        String type = room.getRoomType();
                        typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
                    }
                }
            }
        }
        String mostPopularType = "None";
        int maxCount = 0;   
        for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopularType = entry.getKey();
            }
        }

        return mostPopularType;
    }

    public Customer findCustomerWithMostReservations() {
        Map<String, Integer> resCount = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                resCount.put(r.getCustomerId(), resCount.getOrDefault(r.getCustomerId(), 0) + 1);
            }
        }
        
        String topCustomerId = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : resCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                topCustomerId = entry.getKey();
            }
        }

        if (topCustomerId != null) {
            return customerManager.findCustomerById(topCustomerId);
        } else {
            return null;
        }
    }
}
