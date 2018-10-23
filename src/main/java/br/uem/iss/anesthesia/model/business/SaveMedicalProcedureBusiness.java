package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.MedicalProcedureModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import br.uem.iss.anesthesia.model.repository.MedicalProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMedicalProcedureBusiness extends SaveModelBusiness<MedicalProcedureModel> {

    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveMedicalProcedureBusiness(MedicalProcedureRepository repository, NameNotNullValidator nameNotNullValidator) {
        super(repository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(MedicalProcedureModel model) {
    }

    @Override
    protected void validateFields(MedicalProcedureModel model) throws BusinessRuleException {
        nameNotNullValidator.validate(model.getName());
    }
}
