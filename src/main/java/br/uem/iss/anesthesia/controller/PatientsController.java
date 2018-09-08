package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.CreatePatientBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.view.PatientView;
import br.uem.iss.anesthesia.view.PatientsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PatientsController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CreatePatientBusiness createPatientBusiness;

    @GetMapping("/patients")
    public ModelAndView listPatients() {
        return new PatientsView(patientRepository.findAll());
    }

    @GetMapping("/patient")
    public ModelAndView newPatient() {
        return new PatientView(null);
    }

    @RequestMapping("/editPatient/{id}")
    public ModelAndView editPatient(@PathVariable Long id) {
        return new PatientView(patientRepository.findById(id).get());
    }

    @PostMapping("/patient")
    public String savePatient(@Valid PatientModel patient, BindingResult result, RedirectAttributes attributes) {
        try {
            createPatientBusiness.create(patient);
            return "redirect:/patients";
        } catch (BusinessRuleException e) {
            attributes.addFlashAttribute("message", e.getMessage());
        }
        return null;
    }

    @RequestMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.delete(patientRepository.findById(id).get());
        return "redirect:/patients";
    }
}
