package co.com.sofka.cityhotel.booking.domain.booking.values.service;

import co.com.sofka.cityhotel.booking.domain.generic.ValueObject;

public class ServiceType implements ValueObject<ServiceType.ServiceList> {

    private final ServiceList serviceType;

    public ServiceType(ServiceList serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public ServiceList value() {
        return serviceType;
    }

    public enum ServiceList {
        BASIC,
        WIFI,
        ROOM_SERVICE,
        RESTAURANT_PACK,
        OPEN_BAR
    }
}
