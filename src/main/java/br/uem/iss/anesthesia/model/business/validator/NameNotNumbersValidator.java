package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.NumberContendNotAllowedException;

public class NameNotNumbersValidator {

    private String field;

    protected NameNotNumbersValidator(String field) {
        this.field = field;
    }

    public void validate(String data) throws NumberContendNotAllowedException {
        if (!data.matches("[a-zA-Z]+$")) {
            throw new NumberContendNotAllowedException(field);
            // str consists entirely of letters
        }
    }
}