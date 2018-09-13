package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class BackgroundModel extends DefaultModel {

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
        if (!(o instanceof BackgroundModel))
            return false;
        BackgroundModel that = (BackgroundModel) o;
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
