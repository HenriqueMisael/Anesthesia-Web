package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMedicineBusiness extends SaveModelBusiness<MedicineModel> {

    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveMedicineBusiness(MedicineRepository repository, NameNotNullValidator nameNotNullValidator) {
        super(repository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(MedicineModel model) {
    }

    @Override
    protected void validateFields(MedicineModel model) throws BusinessRuleException {
        nameNotNullValidator.validate(model.getName());
    }
}
