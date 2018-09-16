package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SavePatientBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.view.PatientView;
import br.uem.iss.anesthesia.view.PatientsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PatientsController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SavePatientBusiness savePatientBusiness;

    @GetMapping("/patients")
    public ModelAndView listPatients() {
        return new PatientsView(patientRepository.findByActiveTrue());
    }

    @GetMapping("/patient")
    public ModelAndView newPatient() {
        return viewWithoutMessage(new PatientModel());
    }

    @GetMapping("/patient/{id}")
    public ModelAndView editPatient(@PathVariable Long id) {
        return viewWithoutMessage(patientRepository.findById(id).get());
    }

    @PostMapping("/patient")
    public ModelAndView savePatient(@Valid PatientModel patient) {
        try {
            savePatientBusiness.save(patient);
            return listPatients();
        } catch (BusinessRuleException e) {
            return viewWithMessage(patient, e.getMessage());
        }
    }

    @RequestMapping("/deletePatient/{id}")
    public ModelAndView deletePatient(@PathVariable Long id) {
        PatientModel patient = patientRepository.findById(id).get();
        patient.inactivate();
        try {
            savePatientBusiness.save(patient);
        } catch (BusinessRuleException e) {
        }
        return listPatients();
    }

    private PatientView viewWithoutMessage(PatientModel patient) {
        return viewWithMessage(patient, null);
    }

    private PatientView viewWithMessage(PatientModel patient, String message) {
        return new PatientView(patient, message);
    }
}
