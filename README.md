# Assignment-PRO-DDNH

<b>Nguyễn Anh Đức</b>: Chịu trách nhiệm Module Quản lý Khách hàng (Customer Module).  
Nhiệm vụ cụ thể:
<br>
Code lớp Customer.java: Định nghĩa entity (fields, getters/setters, toString, toFileString, equals).
<br>
Code lớp CustomerManager.java: Quản lý list customers (add, update, delete, findById, getAll, generateId).
<br>
Test: Viết method test đơn giản trong Main (tạm thời) để kiểm tra add/update/delete.
Thời gian ước tính: 1-2 ngày.
<br>
Dependency: Không có. Output: List<Customer> để các module khác dùng.
<br>
<br>
<b>Phạm Công Hùng</b>: Chịu trách nhiệm Module Quản lý Khách sạn và Phòng (Hotel & Room Module) 
Nhiệm vụ cụ thể:
<br>
Code lớp Hotel.java: Entity (fields, getters/setters, add/remove Room, toString, toFileString, equals).
<br>
Code lớp Room.java: Entity (fields, getters/setters, toString, toFileString, equals).
<br>
Code lớp HotelManager.java: Quản lý list hotels và allRooms (addHotel, updateHotel, deleteHotel, findHotelById, addRoomToHotel, updateRoom, deleteRoom, findRoomById, getAllHotels, getAllRooms, generateHotelId, generateRoomId).
<br>
Test: Test add hotel, add room vào hotel, delete room (kiểm tra allRooms cập nhật đúng).
<br>
Thời gian ước tính: 2-3 ngày.
<br>
Dependency: Không có trực tiếp, nhưng cung cấp Room cho Reservation.

<b>Nguyễn Huy Nhật</b>: Chịu trách nhiệm Module Quản lý Đặt phòng (Reservation Module) 
<br>
Nhiệm vụ cụ thể:
Code lớp Reservation.java: Entity (fields, getters/setters, add/remove RoomId, calculateTotalNights, calculateTotalAmount, toString, toFileString, equals).
<br>
Code lớp ReservationManager.java: Quản lý list reservations (addReservation, createReservation, getAll, findById, isRoomAvailable, addRoomToReservation, removeRoomFromReservation, cancelReservation, generateId, sortCustomersByTotalSpent, sortHotelsByBookingCount, findMostPopularRoomType, findCustomerWithMostReservations).
<br>
Test: Test create reservation, check availability, calculate total, reports (sử dụng mock data từ Customer và Room).
<br>
Thời gian ước tính: 3-4 ngày.
<br>
Dependency: Sử dụng CustomerManager (từ Member A) để check customer, HotelManager (từ Member B) để get rooms và calculate.
<br>
<br>

<b>Nguyễn Minh Đức</b>: Chịu trách nhiệm Module Tích hợp và Lưu trữ (Integration & File Module) 
<br>
Nhiệm vụ cụ thể:
<br>
Code lớp FileManager.java: Lưu/tải data (save/load Customers, Hotels, Rooms, Reservations; sử dụng toFileString từ các entity).
<br>
Code lớp Main.java: Menu chính (manageCustomers, manageHotels, manageRooms, manageReservations, viewReports), xử lý input (getIntInput, getDoubleInput, getDateInput), gọi các manager.
<br>
Test: Test full flow (load data, run menu, save data).
<br>
Thời gian ước tính: 2-3 ngày (sau khi các module khác hoàn thành).
<br>
Dependency: Phụ thuộc tất cả (CustomerManager, HotelManager, ReservationManager).
