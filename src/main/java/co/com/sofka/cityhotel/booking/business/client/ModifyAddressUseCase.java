package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.ModifyAddressCommand;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class ModifyAddressUseCase implements CommandUseCase<ModifyAddressCommand> {

    private final EventsRepository eventsRepository;

    public ModifyAddressUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ModifyAddressCommand command) {
        List<DomainEvent> result = eventsRepository.findAggregateRootId(command.getClientId());
        Client client = Client.from(ClientId.of(command.getClientId()), result);
        client.modifyAddress(
                new AddressValue(command.getAddressValue()),
                ClientId.of(command.getClientId())
        );
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
