package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ProcessExamsModel;
import org.springframework.data.repository.CrudRepository;

public interface ProcessExamsRepository extends CrudRepository<ProcessExamsModel, Long> {


}
