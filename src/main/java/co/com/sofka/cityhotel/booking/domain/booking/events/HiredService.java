package co.com.sofka.cityhotel.booking.domain.booking.events;

import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.generic.DomainEvent;

public class HiredService extends DomainEvent {

    private ServiceType serviceType;

    private HiredService() {
        super("co.com.sofka.cityhotel.booking.domain.booking.events.HiredService");
    }

    public HiredService(ServiceType serviceType){
        super("co.com.sofka.cityhotel.booking.domain.booking.events.HiredService");
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }
}
