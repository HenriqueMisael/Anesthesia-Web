package br.uem.iss.anesthesia.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "process_exams")
public class ProcessExamsModel extends DefaultModel{
    private boolean approved;
    @OneToOne
    private ExamModel exams;
    @OneToOne
    private ProcessModel process;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ExamModel getExams() {
        return exams;
    }

    public void setExams(ExamModel exams) {
        this.exams = exams;
    }

    public ProcessModel getProcess() {
        return process;
    }

    public void setProcess(ProcessModel process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "ProcessExamsModel{" +
                "approved=" + approved +
                ", exams=" + exams +
                '}';
    }
}
