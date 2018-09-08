package br.uem.iss.anesthesia.model.business.validator;

import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class SurnameNotNullValidatorTest {

    @InjectMocks
    private SurnameNotNullValidator surnameNotNullValidator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test(expected = NullContentNotAllowedException.class)
    public void nullContent() throws NullContentNotAllowedException {
        surnameNotNullValidator.validate(null);
    }
}