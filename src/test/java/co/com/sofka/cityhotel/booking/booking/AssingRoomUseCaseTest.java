package co.com.sofka.cityhotel.booking.booking;

import co.com.sofka.cityhotel.booking.business.booking.AssignRoomUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.commands.AssignRoomCommand;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AssingRoomUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private AssignRoomUseCase assignRoomUseCase;

    @DisplayName("Assign room test")
    @Test
    void testAssignRoom() {

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

        AssignRoomCommand assignRoomCommand = new AssignRoomCommand(
                "testRoomId",
                "testRoomNumber",
                "testBookingId"
        );

        Mockito.when(eventsRepository.findAggregateRootId(assignRoomCommand.getBookingId()))
                .thenAnswer(invocationOnMock -> {
                    List<DomainEvent> bookingEvents = new ArrayList<>();
                    bookingEvents.add(createdBooking);
                    return bookingEvents;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(AssignedRoom.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = assignRoomUseCase.apply(assignRoomCommand);

        Assertions.assertEquals(1, result.size());

        AssignedRoom guest = (AssignedRoom) result.get(0);

        Assertions.assertEquals("testBookingId", guest.aggregateRootId());
        Assertions.assertEquals("testRoomNumber", guest.getRoomNumber().value());
    }
}
