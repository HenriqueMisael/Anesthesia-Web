package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.MedicalProcedureModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalProcedureRepository extends CrudRepository<MedicalProcedureModel, Long> {

    Optional<BackgroundModel> findByName(String name);
}