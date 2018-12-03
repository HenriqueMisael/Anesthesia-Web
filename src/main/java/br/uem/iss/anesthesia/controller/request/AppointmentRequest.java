package br.uem.iss.anesthesia.controller.request;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
//import com.sun.deploy.ref.AppModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentRequest {

    public ProcessModel process;
    private LocalDate date;
    private String hour;
    private boolean active;
    private Long id;

    public AppointmentRequest() {
    }

    public AppointmentRequest(AppointmentModel appointmentModel) {
        this.id = appointmentModel.getId();
        this.process = appointmentModel.getProcess();
        this.date = appointmentModel.getDate().toLocalDate();
        this.hour = appointmentModel.getDate().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.active = appointmentModel.isActive();
    }

    public ProcessModel getProcess() {
        return process;
    }

    public void setProcess(ProcessModel process) {
        this.process = process;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
