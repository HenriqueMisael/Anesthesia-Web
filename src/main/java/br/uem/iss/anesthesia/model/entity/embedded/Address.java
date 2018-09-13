package br.uem.iss.anesthesia.model.entity.embedded;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String addressDistrict;
    private String addressPlace;
    private Integer addressNumber;

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressPlace() {
        return addressPlace;
    }

    public void setAddressPlace(String addressPlace) {
        this.addressPlace = addressPlace;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;
        Address address = (Address) o;
        return Objects.equals(getAddressDistrict(), address.getAddressDistrict()) && Objects.equals(getAddressPlace(), address.getAddressPlace()) && Objects.equals(getAddressNumber(), address.getAddressNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressDistrict(), getAddressPlace(), getAddressNumber());
    }

    @Override
    public String toString() {
        return getAddressPlace() + ", " + getAddressNumber() + ", " + getAddressDistrict();
    }
}
