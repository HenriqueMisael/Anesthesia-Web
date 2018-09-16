package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePatientBusiness extends SaveModelBusiness<PatientModel> {

    private CpfValidator cpfValidator;
    private NameNotNullValidator nameNotNullValidator;
    private SurnameNotNullValidator surnameNotNullValidator;
    private SaveCityBusiness saveCityBusiness;
    private SaveBackgroundBusiness saveBackgroundBusiness;
    private SaveMedicineBusiness saveMedicineBusiness;
    private ModelNoveltyChecker modelNoveltyChecker;

    @Autowired
    public SavePatientBusiness(PatientRepository patientRepository, CpfValidator cpfValidator, NameNotNullValidator nameNotNullValidator, SurnameNotNullValidator surnameNotNullValidator, SaveCityBusiness saveCityBusiness, SaveBackgroundBusiness saveBackgroundBusiness, SaveMedicineBusiness saveMedicineBusiness, ModelNoveltyChecker modelNoveltyChecker) {
        super(patientRepository);
        this.cpfValidator = cpfValidator;
        this.nameNotNullValidator = nameNotNullValidator;
        this.surnameNotNullValidator = surnameNotNullValidator;
        this.saveCityBusiness = saveCityBusiness;
        this.saveBackgroundBusiness = saveBackgroundBusiness;
        this.saveMedicineBusiness = saveMedicineBusiness;
        this.modelNoveltyChecker = modelNoveltyChecker;
    }

    @Override
    protected void saveDependencies(PatientModel model) throws BusinessRuleException {
        if (modelNoveltyChecker.check(model.getCity())) {
            saveCityBusiness.save(model.getCity());
        }
        if (model.getBackgrounds() != null) {
            for (BackgroundModel item : model.getBackgrounds()) {
                if (modelNoveltyChecker.check(item)) {
                    saveBackgroundBusiness.save(item);
                }
            }
        }
        if (model.getMedicines() != null) {
            for (MedicineModel item : model.getMedicines()) {
                if (modelNoveltyChecker.check(item)) {
                    saveMedicineBusiness.save(item);
                }
            }
        }
    }

    @Override
    protected void validateFields(PatientModel model) throws InvalidCpfFormatException, InvalidCpfContentException, NullContentNotAllowedException {
        cpfValidator.validate(model.getCpf());
        nameNotNullValidator.validate(model.getName());
        surnameNotNullValidator.validate(model.getSurname());
    }
}
