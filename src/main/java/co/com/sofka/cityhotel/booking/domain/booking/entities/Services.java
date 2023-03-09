package co.com.sofka.cityhotel.booking.domain.booking.entities;

import co.com.sofka.cityhotel.booking.domain.booking.values.identities.ServiceId;
import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class Services extends Entity<ServiceId> {

    private List<ServiceType> services;

    private Services(ServiceId serviceId) {
        super(serviceId);
    }

    private Services(ServiceId serviceId, ServiceType serviceType) {
        super(serviceId);
        this.services = new ArrayList<>();
        this.services.add(serviceType);
    }

    public static Services from(ServiceId serviceId, ServiceType serviceType) {
        return new Services(serviceId, serviceType);
    }

    public List<ServiceType> services() {
        return services;
    }

    public void hireService(ServiceType serviceType) {
        this.services.add(serviceType);
    }

    public void removeService(ServiceType serviceType) {
        this.services.remove(serviceType);
    }

    public void changeHiredService(ServiceType serviceType, ServiceType newServiceType) {
        this.services.remove(serviceType);
        this.services.add(newServiceType);
    }
}
