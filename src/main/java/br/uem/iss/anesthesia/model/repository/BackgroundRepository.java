package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundRepository extends CrudRepository<BackgroundModel, Long> {

}
