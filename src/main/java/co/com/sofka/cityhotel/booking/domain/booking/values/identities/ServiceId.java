package co.com.sofka.cityhotel.booking.domain.booking.values.identities;

import co.com.sofka.cityhotel.booking.domain.generic.Identity;

public class ServiceId extends Identity {

    public ServiceId() {}

    private ServiceId(String uuid) {
        super(uuid);
    }

    public static ServiceId of(String uuid) {
        return new ServiceId(uuid);
    }
}
