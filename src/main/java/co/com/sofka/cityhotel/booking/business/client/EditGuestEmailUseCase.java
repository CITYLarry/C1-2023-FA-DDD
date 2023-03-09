package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.EditGuestEmailCommand;
import co.com.sofka.cityhotel.booking.domain.client.values.guest.GuestEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.GuestId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class EditGuestEmailUseCase implements CommandUseCase<EditGuestEmailCommand> {

    private final EventsRepository eventsRepository;

    public EditGuestEmailUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(EditGuestEmailCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), result);
        client.editGuestEmail(
                GuestId.of(command.getGuestId()),
                new GuestEmail(command.getGuestEmail()));
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
