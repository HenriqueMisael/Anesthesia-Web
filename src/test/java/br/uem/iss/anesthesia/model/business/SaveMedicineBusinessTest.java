package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class SaveMedicineBusinessTest extends UnitTest {

    @Mock
    private MedicineRepository medicineRepository;
    @Mock
    private NameNotNullValidator nameNotNullValidator;
    @InjectMocks
    private SaveMedicineBusiness saveMedicineBusiness;

    public void happyDay() throws BusinessRuleException {
        MedicineModel medicineModel = new MedicineModel();
        medicineModel.setName("Dorflex");
        saveMedicineBusiness.save(medicineModel);
        verify(nameNotNullValidator).validate(medicineModel.getName());
        verify(medicineRepository).save(medicineModel);
    }
}