package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.entity.DefaultModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import br.uem.iss.anesthesia.model.repository.CityRepository;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelNoveltyChecker {

    private CityRepository cityRepository;
    private BackgroundRepository backgroundRepository;
    private MedicineRepository medicineRepository;

    @Autowired
    public ModelNoveltyChecker(CityRepository cityRepository, BackgroundRepository backgroundRepository, MedicineRepository medicineRepository) {
        this.cityRepository = cityRepository;
        this.backgroundRepository = backgroundRepository;
        this.medicineRepository = medicineRepository;
    }

    public boolean check(CityModel model) {
        if (model == null) {
            return false;
        }
        return check(model, cityRepository.findByName(model.getName()));
    }

    public boolean check(BackgroundModel model) {
        if (model == null) {
            return false;
        }
        return check(model, backgroundRepository.findByName(model.getName()));
    }

    public boolean check(MedicineModel model) {
        if (model == null) {
            return false;
        }
        return check(model, medicineRepository.findByName(model.getName()));
    }

    private <T extends DefaultModel> boolean check(T model, Optional<T> persisted) {
        if (persisted.isPresent()) {
            model.setId(persisted.get().getId());
        }
        return model.isNew();
    }
}
