package br.uem.iss.anesthesia.controller.request;

import br.uem.iss.anesthesia.model.entity.DoctorModel;

import java.time.LocalDateTime;

public class PatientReportRequest {

    private DoctorModel doctor;
    private LocalDateTime initial;
    private LocalDateTime end;

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getInitial() {
        return initial;
    }

    public void setInitial(LocalDateTime initial) {
        this.initial = initial;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
