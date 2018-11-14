package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.ConsultModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends CrudRepository<ConsultModel, Long> {



    Iterable<ConsultModel> findByActiveTrue();
    Iterable<ConsultModel> findByNameContainingAndActiveTrue(String name);
}
