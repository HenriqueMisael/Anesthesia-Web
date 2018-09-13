package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.MedicineModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends CrudRepository<MedicineModel, Long> {

}
