package com.payingguest.app.controllers;

import com.payingguest.app.dtos.BookingResponseDTO;
import com.payingguest.app.services.BookingService;
import com.payingguest.app.security.AuthUser; // your auth principal
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getTenantBookings(@AuthenticationPrincipal AuthUser authUser) {
        Long tenantId = authUser.getUserId(); // ✅ Long, matches service
        List<BookingResponseDTO> bookings = bookingService.getBookingsForTenant(tenantId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@AuthenticationPrincipal AuthUser authUser,
                                                            @PathVariable Integer bookingId) {
        Long tenantId = authUser.getUserId(); // ✅ Long, matches service
        BookingResponseDTO dto = bookingService.cancelBookingForTenant(tenantId, bookingId);
        return ResponseEntity.ok(dto);
    }
}