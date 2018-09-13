package br.uem.iss.anesthesia.model.entity;

public enum CivilState {
    MARRIED("Casado"), SINGLE("Solteiro"), DIVORCED("Divorciado"), WIDOW("Viúvo");
    private final String description;

    CivilState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
