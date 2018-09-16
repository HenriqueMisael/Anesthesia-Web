package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OfficeHours")
public class OfficeHoursModel extends DefaultModel {
    private Week diaSemana;
    private boolean isManha;
    private boolean isTarde;
    @OneToOne
    private DoctorModel doctor;

    public Week getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Week diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isManha() {
        return isManha;
    }

    public void setManha(boolean manha) {
        isManha = manha;
    }

    public boolean isTarde() {
        return isTarde;
    }

    public void setTarde(boolean tarde) {
        isTarde = tarde;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }
}
