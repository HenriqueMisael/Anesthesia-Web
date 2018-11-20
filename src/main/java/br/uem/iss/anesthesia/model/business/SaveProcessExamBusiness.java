package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.ProcessExamsModel;
import br.uem.iss.anesthesia.model.repository.ProcessExamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProcessExamBusiness extends SaveModelBusiness<ProcessExamsModel> {
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveProcessExamBusiness(ProcessExamsRepository processExamsRepository, NameNotNullValidator nameNotNullValidator) {
        super(processExamsRepository);
    }

    @Override
    protected void saveDependencies(ProcessExamsModel model) throws BusinessRuleException {
    }

    @Override
    protected void validateFields(ProcessExamsModel model) throws NullContentNotAllowedException {
    }
}
