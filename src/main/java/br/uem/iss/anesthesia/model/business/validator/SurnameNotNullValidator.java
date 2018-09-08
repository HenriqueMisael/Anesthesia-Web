package br.uem.iss.anesthesia.model.business.validator;

import org.springframework.stereotype.Component;

@Component
public class SurnameNotNullValidator extends NotNullValidator {

    public SurnameNotNullValidator() {
        super("Sobrenome");
    }
}
