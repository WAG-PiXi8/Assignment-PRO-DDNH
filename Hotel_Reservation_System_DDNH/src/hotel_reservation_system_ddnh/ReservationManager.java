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
        if (customerManager.findCustomerById(reservation.getCustomerId()) == null) {
            System.out.println("Customer not found.");
            return;
        }
        
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
        if (res != null && res.getStatus().equals("Confirmed")) {
            if (isRoomAvailable(roomId, res.getCheckInDate(), res.getCheckOutDate())) {
                res.addRoomId(roomId);
                res.calculateTotalAmount(hotelManager.getAllRooms());
            } else {
                System.out.println("Room not available.");
            }
        }
    }

    public void removeRoomFromReservation(String reservationId, String roomId) {
        Reservation res = findReservationById(reservationId);
        if (res != null && res.getStatus().equals("Confirmed")) {
            res.removeRoomId(roomId);
            res.calculateTotalAmount(hotelManager.getAllRooms());
        }
    }

    public void cancelReservation(String reservationId) {
        Reservation res = findReservationById(reservationId);
        if (res != null) {
            res.setStatus("Cancelled");
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

    public List<Customer> sortCustomersByTotalSpent() {
        Map<String, Double> spending = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                spending.put(r.getCustomerId(), spending.getOrDefault(r.getCustomerId(), 0.0) + r.getTotalAmount());
            }
        }
        return customerManager.getAllCustomers().stream()
                .sorted(Comparator.comparingDouble(c -> -spending.getOrDefault(c.getCustomerId(), 0.0))) // Descending
                .collect(Collectors.toList());
    }

    public List<Hotel> sortHotelsByBookingCount() {
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
        return hotelManager.getAllHotels().stream()
                .sorted(Comparator.comparingInt(h -> -bookingCount.getOrDefault(h.getHotelId(), 0))) // Descending
                .collect(Collectors.toList());
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
        return typeCount.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse("None");
    }

    public Customer findCustomerWithMostReservations() {
        Map<String, Integer> resCount = new HashMap<>();
        for (Reservation r : reservations) {
            if (r.getStatus().equals("Confirmed")) {
                resCount.put(r.getCustomerId(), resCount.getOrDefault(r.getCustomerId(), 0) + 1);
            }
        }
        String topId = resCount.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);
        return customerManager.findCustomerById(topId);
    }
}
