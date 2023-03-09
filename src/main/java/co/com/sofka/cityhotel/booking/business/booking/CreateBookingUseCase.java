package co.com.sofka.cityhotel.booking.business.booking;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.Booking;
import co.com.sofka.cityhotel.booking.domain.booking.commands.CreateBookingCommand;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.PaymentId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.ServiceId;
import co.com.sofka.cityhotel.booking.domain.booking.values.payment.PaymentAmount;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class CreateBookingUseCase implements CommandUseCase<CreateBookingCommand> {

    private final EventsRepository eventsRepository;

    public CreateBookingUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateBookingCommand command) {
        Booking booking = new Booking(
                BookingId.of(command.getBookingId()),
                ClientId.of(command.getClientId()),
                Services.from(
                        ServiceId.of(command.getServiceId()),
                        new ServiceType(ServiceType.ServiceList.BASIC)),
                Payment.from(
                        PaymentId.of(command.getPaymentId()),
                        new PaymentAmount("basic amount")
                )
        );
        return  booking.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
