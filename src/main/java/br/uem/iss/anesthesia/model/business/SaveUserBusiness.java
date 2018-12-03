package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNumbersValidator;
import br.uem.iss.anesthesia.model.entity.UserModel;
import br.uem.iss.anesthesia.model.repository.MedicalProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserBusiness extends SaveModelBusiness<UserModel> {

    private NameNotNullValidator nameNotNullValidator;
    private NameNotNumbersValidator nameNotNumbersValidator;

    @Autowired
    public SaveUserBusiness(MedicalProcedureRepository repository, NameNotNullValidator nameNotNullValidator) {
        super(repository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(UserModel model) {
    }

    @Override
    protected void validateFields(UserModel model) throws BusinessRuleException {
        nameNotNullValidator.validate(model.getLogin());
        nameNotNumbersValidator.validate(model.getLogin());
        nameNotNullValidator.validate(model.getPassword());
    }
}
