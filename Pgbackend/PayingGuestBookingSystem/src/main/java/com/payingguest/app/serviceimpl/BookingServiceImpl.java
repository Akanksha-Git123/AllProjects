package com.payingguest.app.serviceimpl;

import com.payingguest.app.dtos.BookingResponseDTO;
import com.payingguest.app.entities.Booking;
import com.payingguest.app.entities.PgListing;
import com.payingguest.app.repositories.BookingRepository;
import com.payingguest.app.services.BookingService;
import com.payingguest.app.enums.BookingStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<BookingResponseDTO> getBookingsForTenant(Long tenantId) {
        List<Booking> bookings = bookingRepository.findByTenant_UserId(tenantId);
        return bookings.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookingResponseDTO cancelBookingForTenant(Long tenantId, Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getTenant().getUserId().equals(tenantId)) {
            throw new RuntimeException("Unauthorized: booking does not belong to tenant");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return toDto(booking);
    }

    private BookingResponseDTO toDto(Booking b) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(b.getBookingId());

        PgListing pg = b.getPg();
        if (pg != null) {
            dto.setPgId(pg.getPgId());
            dto.setPgName(pg.getPgName()); // ✅ instead of getName()

            dto.setAddress(pg.getAddress());
            dto.setCity(pg.getCity());
            if (pg.getOwner() != null) {
                dto.setOwnerName(pg.getOwner().getFullName());
                dto.setOwnerPhone(pg.getOwner().getPhoneNumber());
            }
        }

        dto.setMoveInDate(b.getMoveInDate());
        dto.setMoveOutDate(b.getMoveOutDate());
        dto.setStatus(b.getStatus());

        if (b.getRoomType() != null) {
            // Use the actual field from your RoomType entity
        	dto.setRoomType(b.getRoomType().getRoomCategory().name());

            dto.setTotalRent(b.getRoomType().getPrice());       // BigDecimal directly
        }

        dto.setCreatedAt(b.getCreatedAt());
        return dto;
    }
}
