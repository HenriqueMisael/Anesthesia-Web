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
    @Mock
    private ModelNoveltyChecker modelNoveltyChecker;
    @InjectMocks
    private SavePatientBusiness savePatientBusiness;

    @Override
    public void before() {
        super.before();
        doReturn(true).when(modelNoveltyChecker).check(newBackground);
        doReturn(true).when(modelNoveltyChecker).check(newMedicine);
        doReturn(false).when(modelNoveltyChecker).check(persistedBackground);
        doReturn(false).when(modelNoveltyChecker).check(persistedMedicine);
    }

    @Test
    public void allFieldsNewCity() throws BusinessRuleException {
        mockPatient();
        doReturn(true).when(modelNoveltyChecker).check(city);
        doReturn(asSet(newBackground, persistedBackground)).when(patientModel).getBackgrounds();
        doReturn(asSet(newMedicine, persistedMedicine)).when(patientModel).getMedicines();
        save(patientModel);
        verify(modelNoveltyChecker).check(patientModel.getCity());
        for (BackgroundModel background : patientModel.getBackgrounds()) {
            verify(modelNoveltyChecker).check(background);
        }
        for (MedicineModel medicine : patientModel.getMedicines()) {
            verify(modelNoveltyChecker).check(medicine);
        }
        verify(saveCityBusiness).save(patientModel.getCity());
        verify(saveBackgroundBusiness).save(newBackground);
        verify(saveMedicineBusiness).save(newMedicine);
    }

    @Test
    public void essentialFieldsPersistedCity() throws BusinessRuleException {
        mockPatient();
        doReturn(false).when(modelNoveltyChecker).check(city);
        save(patientModel);
    }

    private void mockPatient() {
        doReturn("09213087950").when(patientModel).getCpf();
        doReturn("Henrique Misael").when(patientModel).getName();
        doReturn("Machado").when(patientModel).getSurname();
        doReturn(city).when(patientModel).getCity();
    }

    private void save(PatientModel patientModel) throws BusinessRuleException {
        savePatientBusiness.save(patientModel);
        verify(cpfValidator).validate(patientModel.getCpf());
        verify(nameNotNullValidator).validate(patientModel.getName());
        verify(surnameNotNullValidator).validate(patientModel.getSurname());
        verify(patientRepository).save(patientModel);
    }
}