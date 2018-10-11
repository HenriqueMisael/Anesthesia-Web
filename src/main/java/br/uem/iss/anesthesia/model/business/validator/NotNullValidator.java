package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;

public abstract class NotNullValidator {

    private String field;

    protected NotNullValidator(String field) {
        this.field = field;
    }

    public void validate(String data) throws NullContentNotAllowedException {
        if (data == null || data.isEmpty()) {
            throw new NullContentNotAllowedException(field);
        }
    }
}
