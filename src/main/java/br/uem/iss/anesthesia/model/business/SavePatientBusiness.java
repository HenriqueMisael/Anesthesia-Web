package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePatientBusiness extends SaveModelBusiness<PatientModel> {

    private CpfValidator cpfValidator;
    private NameNotNullValidator nameNotNullValidator;
    private SurnameNotNullValidator surnameNotNullValidator;

    @Autowired
    public SavePatientBusiness(PatientRepository patientRepository, CpfValidator cpfValidator, NameNotNullValidator nameNotNullValidator, SurnameNotNullValidator surnameNotNullValidator) {
        super(patientRepository);
        this.cpfValidator = cpfValidator;
        this.nameNotNullValidator = nameNotNullValidator;
        this.surnameNotNullValidator = surnameNotNullValidator;
    }

    @Override
    protected void saveDependencies(PatientModel patient) {
    }

    @Override
    protected void validateFields(PatientModel patient) throws InvalidCpfFormatException, InvalidCpfContentException, NullContentNotAllowedException {
        cpfValidator.validate(patient.getCpf());
        nameNotNullValidator.validate(patient.getName());
        surnameNotNullValidator.validate(patient.getSurname());
    }
}
