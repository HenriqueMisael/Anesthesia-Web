package br.uem.iss.anesthesia.model.entity;

import javax.persistence.*;
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
    @OneToMany(fetch = FetchType.LAZY)
    private List<ExamModel> exams;
    private boolean active = true;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar inicialDate;


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

    public Calendar getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(Calendar inicialDate) {
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

    public List<ExamModel> getExams() {
        return exams;
    }

    public void setExams(List<ExamModel> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Ativo: " + this.active + ", Doctor: " + this.doctor+ ", Paciente: " + this.patient.getName();
    }
}
