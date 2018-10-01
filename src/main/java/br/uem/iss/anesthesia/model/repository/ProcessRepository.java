package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.ProcessModel;
import org.springframework.data.repository.CrudRepository;

public interface ProcessRepository extends CrudRepository<ProcessModel, Long> {

    Iterable<ProcessModel> findByActiveTrue();

}
