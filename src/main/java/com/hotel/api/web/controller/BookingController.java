package com.hotel.api.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.api.web.model.ApiResponse;
import com.hotel.api.web.model.Booking;
import com.hotel.api.web.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/book")
	public ResponseEntity<ApiResponse<Object>> bookRoom(@RequestBody Booking booking) {
		ApiResponse<Object> response = new ApiResponse<>();
		bookingService.bookRoom(booking);
		response.setSuccess(true);
		response.setMessage("Room added");
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getAllBookings();
		return ResponseEntity.ok(bookings);

	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Booking> getBookingById(@PathVariable String bookingId) {
		Booking booking = bookingService.getBookingById(bookingId);
		return ResponseEntity.ok(booking);
	}

	@PutMapping("/{bookingId}/status")
	public ResponseEntity<ApiResponse<Object>> updateBookingStatus(@PathVariable String bookingId,
			@RequestParam String status) {
		ApiResponse<Object> response = new ApiResponse<>();
		bookingService.updateBookingStatus(bookingId, status);
		response.setSuccess(true);
		response.setMessage("Booking status updated");
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<ApiResponse<Object>> deleteBooking(@PathVariable String bookingId) {
		ApiResponse<Object> response = new ApiResponse<>();
		bookingService.deleteBooking(bookingId);
		response.setSuccess(true);
		response.setMessage("Booking removed");
		return ResponseEntity.ok().body(response);
	}

}
