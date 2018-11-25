package br.uem.iss.anesthesia.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="Process")
public class ProcessModel extends DefaultModel {

    @OneToOne
    private DoctorModel doctor;
    @OneToOne
    private PatientModel patient;
    @OneToOne
    private MedicalProcedureModel medicalProcedure;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ProcessExamsModel> procesexams;
    private boolean active = true;
    private LocalDate inicialDate;

    public String getPacienteMedico(){
        String texto = "Paciente: "+ this.getPatient().getName() + " | Médico: " + this.getDoctor().getName() ;
        return texto;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public LocalDate getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(LocalDate inicialDate) {
        this.inicialDate = inicialDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void inactivate() {
        active = false;
    }

    public MedicalProcedureModel getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(MedicalProcedureModel medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }

    public List<ProcessExamsModel> getProcesexams() {
        return procesexams;
    }

    public void setProcesexams(List<ProcessExamsModel> procesexams) {
        this.procesexams = procesexams;
    }

    @Override
    public String toString() {
        return "ProcessModel{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", medicalProcedure=" + medicalProcedure +
                ", procesexams=" + procesexams +
                ", active=" + active +
                ", inicialDate=" + inicialDate +
                '}';
    }

    public ProcessModel(DoctorModel doctor, PatientModel patient, MedicalProcedureModel medicalProcedure, List<ProcessExamsModel> procesexams, boolean active) {
        this.doctor = doctor;
        this.patient = patient;
        this.medicalProcedure = medicalProcedure;
        this.procesexams = procesexams;
        this.active = active;
    }

    public ProcessModel() {
    }
    public ProcessModel(Long id) {
        this.setId(id);
    }
}
