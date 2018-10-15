package br.uem.iss.anesthesia.model.business.exception;

public class PatientWithoutContactException extends BusinessRuleException {

    public PatientWithoutContactException() {
        super("Paciente precisa de ao menos uma informação de contato");
    }
}
