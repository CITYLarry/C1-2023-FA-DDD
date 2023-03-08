package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.AddGuestCommand;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestName;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class AddGuestUseCase implements CommandUseCase<AddGuestCommand> {

    private final EventsRepository eventsRepository;

    public AddGuestUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddGuestCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), result);
        client.addGuest(
                GuestId.of(command.getGuestId()),
                new GuestName(command.getGuestName()),
                new GuestEmail(command.getGuestEmail()),
                ClientId.of(command.getClientId())
        );
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
