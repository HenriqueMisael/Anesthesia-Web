package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MedicalProcedures")
public class MedicalProcedureModel extends DefaultModel {

    private String name;
    private boolean active;
    private List<ExamModel> exams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ExamModel> getExams() { return exams; }

    public void setExams(List<ExamModel> exams) { this.exams = exams; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BackgroundModel))
            return false;
        BackgroundModel that = (BackgroundModel) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
