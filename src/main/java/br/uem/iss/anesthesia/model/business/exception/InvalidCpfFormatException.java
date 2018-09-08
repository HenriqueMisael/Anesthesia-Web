package br.uem.iss.anesthesia.model.business.exception;

public class InvalidCpfFormatException extends BusinessRuleException {

    public InvalidCpfFormatException() {
        super("Formato do CPF inválido: CPF deve ter 11 dígitos");
    }
}
