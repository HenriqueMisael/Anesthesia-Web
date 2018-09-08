package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CpfValidator {

    private CpfFormatValidator cpfFormatValidator;
    private CpfDigitValidator cpfDigitValidator;

    @Autowired
    public CpfValidator(CpfFormatValidator cpfFormatValidator, CpfDigitValidator cpfDigitValidator) {
        this.cpfFormatValidator = cpfFormatValidator;
        this.cpfDigitValidator = cpfDigitValidator;
    }

    public void validate(String cpf) throws InvalidCpfFormatException, InvalidCpfContentException {
        cpf = removeSeparators(cpf);
        cpfFormatValidator.validate(cpf);
        validateFirstDigit(cpf);
        validateSecondDigit(cpf);
    }

    private String removeSeparators(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        return cpf;
    }

    private void validateSecondDigit(String cpf) throws InvalidCpfContentException {
        cpfDigitValidator.validate(cpf, 2);
    }

    private void validateFirstDigit(String cpf) throws InvalidCpfContentException {
        cpfDigitValidator.validate(cpf, 1);
    }
}
