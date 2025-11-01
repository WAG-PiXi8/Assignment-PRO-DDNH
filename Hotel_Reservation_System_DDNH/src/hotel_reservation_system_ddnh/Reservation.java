package hotel_reservation_system_ddnh;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private String reservationId;
    private String customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<String> roomIds = new ArrayList<>();
    private double totalAmount;
    private String status = "Confirmed";

    public Reservation(String reservationId, String customerId, LocalDate checkInDate, LocalDate checkOutDate, List<String> roomIds) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomIds = roomIds;
    }

    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
    public List<String> getRoomIds() { return roomIds; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void addRoomId(String roomId) { 
        roomIds.add(roomId); 
    }
    public void removeRoomId(String roomId) { 
        roomIds.remove(roomId); 
    }

    public long calculateTotalNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public void calculateTotalAmount(List<Room> allRooms) {
        long nights = calculateTotalNights();
        double sum = 0;
        for (String rid : roomIds) {
            for (Room r : allRooms) {
                if (r.getRoomId().equals(rid)) {
                    sum += r.getPricePerNight() * nights;
                    break;
                }
            }
        }
        this.totalAmount = sum;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomIds=" + roomIds +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }

    public String toFileString() {
        return reservationId + "|" + customerId + "|" + checkInDate + "|" + checkOutDate + "|" +
                status + "|" + String.join(";", roomIds) + "|" + totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId);
    }
}