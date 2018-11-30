package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SavePatientBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.*;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import br.uem.iss.anesthesia.model.repository.CityRepository;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.view.PatientView;
import br.uem.iss.anesthesia.view.PatientsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(PatientsController.HOME)
public class PatientsController extends AbstractController {

    public static final String HOME = "/patient";
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SavePatientBusiness savePatientBusiness;
    @Autowired
    private BackgroundRepository backgroundRepository;
    @Autowired
    private MedicineRepository medicineRepository;


    @GetMapping
    public ModelAndView listPatients(@RequestParam(value = "cpf", required = false) String cpf, @RequestParam(value = "name", required = false) String name) {
        Iterable<PatientModel> patients;
        if (cpf != null && name != null) {
            patients = patientRepository.findByCpfContainingAndNameContainingAndActiveTrue(cpf, name);
        } else if (cpf != null) {
            patients = patientRepository.findByCpfContainingAndActiveTrue(cpf);
        } else if (name != null) {
            patients = patientRepository.findByNameContainingAndActiveTrue(name);
        } else {
            patients = patientRepository.findByActiveTrue();
        }
        return new PatientsView(patients);
    }

    @GetMapping("/new")
    public ModelAndView newPatient() {
        return viewWithoutMessage(new PatientModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editPatient(@PathVariable Long id) {
        return viewWithoutMessage(patientRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView savePatient(PatientModel patient) {
        try {
            savePatientBusiness.save(patient);
            return listPatients(null, null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(patient, e.getMessage());
        }
    }

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id) {
        PatientModel patient = patientRepository.findById(id).get();
        patient.inactivate();
        try {
            savePatientBusiness.save(patient);
        } catch (BusinessRuleException e) {
        }
        return "redirect:" + HOME;
    }

    private PatientView viewWithoutMessage(PatientModel patient) {
        return viewWithMessage(patient, null);
    }

    private PatientView viewWithMessage(PatientModel patient, String message) {
        Iterable<CityModel> cityOptions = cityRepository.findAll();
        Iterable<BackgroundModel> backgrounds = backgroundRepository.findAll();
        Iterable<MedicineModel> medicines = medicineRepository.findAll();
        List<String> ufOptions = new ArrayList<>();
        for (CityModel cityModel : cityOptions) {
            String uf = cityModel.getUf();
            if (!ufOptions.contains(uf)) {
                ufOptions.add(uf);
            }
        }
        return new PatientView(patient, message, cityOptions, ufOptions, Gender.values(), CivilState.values(), backgrounds, medicines);
    }
}
