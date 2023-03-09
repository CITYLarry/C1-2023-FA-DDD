package co.com.sofka.cityhotel.booking.business.booking;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.Booking;
import co.com.sofka.cityhotel.booking.domain.booking.commands.CheckOutRoomCommand;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class CheckOutRoomUseCase implements CommandUseCase<CheckOutRoomCommand> {

    private final EventsRepository eventsRepository;

    public CheckOutRoomUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(CheckOutRoomCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getBookingId());
        Booking booking = Booking.from(BookingId.of(command.getBookingId()), result);
        booking.checkOutRoom(RoomId.of(command.getRoomId()));
        return booking.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
