package br.uem.iss.anesthesia.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Exam")
public class ExamModel extends DefaultModel{
    private String name;
    private String description;
    private int jejumTime;


    public ExamModel(){

    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJejumTime() {
        return jejumTime;
    }

    public void setJejumTime(int jejumTime) {
        this.jejumTime = jejumTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
