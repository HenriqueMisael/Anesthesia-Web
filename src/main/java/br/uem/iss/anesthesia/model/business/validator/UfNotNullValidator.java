package br.uem.iss.anesthesia.model.business.validator;

import org.springframework.stereotype.Component;

@Component
public class UfNotNullValidator extends NotNullValidator {

    protected UfNotNullValidator() {
        super("Unidade federativa");
    }
}
