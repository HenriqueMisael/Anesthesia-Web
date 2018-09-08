package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfContentException;
import br.uem.iss.anesthesia.model.business.exception.InvalidCpfFormatException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.entity.DefaultModel;
import org.springframework.data.repository.CrudRepository;

public abstract class CreateModelBusiness<T extends DefaultModel> {

    private final CrudRepository<T, Long> repository;

    public CreateModelBusiness(CrudRepository repository) {
        this.repository = repository;
    }

    public void create(T model) throws BusinessRuleException {
        validateFields(model);
        saveDependencies(model);
        repository.save(model);
    }

    protected abstract void saveDependencies(T patient);

    protected abstract void validateFields(T patient) throws InvalidCpfFormatException, InvalidCpfContentException, NullContentNotAllowedException;
}
