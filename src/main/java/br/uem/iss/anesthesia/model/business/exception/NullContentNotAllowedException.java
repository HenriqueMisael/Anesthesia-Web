package br.uem.iss.anesthesia.model.business.exception;

public class NullContentNotAllowedException extends BusinessRuleException {

    public NullContentNotAllowedException(String field) {
        super(field + " não pode ser vazio");
    }
}
