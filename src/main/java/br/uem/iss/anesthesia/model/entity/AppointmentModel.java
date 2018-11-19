package br.uem.iss.anesthesia.model.entity;

import br.uem.iss.anesthesia.model.entity.DefaultModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "consult")
public class AppointmentModel extends DefaultModel {

    private ProcessModel process;
    private LocalDateTime date;
    private boolean active;

    public AppointmentModel(ProcessModel process, LocalDateTime date, boolean active) {
        this.process = process;
        this.date = date;
        this.active = active;
    }

    public AppointmentModel() {

    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ProcessModel getProcess() {
        return process;
    }

    public void setProcess(ProcessModel process) {
        this.process = process;
    }

    public void inactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
