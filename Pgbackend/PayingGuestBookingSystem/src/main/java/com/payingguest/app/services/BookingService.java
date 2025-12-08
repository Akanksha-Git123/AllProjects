package com.payingguest.app.services;

import com.payingguest.app.dtos.BookingResponseDTO;
import java.util.List;

public interface BookingService {
    List<BookingResponseDTO> getBookingsForTenant(Long tenantId);
    BookingResponseDTO cancelBookingForTenant(Long tenantId, Integer bookingId) throws RuntimeException;
}
