package br.uem.iss.anesthesia.model.entity;

import br.uem.iss.anesthesia.model.entity.DefaultModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consults")
public class AppointmentModel extends DefaultModel {

    private ProcessModel process;
    private LocalDateTime date;



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
}
