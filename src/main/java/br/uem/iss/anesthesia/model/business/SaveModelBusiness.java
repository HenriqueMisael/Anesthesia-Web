package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.DefaultModel;
import org.springframework.data.repository.CrudRepository;

public abstract class SaveModelBusiness<T extends DefaultModel> {

    private final CrudRepository<T, Long> repository;

    public SaveModelBusiness(CrudRepository repository) {
        this.repository = repository;
    }

    public void save(T model) throws BusinessRuleException {
        validateFields(model);
        saveDependencies(model);
        repository.save(model);
    }

    protected abstract void saveDependencies(T model) throws BusinessRuleException;

    protected abstract void validateFields(T model) throws BusinessRuleException;
}
