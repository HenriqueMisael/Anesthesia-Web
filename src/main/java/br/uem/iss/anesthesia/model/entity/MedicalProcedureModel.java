package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MedicalProcedures")
public class MedicalProcedureModel extends DefaultModel {

    private String name;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ExamModel> exams;

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

    public Set<ExamModel> getExams() {
        return exams;
    }

    public void setExams(Set<ExamModel> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MedicalProcedureModel))
            return false;
        MedicalProcedureModel that = (MedicalProcedureModel) o;
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
