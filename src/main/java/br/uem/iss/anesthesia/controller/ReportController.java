package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
public class ReportController {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public ReportController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/patient-registry/{id}")
    public ModelAndView patientRegistry(@PathVariable Long id) {
        return new ModelAndView("report_patient-registry", "patient", patientRepository.findById(id).get());
    }

    @GetMapping("/patient-report")
    public ModelAndView patientReport() {
        ModelAndView modelAndView = new ModelAndView("form_patient-report", "filter", new PatientReportRequest());
        modelAndView.addObject("doctors", doctorRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/patient-report")
    public String patientReport(@RequestBody PatientReportRequest request) {
        System.out.println(request.getDoctor());
        System.out.println(request.getInitial());
        System.out.println(request.getEnd());
        return "redirect:/patient-report";
    }
}
