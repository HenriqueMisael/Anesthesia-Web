package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentModel, Long> {

    List<AppointmentModel> findByDateBetween(LocalDateTime initial, LocalDateTime end);

    List<AppointmentModel> findByDoctorAndDateBetween(DoctorModel doctor, LocalDateTime initial, LocalDateTime end);
}
