package hotel_reservation_system_ddnh;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotels = new ArrayList<>();
    private List<Room> allRooms = new ArrayList<>();

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void updateHotel(String hotelId, String newName, String newAddress) {
        Hotel h = findHotelById(hotelId);
        if (h != null) {
            if (!newName.isEmpty()) h.setHotelName(newName);
            if (!newAddress.isEmpty()) h.setAddress(newAddress);
            System.out.println("Updated Hotel ID: " + hotelId);
        }
    }

    public void deleteHotel(String hotelId) {
        Hotel h = findHotelById(hotelId);
        if (h != null) {
            allRooms.removeIf(r -> r.getHotelId().equals(hotelId));
            hotels.remove(h);
            System.out.println("Deleted Hotel ID: " + hotelId + " and its associated rooms.");
        }
        else {
            System.out.println("Error: Hotel ID not found: " + hotelId);
        }
    }

    public Hotel findHotelById(String hotelId) {
        for (Hotel h : hotels) {
            if (h.getHotelId().equals(hotelId)) return h;
        }
        return null;
    }

    public void addRoomToHotel(String hotelId, Room room) {
        Hotel h = findHotelById(hotelId);
        if (h != null) {
            h.addRoom(room);
            allRooms.add(room);
        }
    }

    public void updateRoom(String roomId, String newNumber, String newType, double newPrice) {
        Room r = findRoomById(roomId);
        if (r != null) {
            if (!newNumber.isEmpty()) r.setRoomNumber(newNumber);
            if (!newType .isEmpty()) r.setRoomType(newType);
            if (newPrice > 0) r.setPricePerNight(newPrice);
            System.out.println("Updated Room ID: " + roomId);
        }
    }

    public void deleteRoom(String roomId) {
        Room r = findRoomById(roomId);
        if (r != null) {
            Hotel h = findHotelById(r.getHotelId());
            if (h != null) h.removeRoom(r);
            allRooms.remove(r);
            System.out.println("Deleted Room ID: " + roomId);
        }
        else {
            System.out.println("Error: Room ID not found: " + roomId);
        }
    }

    public Room findRoomById(String roomId) {
        for (Room r : allRooms) {
            if (r.getRoomId().equals(roomId)) return r;
        }
        return null;
    }

    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotels);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(allRooms);
    }

    public String generateHotelId() {
        int maxId = 0;
        for (Hotel h : hotels) {
            try {
                int idNum = Integer.parseInt(h.getHotelId().substring(3));
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (Exception e) {}
        }
        return "HTL" + String.format("%03d", maxId + 1);
    }

    public String generateRoomId() {
        int maxId = 0;
        for (Room r : allRooms) {
             try {
                int idNum = Integer.parseInt(r.getRoomId().substring(2));
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (Exception e) {}
        }
        return "RM" + String.format("%03d", maxId + 1);
    }
}

