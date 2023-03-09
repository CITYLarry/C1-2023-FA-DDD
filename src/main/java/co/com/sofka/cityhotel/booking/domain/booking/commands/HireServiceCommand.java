package co.com.sofka.cityhotel.booking.domain.booking.commands;

import co.com.sofka.cityhotel.booking.domain.booking.values.service.ServiceType;
import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class HireServiceCommand extends Command {

    private ServiceType.ServiceList service;
    private String bookingId;

    private HireServiceCommand() {}

    public HireServiceCommand(ServiceType.ServiceList service, String bookingId) {
        this.service = service;
        this.bookingId = bookingId;
    }

    public ServiceType.ServiceList getService() {
        return service;
    }

    public void setService(ServiceType.ServiceList service) {
        this.service = service;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
