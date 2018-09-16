package br.uem.iss.anesthesia.model.entity;

public enum Week {
    SEG("Segunda-feira"), TER("Terça-feira"), QUA("Quarta-feira"), QUI("Quinta-feira"), SEX("Sexta-feita"),
    SAB("Sábado"), DOM("Domingo");

    private final String name;

    Week(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
