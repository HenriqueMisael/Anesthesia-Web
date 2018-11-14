package br.uem.iss.anesthesia.model.entity;

public enum UserType {
    MEDIC("Médico"), OPERATOR("Operador");
    private final String description;

    UserType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
