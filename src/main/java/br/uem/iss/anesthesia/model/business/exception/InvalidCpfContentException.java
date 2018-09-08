package br.uem.iss.anesthesia.model.business.exception;

public class InvalidCpfContentException extends BusinessRuleException {

    public InvalidCpfContentException() {
        super("Conteúdo do CPF inválido");
    }
}
