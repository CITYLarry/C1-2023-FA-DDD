package co.com.sofka.cityhotel.booking.business.booking;

import co.com.sofka.cityhotel.booking.business.commons.EventUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.Booking;
import co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class FreeUpRoomUseCase implements EventUseCase<CheckOutRoom> {

    private final EventsRepository eventsRepository;

    public FreeUpRoomUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CheckOutRoom event) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(event.getBookingId().value());
        Booking booking = Booking.from(BookingId.of(event.getBookingId().value()), result);
        booking.freeUpRoom(event.getRoomId(), new RoomAvailable(true));
        return booking.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
