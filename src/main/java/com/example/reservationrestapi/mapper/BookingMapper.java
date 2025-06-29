package com.example.reservationrestapi.mapper;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.entity.Booking;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "id", target = "bookingId")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "flight.flightId", target = "flightId")
    BookingDto toDTO(Booking booking);

    @Mapping(target = "id", source = "bookingId")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "flight", ignore = true)
    Booking toEntity(BookingDto dto, @Context User user, @Context Flight flight);

    @AfterMapping
    default void setUserAndFlight(@MappingTarget Booking booking,
                                  BookingDto dto,
                                  @Context User user,
                                  @Context Flight flight) {
        booking.setUser(user);
        booking.setFlight(flight);
    }
}
