package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.UfNotNullValidator;
import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveCityBusiness extends SaveModelBusiness<CityModel> {

    private NameNotNullValidator nameNotNullValidator;
    private UfNotNullValidator ufNotNullValidator;

    @Autowired
    public SaveCityBusiness(CityRepository repository, NameNotNullValidator nameNotNullValidator, UfNotNullValidator ufNotNullValidator) {
        super(repository);
        this.nameNotNullValidator = nameNotNullValidator;
        this.ufNotNullValidator = ufNotNullValidator;
    }

    @Override
    protected void saveDependencies(CityModel model) {
    }

    @Override
    protected void validateFields(CityModel model) {
    }
}
