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

    private String name;
    private String CRM;
    private boolean segundaManha;
    private boolean segundaTarde;
    private boolean tercaManha;
    private boolean tercaTarde;
    private boolean quartaManha;
    private boolean quartaTarde;
    private boolean quintaManha;
    private boolean quintaTarde;
    private boolean sextaManha;
    private boolean sextaTarde;
    private boolean sabadoManha;
    private boolean sabadoTarde;
    private boolean domingoManha;
    private boolean domingoTarde;
    private boolean active = true;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
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

    @Override
    public String toString() {
        return "CRM: " + this.CRM + ", Nome: " + this.name+ ", Ativo: " + this.active + ", Segunda Manhã: " + this.segundaManha
                + ", Segunda Tarde: " + this.segundaTarde;
    }

    public boolean isSegundaManha() {
        return segundaManha;
    }

    public void setSegundaManha(boolean segundaManha) {
        this.segundaManha = segundaManha;
    }

    public boolean isSegundaTarde() {
        return segundaTarde;
    }

    public void setSegundaTarde(boolean segundaTarde) {
        this.segundaTarde = segundaTarde;
    }

    public boolean isTercaManha() {
        return tercaManha;
    }

    public void setTercaManha(boolean tercaManha) {
        this.tercaManha = tercaManha;
    }

    public boolean isTercaTarde() {
        return tercaTarde;
    }

    public void setTercaTarde(boolean tercaTarde) {
        this.tercaTarde = tercaTarde;
    }

    public boolean isQuartaManha() {
        return quartaManha;
    }

    public void setQuartaManha(boolean quartaManha) {
        this.quartaManha = quartaManha;
    }

    public boolean isQuartaTarde() {
        return quartaTarde;
    }

    public void setQuartaTarde(boolean quartaTarde) {
        this.quartaTarde = quartaTarde;
    }

    public boolean isQuintaManha() {
        return quintaManha;
    }

    public void setQuintaManha(boolean quintaManha) {
        this.quintaManha = quintaManha;
    }

    public boolean isQuintaTarde() {
        return quintaTarde;
    }

    public void setQuintaTarde(boolean quintaTarde) {
        this.quintaTarde = quintaTarde;
    }

    public boolean isSextaManha() {
        return sextaManha;
    }

    public void setSextaManha(boolean sextaManha) {
        this.sextaManha = sextaManha;
    }

    public boolean isSextaTarde() {
        return sextaTarde;
    }

    public void setSextaTarde(boolean sextaTarde) {
        this.sextaTarde = sextaTarde;
    }

    public boolean isSabadoManha() {
        return sabadoManha;
    }

    public void setSabadoManha(boolean sabadoManha) {
        this.sabadoManha = sabadoManha;
    }

    public boolean isSabadoTarde() {
        return sabadoTarde;
    }

    public void setSabadoTarde(boolean sabadoTarde) {
        this.sabadoTarde = sabadoTarde;
    }

    public boolean isDomingoManha() {
        return domingoManha;
    }

    public void setDomingoManha(boolean domingoManha) {
        this.domingoManha = domingoManha;
    }

    public boolean isDomingoTarde() {
        return domingoTarde;
    }

    public void setDomingoTarde(boolean domingoTarde) {
        this.domingoTarde = domingoTarde;
    }
}
