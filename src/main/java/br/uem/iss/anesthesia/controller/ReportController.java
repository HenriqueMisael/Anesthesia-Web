package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.model.AppointmentModel;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.view.AbstractModelAndView;
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

    public ReportController(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public AbstractModelAndView home() {
        return new AbstractModelAndView("report_index");
    }

    @GetMapping("/patient-registry/{id}")
    public AbstractModelAndView patientRegistry(@PathVariable Long id) {
        return new AbstractModelAndView("report_patient-registry", "patient", patientRepository.findById(id).get());
    }

    @GetMapping("/patient-report")
    public AbstractModelAndView patientReport() {
        AbstractModelAndView modelAndView = new AbstractModelAndView("form_patient-report", "filter", new PatientReportRequest());
        modelAndView.addObject("doctors", doctorRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/patient-report")
    public ModelAndView patientReport(@ModelAttribute PatientReportRequest request) {
        LocalDateTime initial = request.getInitial();
        LocalDateTime end = request.getEnd();
        List<AppointmentModel> appointments;
        if (request.getDoctor() == null) {
            appointments = appointmentRepository.findByDateBetween(initial, end);
        } else {
            appointments = appointmentRepository.findByDoctorAndDateBetween(request.getDoctor(), initial, end);
        }
        ModelAndView modelAndView = new ModelAndView("report_patient-report", "appointments", appointments);
        modelAndView.addObject("description", "Consultas com o médico " + request.getDoctor().getName() + " de " + request.getInitial() + " a " + request.getEnd());
        return modelAndView;
    }
}
