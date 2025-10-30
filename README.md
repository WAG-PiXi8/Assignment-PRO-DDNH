# Assignment-PRO-DDNH

Member A: Chịu trách nhiệm Module Quản lý Khách hàng (Customer Module)  
Nhiệm vụ cụ thể:
Code lớp Customer.java: Định nghĩa entity (fields, getters/setters, toString, toFileString, equals).
Code lớp CustomerManager.java: Quản lý list customers (add, update, delete, findById, getAll, generateId).
Test: Viết method test đơn giản trong Main (tạm thời) để kiểm tra add/update/delete.
Thời gian ước tính: 1-2 ngày.
Dependency: Không có. Output: List<Customer> để các module khác dùng.

Member B: Chịu trách nhiệm Module Quản lý Khách sạn và Phòng (Hotel & Room Module) 
Nhiệm vụ cụ thể:
Code lớp Hotel.java: Entity (fields, getters/setters, add/remove Room, toString, toFileString, equals).
Code lớp Room.java: Entity (fields, getters/setters, toString, toFileString, equals).
Code lớp HotelManager.java: Quản lý list hotels và allRooms (addHotel, updateHotel, deleteHotel, findHotelById, addRoomToHotel, updateRoom, deleteRoom, findRoomById, getAllHotels, getAllRooms, generateHotelId, generateRoomId).
Test: Test add hotel, add room vào hotel, delete room (kiểm tra allRooms cập nhật đúng).
Thời gian ước tính: 2-3 ngày.
Dependency: Không có trực tiếp, nhưng cung cấp Room cho Reservation.

Member C: Chịu trách nhiệm Module Quản lý Đặt phòng (Reservation Module) 
Nhiệm vụ cụ thể:
Code lớp Reservation.java: Entity (fields, getters/setters, add/remove RoomId, calculateTotalNights, calculateTotalAmount, toString, toFileString, equals).
Code lớp ReservationManager.java: Quản lý list reservations (addReservation, createReservation, getAll, findById, isRoomAvailable, addRoomToReservation, removeRoomFromReservation, cancelReservation, generateId, sortCustomersByTotalSpent, sortHotelsByBookingCount, findMostPopularRoomType, findCustomerWithMostReservations).
Test: Test create reservation, check availability, calculate total, reports (sử dụng mock data từ Customer và Room).
Thời gian ước tính: 3-4 ngày.
Dependency: Sử dụng CustomerManager (từ Member A) để check customer, HotelManager (từ Member B) để get rooms và calculate.

Member D: Chịu trách nhiệm Module Tích hợp và Lưu trữ (Integration & File Module) 
Nhiệm vụ cụ thể:
Code lớp FileManager.java: Lưu/tải data (save/load Customers, Hotels, Rooms, Reservations; sử dụng toFileString từ các entity).
Code lớp Main.java: Menu chính (manageCustomers, manageHotels, manageRooms, manageReservations, viewReports), xử lý input (getIntInput, getDoubleInput, getDateInput), gọi các manager.
Test: Test full flow (load data, run menu, save data).
Thời gian ước tính: 2-3 ngày (sau khi các module khác hoàn thành).
Dependency: Phụ thuộc tất cả (CustomerManager, HotelManager, ReservationManager).
