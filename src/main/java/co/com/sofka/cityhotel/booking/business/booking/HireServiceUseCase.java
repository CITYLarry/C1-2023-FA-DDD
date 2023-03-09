package co.com.sofka.cityhotel.booking.business.booking;


import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.Booking;
import co.com.sofka.cityhotel.booking.domain.booking.commands.HireServiceCommand;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class HireServiceUseCase implements CommandUseCase<HireServiceCommand> {

    private final EventsRepository eventsRepository;

    public HireServiceUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @Override
    public List<DomainEvent> apply(HireServiceCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getBookingId());
        Booking booking = Booking.from(BookingId.of(command.getBookingId()), result);
        booking.hireService(new ServiceType(command.getService()));
        return booking.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
