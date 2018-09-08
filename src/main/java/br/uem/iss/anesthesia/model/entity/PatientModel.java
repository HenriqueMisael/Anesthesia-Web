package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PatientModel extends DefaultModel {

    private String name;
    private String surname;
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PatientModel))
            return false;
        PatientModel that = (PatientModel) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname());
    }

    @Override
    protected PatientModel clone() throws CloneNotSupportedException {
        PatientModel clone = (PatientModel) super.clone();
        clone.setName(name);
        clone.setSurname(surname);
        return clone;
    }
}
