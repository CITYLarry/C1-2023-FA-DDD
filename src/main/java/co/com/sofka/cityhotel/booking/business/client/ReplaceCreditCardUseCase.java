package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.ReplaceCreditCardCommand;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class ReplaceCreditCardUseCase implements CommandUseCase<ReplaceCreditCardCommand> {

    private final EventsRepository eventsRepository;

    public ReplaceCreditCardUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ReplaceCreditCardCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), result);
        client.replaceCreditCard(
                CreditCardId.of(command.getCreditCardId()),
                new CreditCardNumber(command.getCreditCardNumber()),
                new CreditCardExpDate(command.getCreditCardExpDate()),
                new CreditCardCcv(command.getCreditCardCcv()),
                ClientId.of(command.getClientId())
                );
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
