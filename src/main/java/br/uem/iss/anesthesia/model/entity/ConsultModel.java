package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Consults")
public class ConsultModel extends DefaultModel {

    PatientModel patient;
    DoctorModel doctor;
    ProcessModel process;
    private boolean active;

    public ConsultModel(PatientModel patient, DoctorModel doctor, ProcessModel process) {
        this.patient = patient;
        this.doctor = doctor;
        this.process = process;
    }

    public void inactivate() {
        active = false;
    }

    public ConsultModel() { }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public ProcessModel getProcess() {
        return process;
    }

    public void setProcess(ProcessModel process) {
        this.process = process;
    }
    private void copy(Set target, Set source) {
        for (Object o : source) {
            target.add(o);
        }
    }

}
