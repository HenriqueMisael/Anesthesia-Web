package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.UfNotNullValidator;
import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.repository.CityRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class SaveCityBusinessTest extends UnitTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private NameNotNullValidator nameNotNullValidator;
    @Mock
    private UfNotNullValidator ufNotNullValidator;
    @InjectMocks
    private SaveCityBusiness saveCityBusiness;

    public void happyDay() throws BusinessRuleException {
        CityModel cityModel = new CityModel();
        cityModel.setUf("PR");
        cityModel.setName("Maringá");
        saveCityBusiness.save(cityModel);
        verify(ufNotNullValidator).validate(cityModel.getUf());
        verify(nameNotNullValidator).validate(cityModel.getName());
        verify(cityRepository).save(cityModel);
    }
}