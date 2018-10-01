package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveExamBusiness extends SaveModelBusiness<ExamModel> {
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveExamBusiness(ExamRepository examRepository, NameNotNullValidator nameNotNullValidator) {
        super(examRepository);
        this.nameNotNullValidator = nameNotNullValidator;
    }

    @Override
    protected void saveDependencies(ExamModel model) throws BusinessRuleException {

    }

    @Override
    protected void validateFields(ExamModel model) throws BusinessRuleException {
        nameNotNullValidator.validate(model.getDescription());
    }
}
