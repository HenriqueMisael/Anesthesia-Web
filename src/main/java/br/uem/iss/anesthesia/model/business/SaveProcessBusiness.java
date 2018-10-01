package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProcessBusiness extends SaveModelBusiness<ProcessModel> {

    @Autowired
    public SaveProcessBusiness(ProcessRepository processRepository) {
        super(processRepository);

    }

    @Override
    protected void saveDependencies(ProcessModel process) throws BusinessRuleException {

    }

    @Override
    protected void validateFields(ProcessModel model) throws NullContentNotAllowedException {
    }
}
