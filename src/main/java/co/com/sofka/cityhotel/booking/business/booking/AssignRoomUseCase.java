package co.com.sofka.cityhotel.booking.business.booking;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.Booking;
import co.com.sofka.cityhotel.booking.domain.booking.commands.AssignRoomCommand;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class AssignRoomUseCase implements CommandUseCase<AssignRoomCommand> {

    private final EventsRepository eventsRepository;

    public AssignRoomUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AssignRoomCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getBookingId());
        Booking booking = Booking.from(BookingId.of(command.bookingId), result);
        booking.assignRoom(
                RoomId.of(command.getRoomId()),
                new RoomNumber(command.getRoomNumber())
        );
        return booking.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
