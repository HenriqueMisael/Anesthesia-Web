package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveBackgroundBusiness extends SaveModelBusiness<BackgroundModel> {

    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveBackgroundBusiness(BackgroundRepository repository, NameNotNullValidator nameNotNullValidator) {
        super(repository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(BackgroundModel model) {
    }

    @Override
    protected void validateFields(BackgroundModel model) throws BusinessRuleException {
        nameNotNullValidator.validate(model.getName());
    }
}
