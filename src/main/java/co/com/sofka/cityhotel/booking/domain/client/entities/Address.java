package co.com.sofka.cityhotel.booking.domain.client.entities;

import co.com.sofka.cityhotel.booking.domain.client.values.address.AddressValue;
import co.com.sofka.cityhotel.booking.domain.client.values.identities.AddressId;
import co.com.sofka.cityhotel.booking.domain.generic.Entity;

public class Address extends Entity<AddressId> {

    private AddressValue addressValue;

    private Address(AddressId addressId) {
        super(addressId);
    }

    private Address(AddressId addressId, AddressValue addressValue) {
        super(addressId);
        this.addressValue = addressValue;
    }

    public static Address from (AddressId addressId, AddressValue addressValue) {
        return new Address(addressId, addressValue);
    }

    public String addressValue() {
        return addressValue.value();
    }

    public void updateAddressValue(AddressValue addressValue) {
        this.addressValue = addressValue;
    }
}
