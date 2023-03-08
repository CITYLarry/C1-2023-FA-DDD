package co.com.sofka.cityhotel.booking.domain.client.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class CreditCardId extends Identity {

    public CreditCardId() {}

    private CreditCardId(String uuid) {
        super(uuid);
    }

    public static CreditCardId of(String uuid) {
        return new CreditCardId(uuid);
    }
}
