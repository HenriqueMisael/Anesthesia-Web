package br.uem.iss.anesthesia.controller.request;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;

import java.time.LocalDate;

public class ProcessReportRequest {

    private PatientModel patient;
    private LocalDate initial;
    private LocalDate end;

    public LocalDate getInitial() {
        return initial;
    }

    public void setInitial(LocalDate initial) {
        this.initial = initial;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "ProcessReportRequest{" +
                "patient=" + patient +
                ", initial=" + initial +
                ", end=" + end +
                '}';
    }
}
