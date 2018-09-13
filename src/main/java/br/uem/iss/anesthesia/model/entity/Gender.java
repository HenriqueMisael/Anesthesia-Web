package br.uem.iss.anesthesia.model.entity;

public enum Gender {
    MALE("Masculino"), FEMALE("Feminino");
    private final String description;

    Gender(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
