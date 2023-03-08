package co.com.sofka.cityhotel.booking.domain.client.commands;

import co.com.sofka.cityhotel.booking.domain.generic.Command;

public class ModifyAddressCommand extends Command {

    private String addressValue;
    private String clientId;

    private ModifyAddressCommand() {}

    public ModifyAddressCommand(String addressValue, String clientId) {
        this.addressValue = addressValue;
        this.clientId = clientId;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
