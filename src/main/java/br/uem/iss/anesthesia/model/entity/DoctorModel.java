package br.uem.iss.anesthesia.model.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Doctors")
public class DoctorModel extends DefaultModel {

    private String nome;
    private String CRM;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OfficeHoursModel> disponibilidade;
    @ColumnDefault("true")
    private boolean active;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public List<OfficeHoursModel> getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(List<OfficeHoursModel> disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
