package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SavePatientBusinessTest extends UnitTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private CpfValidator cpfValidator;
    @Mock
    private NameNotNullValidator nameNotNullValidator;
    @Mock
    private SurnameNotNullValidator surnameNotNullValidator;
    @Mock
    private BackgroundModel newBackground;
    @Mock
    private MedicineModel newMedicine;
    @Mock
    private BackgroundModel persistedBackground;
    @Mock
    private MedicineModel persistedMedicine;
    @Mock
    private CityModel city;
    @Mock
    private PatientModel patientModel;
    @Mock
    private SaveCityBusiness saveCityBusiness;
    @Mock
    private SaveBackgroundBusiness saveBackgroundBusiness;
    @Mock
    private SaveMedicineBusiness saveMedicineBusiness;
    @InjectMocks
    private SavePatientBusiness createPatientBusiness;

    @Override
    public void before() {
        super.before();
        doReturn(true).when(newBackground).isNew();
        doReturn(true).when(newMedicine).isNew();
        doReturn(false).when(persistedBackground).isNew();
        doReturn(false).when(persistedMedicine).isNew();
    }

    @Test
    public void happyDayNewCity() throws BusinessRuleException {
        mockPatient();
        doReturn(true).when(city).isNew();
        doReturn(asList(newBackground, persistedBackground)).when(patientModel).getBackgrounds();
        doReturn(asList(newMedicine, persistedMedicine)).when(patientModel).getMedicines();
        save(patientModel);
        verify(saveCityBusiness).save(patientModel.getCity());
        verify(saveBackgroundBusiness).save(newBackground);
        verify(saveMedicineBusiness).save(newMedicine);
    }

    @Test
    public void happyDayPersistedCity() throws BusinessRuleException {
        mockPatient();
        doReturn(false).when(city).isNew();
        save(patientModel);
    }

    private void mockPatient() {
        doReturn("09213087950").when(patientModel).getCpf();
        doReturn("Henrique Misael").when(patientModel).getName();
        doReturn("Machado").when(patientModel).getSurname();
        doReturn(city).when(patientModel).getCity();
    }

    private void save(PatientModel patientModel) throws BusinessRuleException {
        createPatientBusiness.save(patientModel);
        verify(cpfValidator).validate(patientModel.getCpf());
        verify(nameNotNullValidator).validate(patientModel.getName());
        verify(surnameNotNullValidator).validate(patientModel.getSurname());
        verify(patientRepository).save(patientModel);
    }
}