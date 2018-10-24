package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.model.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.util.DateSupport;
import br.uem.iss.anesthesia.view.AbstractModelAndView;
import br.uem.iss.anesthesia.view.PatientRegistryView;
import br.uem.iss.anesthesia.view.PatientReportFormView;
import br.uem.iss.anesthesia.view.PatientReportView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private DateSupport dateSupport;

    public ReportController(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, DateSupport dateSupport) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.dateSupport = dateSupport;
    }

    @GetMapping
    public AbstractModelAndView home() {
        return new AbstractModelAndView("report_index");
    }

    @GetMapping("/patient-registry/{id}")
    public AbstractModelAndView patientRegistry(@PathVariable Long id) {
        return new PatientRegistryView(patientRepository.findById(id).get());
    }

    @GetMapping("/patient-report")
    public AbstractModelAndView patientReport() {
        return new PatientReportFormView(doctorRepository.findAll());
    }

    @PostMapping("/patient-report")
    public ModelAndView patientReport(@ModelAttribute PatientReportRequest request) {
        LocalDateTime initial = request.getInitial().atStartOfDay();
        LocalDateTime end = request.getEnd().atTime(23, 59, 59);
        List<AppointmentModel> appointments;
        DoctorModel doctor = request.getDoctor();
        if (doctor == null) {
            appointments = appointmentRepository.findByDateBetween(initial, end);
        } else {
            appointments = appointmentRepository.findByDoctorAndDateBetween(doctor, initial, end);
        }
        return new PatientReportView(dateSupport.format(initial), dateSupport.format(end), doctor, appointments);
    }
}
