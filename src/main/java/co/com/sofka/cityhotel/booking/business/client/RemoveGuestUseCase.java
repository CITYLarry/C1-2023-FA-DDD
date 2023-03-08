package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.RemoveGuestCommand;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class RemoveGuestUseCase implements CommandUseCase<RemoveGuestCommand> {

    private final EventsRepository eventsRepository;

    public RemoveGuestUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(RemoveGuestCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), result);
        client.removeGuest(GuestId.of(command.getGuestId()));
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
