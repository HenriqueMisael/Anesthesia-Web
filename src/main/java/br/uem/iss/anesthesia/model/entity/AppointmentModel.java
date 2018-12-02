package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "consult")
public class AppointmentModel extends DefaultModel {

    @ManyToOne
    private ProcessModel process;
    private LocalDateTime date;
    private boolean active;

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



    @Override
    public int compareTo(Object o) {
        if (o.getClass().equals(getClass())) {
            return date.compareTo(((AppointmentModel) o).getDate());
        }
        return super.compareTo(o);
    }


}
