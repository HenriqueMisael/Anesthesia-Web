package br.uem.iss.anesthesia.model.business.exception;

public class NumberContendNotAllowedException extends BusinessRuleException {

    public NumberContendNotAllowedException(String field) {
        super(field + " não pode conter numeros");
    }
}
