package co.com.sofka.cityhotel.booking.client;

import co.com.sofka.cityhotel.booking.business.client.AddGuestUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.commands.AddGuestCommand;
import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.AddressId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class AddGuestUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private AddGuestUseCase addGuestUseCase;


    @DisplayName("Add guest test")
    @Test
    void testAddGuest() {

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

        AddGuestCommand addGuestCommand = new AddGuestCommand(
                "testGuestId",
                "testGuestName",
                "testGuestEmail",
                "testClientId"
        );

        Mockito.when(eventsRepository.findAggregateRootId(addGuestCommand.getClientId()))
                .thenAnswer(invocationOnMock -> {
                    List<DomainEvent> clientEvents = new ArrayList<>();
                    clientEvents.add(createdClient);
                    return clientEvents;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(AddedGuest.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));


        List<DomainEvent> result = addGuestUseCase.apply(addGuestCommand);

        Assertions.assertEquals(1, result.size());

        AddedGuest guest = (AddedGuest) result.get(0);

        Assertions.assertEquals("testGuestName", guest.getGuestName().value());
        Assertions.assertEquals("testGuestEmail", guest.getGuestEmail().value());
        Assertions.assertEquals("testClientId", guest.getClientId().value());

        /*CreateClientCommand createClientCommand = new CreateClientCommand(
                "testClientId",
                "testClientName",
                "testClientEmail",
                "testClientIdentification",
                "testAddressId",
                "testAddress",
                "testCreditCardId",
                "testCreditCardNumber",
                LocalDate.parse("2026-01-01"),
                111
        );

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CreatedClient.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));*/

        /*List<DomainEvent> createClientEvents = createClientUseCase.apply(createClientCommand);*/

        /*Mockito.when(eventsRepository.findAggregateRootId(createClientEvents.get(0).aggregateRootId()))
                .thenReturn(createClientEvents);

        AddGuestCommand addGuestCommand = new AddGuestCommand(
                "testGuestId",
                "testGuestName",
                "testGuestEmail",
                "testClientId"
        );

        AddedGuest addedGuest = new AddedGuest(
                GuestId.of("testGuestId"),
                new GuestName("testGuestName"),
                new GuestEmail("testGuestEmail"),
                ClientId.of("testClientId")
        );

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(AddedGuest.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));


        List<DomainEvent> result = addGuestUseCase.apply(addGuestCommand);

        System.out.println(result);*/

        /*Mockito.when(eventsRepository.findAggregateRootId(createClientEvents.get(0).aggregateRootId()))
                .thenReturn(createClientEvents);

        CreatedClient client = (CreatedClient) createClientEvents.get(0);

        Assertions.assertEquals(1, client.getGuestSet().size());

        Assertions.assertEquals(client.aggregateRootId(),addedGuest.getClientId().value());
        Assertions.assertEquals(1, client.getGuestSet().size());*/
    }
}
