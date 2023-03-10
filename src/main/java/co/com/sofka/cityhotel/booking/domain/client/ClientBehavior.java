package co.com.sofka.cityhotel.booking.domain.client;

import co.com.sofka.cityhotel.booking.domain.client.entities.CreditCard;
import co.com.sofka.cityhotel.booking.domain.client.entities.Guest;
import co.com.sofka.cityhotel.booking.domain.client.events.AddedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.CreatedClient;
import co.com.sofka.cityhotel.booking.domain.client.events.EditedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.ModifiedAddress;
import co.com.sofka.cityhotel.booking.domain.client.events.RemovedGuest;
import co.com.sofka.cityhotel.booking.domain.client.events.ReplacedCreditCard;
import co.com.sofka.cityhotel.booking.domain.generic.EventChange;

import java.util.ArrayList;

public class ClientBehavior extends EventChange {

    public ClientBehavior(Client client) {
        apply((CreatedClient event) -> {
            client.clientName = event.getClientName();
            client.clientEmail = event.getClientEmail();
            client.clientIdentification = event.getClientIdentification();
            client.address = event.getAddress();
            client.creditCard = event.getCreditCard();
            client.guestList = new ArrayList<>();
        });

        apply((ReplacedCreditCard event) -> {
           client.creditCard = CreditCard.from(
                   event.getCreditCardId(),
                   event.getCreditCardNumber(),
                   event.getCreditCardExpDate(),
                   event.getCreditCardCcv()
           );
        });

        apply((ModifiedAddress event) -> {
           client.address.updateAddressValue(event.getAddressValue());
        });

        apply((AddedGuest event) -> {
            client.guestList.add(Guest.from(
                    event.getGuestId(),
                    event.getGuestName(),
                    event.getGuestEmail()
            ));
        });

        apply((RemovedGuest event) -> {
            client.guestList.removeIf(guest -> guest.identity().value().equals(event.getGuestId().value()));
        });

        apply((EditedGuest event) -> {
            client.guestList.stream()
                    .filter(guest -> guest.identity().value().equals(event.getGuestId().value()))
                    .forEach(guest -> guest.updateGuestEmail(event.getGuestEmail()));
        });
    }
}
