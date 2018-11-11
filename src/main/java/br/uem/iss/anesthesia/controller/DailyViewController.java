package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.view.AbstractModelAndView;
import br.uem.iss.anesthesia.view.DailyViewView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/daily-view")
public class DailyViewController extends AbstractController {

    @GetMapping
    public AbstractModelAndView home() {
        Set<AppointmentModel> morning = new HashSet<>();
        Set<AppointmentModel> afternoon = new HashSet<>();
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
        return new DailyViewView(morning, afternoon, LocalDate.now());
    }

    private PatientModel newPatient(String name) {
        PatientModel patientModel = new PatientModel();
        patientModel.setName(name);
        return patientModel;
    }

    private AppointmentModel newAppointment(LocalDateTime date, DoctorModel doctor, PatientModel patient) {
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setDoctor(doctor);
        appointmentModel.setDate(date);
        appointmentModel.setPatient(patient);
        return appointmentModel;
    }
}
