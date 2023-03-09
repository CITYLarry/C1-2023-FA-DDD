package co.com.sofka.cityhotel.booking.client;

import co.com.sofka.cityhotel.booking.business.client.EditGuestEmailUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.commands.EditGuestEmailCommand;
import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.EditedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.RemovedGuest;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.AddressId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EditGuestEmailUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private EditGuestEmailUseCase editGuestEmailUseCase;

    @DisplayName("Edit guest email test")
    @Test
    void testEditGuestEmail() {
        CreatedClient createdClient = new CreatedClient(
                new ClientName("testClientName"),
                new ClientEmail("testClientEmail"),
                new ClientIdentification("testClientIdentification"),
                Address.from(
                        AddressId.of("testAddressId"),
                        new AddressValue("testAddress")
                ),
                CreditCard.from(
                        CreditCardId.of("testCreditCardId"),
                        new CreditCardNumber("testCreditCardNumber"),
                        new CreditCardExpDate(LocalDate.parse("2026-01-01")),
                        new CreditCardCcv(111)
                )
        );
        createdClient.setAggregateRootId("testClientId");

        AddedGuest addedGuest = new AddedGuest(
                GuestId.of("testGuestId"),
                new GuestName("testGuestName"),
                new GuestEmail("testGuestEmail"),
                ClientId.of("testClientId")
        );

        EditGuestEmailCommand editGuestEmailCommand = new EditGuestEmailCommand(
                "testGuestId",
                "newGuestEmail",
                "testClientId"
        );

        Mockito.when(eventsRepository.findAggregateRootId(editGuestEmailCommand.getClientId()))
                .thenAnswer(invocationOnMock -> {
                    List<DomainEvent> clientEvents = new ArrayList<>();
                    clientEvents.add(createdClient);
                    clientEvents.add(addedGuest);
                    return clientEvents;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(EditedGuest.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = editGuestEmailUseCase.apply(editGuestEmailCommand);

        Assertions.assertEquals(1, result.size());

        EditedGuest guest = (EditedGuest) result.get(0);

        Assertions.assertEquals("newGuestEmail", guest.getGuestEmail().value());
    }
}


