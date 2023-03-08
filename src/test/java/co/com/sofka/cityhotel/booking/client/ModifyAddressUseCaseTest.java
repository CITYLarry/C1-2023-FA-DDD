package co.com.sofka.cityhotel.booking.client;

import co.com.sofka.cityhotel.booking.business.client.CreateClientUseCase;
import co.com.sofka.cityhotel.booking.business.client.ModifyAddressUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.commands.CreateClientCommand;
import co.com.sofka.cityhotel.booking.domain.client.commands.ModifyAddressCommand;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.ModifiedAddress;
import co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
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
public class ModifyAddressUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateClientUseCase createClientUseCase;
    private ModifyAddressUseCase modifyAddressUseCase;

    @BeforeEach
    void setup() {
        createClientUseCase = new CreateClientUseCase(eventsRepository);
        modifyAddressUseCase = new ModifyAddressUseCase(eventsRepository);
    }

    @DisplayName("Modify the client address")
    @Test
    void testModifyAddress() {

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

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CreatedClient.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> createClientEvents = createClientUseCase.apply(createClientCommand);

        Mockito.when(eventsRepository.findAggregateRootId(createClientEvents.get(0).aggregateRootId()))
                .thenReturn(createClientEvents);

        ModifyAddressCommand modifyAddressCommand = new ModifyAddressCommand(
                "modifiedAddress",
                "testClientId"
        );

        ModifiedAddress modifiedAddress = new ModifiedAddress(
                new AddressValue("modifiedAddress"),
                ClientId.of("testClientId")
        );

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ModifiedAddress.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = modifyAddressUseCase.apply(modifyAddressCommand);

        Assertions.assertEquals(1, result.size());

        CreatedClient client = (CreatedClient) createClientEvents.get(0);

        Assertions.assertEquals(client.aggregateRootId(),modifiedAddress.getClientId().value());
        Assertions.assertEquals(client.getAddress().addressValue(),modifiedAddress.getAddressValue().value());
    }
}
