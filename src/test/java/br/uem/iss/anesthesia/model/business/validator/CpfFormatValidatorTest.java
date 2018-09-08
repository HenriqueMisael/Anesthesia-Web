package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class CpfFormatValidatorTest {

    @InjectMocks
    private CpfFormatValidator cpfFormatValidator;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test(expected = InvalidCpfFormatException.class)
    public void cpfWithMask() throws InvalidCpfFormatException {
        cpfFormatValidator.validate("092.130.879-50");
    }

    @Test(expected = InvalidCpfFormatException.class)
    public void cpfWithLetters() throws InvalidCpfFormatException {
        cpfFormatValidator.validate("09213abc950");
    }

    @Test(expected = InvalidCpfFormatException.class)
    public void emptyCpf() throws InvalidCpfFormatException {
        cpfFormatValidator.validate("");
    }
}