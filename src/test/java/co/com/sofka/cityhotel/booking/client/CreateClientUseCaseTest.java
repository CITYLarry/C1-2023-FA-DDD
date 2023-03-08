package co.com.sofka.cityhotel.booking.client;

import co.com.sofka.cityhotel.booking.business.client.CreateClientUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.commands.CreateClientCommand;
import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CreateClientUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateClientUseCase createClientUseCase;

    @BeforeEach
    void setup() {
        createClientUseCase = new CreateClientUseCase(eventsRepository);
    }

    @DisplayName("Create client")
    @Test
    void testCreateClient() {

        CreateClientCommand createClientCommand = new CreateClientCommand(
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

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CreatedClient.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> createClientEvents = createClientUseCase.apply(createClientCommand);

        Assertions.assertEquals(1, createClientEvents.size());

        DomainEvent client = createClientEvents.get(0);

        Assertions.assertTrue(client instanceof CreatedClient);

        Assertions.assertEquals(createdClient.aggregateRootId(), client.aggregateRootId());
        Assertions.assertEquals(createdClient.getClientName().value(), ((CreatedClient) client).getClientName().value());
        Assertions.assertEquals(createdClient.getCreditCard().creditCardNumber(), ((CreatedClient) client).getCreditCard().creditCardNumber());
    }
}
