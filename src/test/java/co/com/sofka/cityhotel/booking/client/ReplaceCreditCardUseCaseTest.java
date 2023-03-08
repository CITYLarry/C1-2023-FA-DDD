package co.com.sofka.cityhotel.booking.client;

import co.com.sofka.cityhotel.booking.business.client.CreateClientUseCase;
import co.com.sofka.cityhotel.booking.business.client.ReplaceCreditCardUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.commands.CreateClientCommand;
import co.com.sofka.cityhotel.booking.domain.client.commands.ReplaceCreditCardCommand;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
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
public class ReplaceCreditCardUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;
    private CreateClientUseCase createClientUseCase;
    private ReplaceCreditCardUseCase replaceCreditCardUseCase;

    @BeforeEach
    void setup() {
        createClientUseCase = new CreateClientUseCase(eventsRepository);
        replaceCreditCardUseCase = new ReplaceCreditCardUseCase(eventsRepository);
    }

    @DisplayName("Replace the client credit card")
    @Test
    void testReplaceCreditCard() {

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

        ReplaceCreditCardCommand replaceCreditCardCommand = new ReplaceCreditCardCommand(
                "testCreditCardId",
                "testCreditCardNumber",
                LocalDate.parse("2026-01-01"),
                111,
                "testClientId"
        );

        ReplacedCreditCard replacedCreditCard = new ReplacedCreditCard(
                CreditCardId.of("testCreditCardId"),
                new CreditCardNumber("testCreditCardNumber"),
                new CreditCardExpDate(LocalDate.parse("2026-01-01")),
                new CreditCardCcv(111),
                ClientId.of("testClientId")
        );

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ReplacedCreditCard.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        List<DomainEvent> result = replaceCreditCardUseCase.apply(replaceCreditCardCommand);

        Assertions.assertEquals(1, result.size());

        CreatedClient client = (CreatedClient) createClientEvents.get(0);

        Assertions.assertEquals(client.aggregateRootId(), replacedCreditCard.getClientId().value());
        Assertions.assertEquals(client.getCreditCard().creditCardNumber(), replacedCreditCard.getCreditCardNumber().value());
        Assertions.assertEquals(client.getCreditCard().creditCardExpDate(), replacedCreditCard.getCreditCardExpDate().value());
        Assertions.assertEquals(client.getCreditCard().creditCardCcv(), replacedCreditCard.getCreditCardCcv().value());
    }
}
