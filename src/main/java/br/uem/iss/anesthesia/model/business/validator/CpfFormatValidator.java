package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import org.springframework.stereotype.Component;

@Component
public class CpfFormatValidator {

    public void validate(String cpf) throws InvalidCpfFormatException {
        if (!cpf.matches("^[0-9]{11}$")) {
            throw new InvalidCpfFormatException();
        }
    }
}
