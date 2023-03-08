package co.com.sofka.cityhotel.booking.business.client;

import co.com.sofka.cityhotel.booking.business.commons.CommandUseCase;
import co.com.sofka.cityhotel.booking.business.commons.EventsRepository;
import co.com.sofka.cityhotel.booking.domain.client.Client;
import co.com.sofka.cityhotel.booking.domain.client.commands.CreateClientCommand;
import co.com.sofka.cityhotel.booking.domain.client.entities.Address;
import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientEmail;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientIdentification;
import co.com.sofka.cityhotel.booking.domain.client.values.client.ClientName;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardCcv;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardExpDate;
import co.com.sofka.cityhotel.booking.domain.client.values.creditcard.CreditCardNumber;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.AddressId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.ClientId;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.CreditCardId;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

import java.util.List;

public class CreateClientUseCase implements CommandUseCase<CreateClientCommand> {

    private EventsRepository eventsRepository;

    public CreateClientUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateClientCommand command) {
        Client client = new Client(
                ClientId.of(command.getClientId()),
                new ClientName(command.getClientName()),
                new ClientEmail(command.getClientEmail()),
                new ClientIdentification(command.getClientIdentification()),
                Address.from(
                        AddressId.of(command.getAddressId()),
                        new AddressValue(command.getAddress())),
                CreditCard.from(
                        CreditCardId.of(command.getCreditCardId()),
                        new CreditCardNumber(command.getCreditCardNumber()),
                        new CreditCardExpDate(command.getCreditCardExpDate()),
                        new CreditCardCcv(command.getCreditCardCcv())
                )
        );
        return client.getUncommittedChanges().stream()
                .map(eventsRepository::saveEvent)
                .toList();
    }
}
