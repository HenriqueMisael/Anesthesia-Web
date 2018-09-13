package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class SaveBackgroundBusinessTest extends UnitTest {

    @Mock
    private BackgroundRepository backgroundRepository;
    @Mock
    private NameNotNullValidator nameNotNullValidator;
    @InjectMocks
    private SaveBackgroundBusiness saveBackgroundBusiness;

    public void happyDay() throws BusinessRuleException {
        BackgroundModel background = new BackgroundModel();
        background.setName("Exofagite");
        saveBackgroundBusiness.save(background);
        verify(nameNotNullValidator).validate(background.getName());
        verify(backgroundRepository).save(background);
    }
}