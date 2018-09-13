package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class CityModel extends DefaultModel {

    private String uf;
    private String name;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CityModel))
            return false;
        CityModel cityModel = (CityModel) o;
        return Objects.equals(getUf(), cityModel.getUf()) && Objects.equals(getName(), cityModel.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUf(), getName());
    }

    @Override
    public String toString() {
        return getName() + " - " + getUf();
    }
}
