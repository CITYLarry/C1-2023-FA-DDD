package co.com.sofka.cityhotel.booking.booking;

import co.com.sofka.cityhotel.booking.business.booking.CheckOutRoomUseCase;
import co.com.sofka.cityhotel.booking.business.booking.FreeUpRoomUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.booking.commands.CheckOutRoomCommand;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Payment;
import co.com.sofka.cityhotel.booking.domain.booking.entities.Services;
import co.com.sofka.cityhotel.booking.domain.booking.events.AssignedRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CheckOutRoom;
import co.com.sofka.cityhotel.booking.domain.booking.events.CreatedBooking;
import co.com.sofka.cityhotel.booking.domain.booking.events.FreeUpRoom;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.BookingId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.PaymentId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.RoomId;
import co.com.sofka.cityhotel.booking.domain.booking.values.identities.ServiceId;
import co.com.sofka.cityhotel.booking.domain.booking.values.payment.PaymentAmount;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomAvailable;
import co.com.sofka.cityhotel.booking.domain.booking.values.room.RoomNumber;
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
public class FreeUpRoomUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private CheckOutRoomUseCase checkOutRoomUseCase;

    @InjectMocks
    private FreeUpRoomUseCase freeUpRoomUseCase;

    @DisplayName("Free up room test")
    @Test
    void testFreeUpRoom() {

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

        AssignedRoom assignedRoom = new AssignedRoom(
                RoomId.of("testRoomId"),
                new RoomNumber("testRoomId"),
                BookingId.of("testBookingId")
        );

        CheckOutRoomCommand checkOutRoomCommand = new CheckOutRoomCommand(
                "testRoomId",
                "testBookingId"
        );

        Mockito.when(eventsRepository.findAggregateRootId(createdBooking.aggregateRootId()))
                .thenAnswer(invocationOnMock -> {
                    List<DomainEvent> bookingEvents = new ArrayList<>();
                    bookingEvents.add(createdBooking);
                    bookingEvents.add(assignedRoom);
                    return bookingEvents;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CheckOutRoom.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = checkOutRoomUseCase.apply(checkOutRoomCommand);

        CheckOutRoom checkOutRoom = (CheckOutRoom) result.get(0);

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(FreeUpRoom.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));


        List<DomainEvent> result2 = freeUpRoomUseCase.apply(checkOutRoom);

        Assertions.assertEquals(1, result2.size());

        FreeUpRoom freeUpRoom = (FreeUpRoom) result2.get(0);

        Assertions.assertEquals(false, freeUpRoom.getRoomAvailable().value());
        Assertions.assertEquals(checkOutRoom.getRoomId().value(), freeUpRoom.getRoomId().value());
    }
}
