package hotel_reservation_system_ddnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private String hotelId;
    private String hotelName;
    private String address;
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String hotelId, String hotelName, String address) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.address = address;
    }

    public String getHotelId() { 
        return hotelId; 
    }
    
    public void setHotelId(String hotelId) { 
        this.hotelId = hotelId; 
    }
    
    public String getHotelName() { 
        return hotelName; 
    }
    
    public void setHotelName(String hotelName) { 
        this.hotelName = hotelName; 
    }
    
    public String getAddress() { 
        return address; 
    }
    
    public void setAddress(String address) { 
        this.address = address; 
    }
    
    public List<Room> getRooms() { 
        return rooms; 
    }

    public void addRoom(Room room) { 
        rooms.add(room); 
    }
    
    public void removeRoom(Room room) { 
        rooms.remove(room); 
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", address='" + address + '\'' +
                ", rooms=" + rooms.size() +
                '}';
    }

    public String toFileString() {
        return hotelId + "|" + hotelName + "|" + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(hotelId, hotel.hotelId);
    }
}

