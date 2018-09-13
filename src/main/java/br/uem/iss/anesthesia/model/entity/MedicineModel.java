package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class MedicineModel extends DefaultModel {

    private String name;

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
        if (!(o instanceof MedicineModel))
            return false;
        MedicineModel that = (MedicineModel) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
