package co.com.sofka.cityhotel.booking.domain.booking.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class CreateBookingCommand extends Command {

    private String bookingId;
    private String clientId;
    private String serviceId;
    private String paymentId;

    private CreateBookingCommand() {}

    public CreateBookingCommand(String bookingId, String clientId, String serviceId, String paymentId) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
