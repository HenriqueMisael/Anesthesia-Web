package br.uem.iss.anesthesia.controller;


import br.uem.iss.anesthesia.model.business.SaveDoctorBusiness;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.view.DoctorFormView;
import br.uem.iss.anesthesia.view.DoctorIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SaveDoctorBusiness saveDoctorBusiness;

    @GetMapping
    public ModelAndView listDoctors(@RequestParam(value = "filtro_crm", required = false) String crm,
                                    @RequestParam(value = "filtro_name", required = false) String name,
                                    @RequestParam(value = "filtro_ativo", required = false, defaultValue = "true") boolean ativo) {
        Iterable<DoctorModel> doctor;
        crm     = ((crm == null) ? "" : crm);
        name    = ((name == null) ? "" : name);
        if (ativo) {
            doctor = doctorRepository.findByCrmContainingAndNameContainingAndActiveTrue(crm, name);
        }else{
            doctor = doctorRepository.findByCrmContainingAndNameContainingAndActiveFalse(crm, name);
        }
        return new DoctorIndexView(doctor, name, crm, ativo);
    }

    @GetMapping("/new")
    public ModelAndView newDoctor() {
        return viewWithoutMessage(new DoctorModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editDoctor(@PathVariable Long id) {
        return viewWithoutMessage(doctorRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView savePatient(@Valid DoctorModel doctor) {
        try {
            saveDoctorBusiness.save(doctor);
            return listDoctors(null, null, true);
        } catch (BusinessRuleException e) {
            return viewWithMessage(doctor, e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteDoctor(@PathVariable Long id) {
        DoctorModel doctor = doctorRepository.findById(id).get();
        doctor.inactivate();
        try {
            saveDoctorBusiness.save(doctor);
        } catch (BusinessRuleException e) {
        }
        return new DoctorIndexView(doctorRepository.findByActiveTrue(), null, null, true);
    }

    private DoctorFormView viewWithoutMessage(DoctorModel doctor) {
        return viewWithMessage(doctor, null);
    }

    private DoctorFormView viewWithMessage(DoctorModel doctor, String message) {
        return new DoctorFormView(doctor, message);
    }
}
