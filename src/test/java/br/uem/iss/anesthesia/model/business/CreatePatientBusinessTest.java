package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class CreatePatientBusinessTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private CpfValidator cpfValidator;
    @Mock
    private NameNotNullValidator nameNotNullValidator;
    @Mock
    private SurnameNotNullValidator surnameNotNullValidator;
    @InjectMocks
    private CreatePatientBusiness createPatientBusiness;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void happyDay() throws BusinessRuleException {
        PatientModel patientModel = new PatientModel();
        patientModel.setName("Henrique Misael");
        patientModel.setSurname("Machado");
        patientModel.setCpf("092.130.879-50");
        createPatientBusiness.create(patientModel);
        verify(cpfValidator).validate(patientModel.getCpf());
        verify(nameNotNullValidator).validate(patientModel.getName());
        verify(surnameNotNullValidator).validate(patientModel.getSurname());
        verify(patientRepository).save(patientModel);
    }
}