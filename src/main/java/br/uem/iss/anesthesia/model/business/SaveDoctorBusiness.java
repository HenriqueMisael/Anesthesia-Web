package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveDoctorBusiness extends SaveModelBusiness<DoctorModel> {
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveDoctorBusiness(DoctorRepository doctorRepository, NameNotNullValidator nameNotNullValidator) {
        super(doctorRepository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(DoctorModel model) throws BusinessRuleException {

    }

    @Override
    protected void validateFields(DoctorModel model) throws NullContentNotAllowedException {
        nameNotNullValidator.validate(model.getName());
    }
}
