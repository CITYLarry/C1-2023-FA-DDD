package co.com.sofka.cityhotel.booking.booking;

import co.com.sofka.cityhotel.booking.business.booking.CreateBookingUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.commands.CreateBookingCommand;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.PaymentId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.ServiceId;
import co.com.sofka.cityhotel.booking.domain.booking.values.payment.PaymentAmount;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CreateBookingUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private CreateBookingUseCase createBookingUseCase;

    @DisplayName("Create booking test")
    @Test
    void testCreateBooking() {

        CreateBookingCommand createBookingCommand = new CreateBookingCommand(
                "testBookingId",
                "testClientId",
                "testServiceId",
                "testPaymentId"
        );

        CreatedBooking createdBooking = new CreatedBooking(
                ClientId.of("testClientId"),
                Services.from(
                        ServiceId.of("testServiceId"),
                        new ServiceType(ServiceType.ServiceList.BASIC)
                ),
                Payment.from(
                        PaymentId.of("testPaymentId"),
                        new PaymentAmount("basic amount")
                )
        );
        createdBooking.setAggregateRootId("testBookingId");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CreatedBooking.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = createBookingUseCase.apply(createBookingCommand);

        Assertions.assertEquals(1, result.size());

        CreatedBooking booking = (CreatedBooking) result.get(0);

        Assertions.assertEquals(createdBooking.aggregateRootId(), booking.aggregateRootId());
        Assertions.assertEquals(createdBooking.getServices().services().get(0).value(), booking.getServices().services().get(0).value());
        Assertions.assertEquals(createdBooking.getPayment().paymentAmount(), booking.getPayment().paymentAmount());
    }
}
