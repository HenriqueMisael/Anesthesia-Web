package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import org.springframework.stereotype.Component;

@Component
public class CpfDigitValidator {

    public void validate(String cpf, int digit) throws InvalidCpfContentException {
        int sum = getSumByDigit(cpf, digit);
        Integer resultExpected = Integer.valueOf(cpf.substring(9, 10));
        validateDigit(sum, resultExpected);
    }

    private int getSumByDigit(String cpf, int digit) {
        int multiplier = 9 + digit;
        String substring = cpf.substring(0, 8 + digit);
        return getSum(multiplier, substring.toCharArray());
    }

    private void validateDigit(int sum, Integer resultExpected) throws InvalidCpfContentException {
        if ((sum * 10) % 11 != resultExpected) {
            throw new InvalidCpfContentException();
        }
    }

    private int getSum(int multiplier, char[] digits) {
        int sum = 0;
        for (char digit : digits) {
            int number = Integer.valueOf(String.valueOf(digit));
            sum += number * multiplier;
            multiplier--;
        }
        return sum;
    }
}
