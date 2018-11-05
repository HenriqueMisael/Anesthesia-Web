package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultRepository extends CrudRepository<ConsultModel, Long> {

    List<AppointmentModel> findByDateBetween(LocalDateTime initial, LocalDateTime end);
    Iterable<ConsultModel> findByActiveTrue();
    Iterable<ConsultModel> findByNameContainingAndActiveTrue(String name);
}
