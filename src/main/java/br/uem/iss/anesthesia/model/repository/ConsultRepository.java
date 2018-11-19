package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends CrudRepository<AppointmentModel, Long> {



    Iterable<AppointmentModel> findByActiveTrue();

}
