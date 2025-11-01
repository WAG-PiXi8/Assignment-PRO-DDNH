package hotel_reservation_system_ddnh;

import java.util.Objects;

public class Room {
    private String roomId;
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private String hotelId;

    public Room(String roomId, String roomNumber, String roomType, double pricePerNight, String hotelId) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.hotelId = hotelId;
    }

    public String getRoomId() { 
        return roomId; 
    }
    
    public void setRoomId(String roomId) { 
        this.roomId = roomId; 
    }
    
    public String getRoomNumber() { 
        return roomNumber; 
    }
    
    public void setRoomNumber(String roomNumber) { 
        this.roomNumber = roomNumber; 
    }
    
    public String getRoomType() { 
        return roomType; 
    }
    
    public void setRoomType(String roomType) { 
        this.roomType = roomType; 
    }
    
    public double getPricePerNight() { 
        return pricePerNight; 
    }
    
    public void setPricePerNight(double pricePerNight) { 
        this.pricePerNight = pricePerNight; 
    }
    
    public String getHotelId() { 
        return hotelId; 
    }
    
    public void setHotelId(String hotelId) { 
        this.hotelId = hotelId; 
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", hotelId='" + hotelId + '\'' +
                '}';
    }

    public String toFileString() {
        return roomId + "|" + roomNumber + "|" + roomType + "|" + pricePerNight + "|" + hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
