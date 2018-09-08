package br.uem.iss.anesthesia.model.business.validator;

import org.springframework.stereotype.Component;

@Component
public class NameNotNullValidator extends NotNullValidator {

    public NameNotNullValidator() {
        super("Nome");
    }
}
