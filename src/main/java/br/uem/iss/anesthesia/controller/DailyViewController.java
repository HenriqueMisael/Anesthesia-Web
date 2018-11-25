package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.view.AbstractModelAndView;
import br.uem.iss.anesthesia.view.DailyViewView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/daily-view")
public class DailyViewController extends AbstractController {

    private AppointmentRepository appointmentRepository;

    @GetMapping
    public AbstractModelAndView home() {
        Set<AppointmentModel> morning = new TreeSet<>();
        Set<AppointmentModel> afternoon = new TreeSet<>();

        //        loadTestData(morning, afternoon);
        loadData(morning, afternoon);

        return new DailyViewView(morning, afternoon, LocalDate.now());
    }

    private void loadData(Set<AppointmentModel> morning, Set<AppointmentModel> afternoon) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime noon = startOfDay.plusHours(12);
        LocalDateTime endOfDay = noon.plusHours(5).plusMinutes(30);

        for (AppointmentModel appointmentModel : appointmentRepository.findByDateBetween(startOfDay, noon.minusMinutes(30))) {
            morning.add(appointmentModel);
        }

        for (AppointmentModel appointmentModel : appointmentRepository.findByDateBetween(noon, endOfDay)) {
            afternoon.add(appointmentModel);
        }
    }

    private void loadTestData(Set<AppointmentModel> morning, Set<AppointmentModel> afternoon) {
        LocalDateTime time = LocalDate.now().atStartOfDay();
        DoctorModel doctor = new DoctorModel();
        doctor.setName("Doutor Médico");
        morning.add(newAppointment(time.plusHours(8), doctor, newPatient("Henrique Misael")));
        morning.add(newAppointment(time.plusHours(10), doctor, newPatient("João Kleber")));
        morning.add(newAppointment(time.plusHours(11), doctor, newPatient("João Souza")));
        morning.add(newAppointment(time.plusHours(11).plusMinutes(30), doctor, newPatient("João Roberto")));
        time = LocalDate.now().atStartOfDay().plusHours(12);
        afternoon.add(newAppointment(time.plusHours(2), doctor, newPatient("José Cleiton")));
        afternoon.add(newAppointment(time.plusHours(2).plusMinutes(30), doctor, newPatient("Luiza Lemos")));
        afternoon.add(newAppointment(time.plusHours(3), doctor, newPatient("Canadense")));
        afternoon.add(newAppointment(time.plusHours(3).plusMinutes(30), doctor, newPatient("Fernanda Azevedo")));
        afternoon.add(newAppointment(time.plusHours(4), doctor, newPatient("Julio Camargo")));
        afternoon.add(newAppointment(time.plusHours(4).plusMinutes(30), doctor, newPatient("Henrique Damasceno")));
        afternoon.add(newAppointment(time.plusHours(5), doctor, newPatient("Stéphanie Caroline")));
        afternoon.add(newAppointment(time.plusHours(5).plusMinutes(30), doctor, newPatient("Alan Willian")));
        afternoon.add(newAppointment(time.plusHours(6), doctor, newPatient("Luciana Rathunde")));
    }

    private PatientModel newPatient(String name) {
        PatientModel patientModel = new PatientModel();
        patientModel.setName(name);
        return patientModel;
    }

    private AppointmentModel newAppointment(LocalDateTime date, DoctorModel doctor, PatientModel patient) {
        ProcessModel processModel = new ProcessModel();
        processModel.setDoctor(doctor);
        processModel.setPatient(patient);
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setDate(date);
        appointmentModel.setProcess(processModel);
        return appointmentModel;
    }
}
