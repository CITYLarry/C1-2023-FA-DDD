package co.com.sofka.cityhotel.booking.domain.booking.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class PaymentId extends Identity {

    public PaymentId() {}

    private PaymentId(String uuid) {
        super(uuid);
    }

    public static PaymentId of(String uuid) {
        return new PaymentId(uuid);
    }
}
