package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class CpfValidatorTest {

    @Mock
    private CpfFormatValidator cpfFormatValidator;
    @Mock
    private CpfDigitValidator cpfDigitValidator;
    @InjectMocks
    private CpfValidator cpfValidator;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void cpfWithoutMask() throws InvalidCpfFormatException, InvalidCpfContentException {
        String cpf = "09213087950";
        cpfValidator.validate(cpf);
        verify(cpfFormatValidator).validate(cpf);
        verify(cpfDigitValidator).validate(cpf, 1);
        verify(cpfDigitValidator).validate(cpf, 2);
    }
}