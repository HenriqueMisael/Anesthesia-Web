package br.uem.iss.anesthesia.controller;


import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.DoctorFormView;
import br.uem.iss.anesthesia.view.DoctorIndexView;
import br.uem.iss.anesthesia.view.ProcessIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private SaveProcessBusiness saveProcessBusiness;

    @GetMapping
    public ModelAndView listDoctors(@RequestParam(value = "filtro_crm", required = false) String crm, @RequestParam(value = "filtro_name", required = false) String name) {
        Iterable<ProcessModel> process;

        process = processRepository.findByActiveTrue();
        return new ProcessIndexView(process, name, crm);
    }

//    @GetMapping("/new")
//    public ModelAndView newDoctor() {
//        return viewWithoutMessage(new DoctorModel());
//    }
//
//    @GetMapping("/{id}")
//    public ModelAndView editDoctor(@PathVariable Long id) {
//        return viewWithoutMessage(doctorRepository.findById(id).get());
//    }
//
//    @PostMapping
//    public ModelAndView savePatient(@Valid DoctorModel doctor) {
//        try {
//            saveDoctorBusiness.save(doctor);
//            return listDoctors(null, null);
//        } catch (BusinessRuleException e) {
//            return viewWithMessage(doctor, e.getMessage());
//        }
//    }
//
//    @GetMapping("/delete/{id}")
//    public ModelAndView deleteDoctor(@PathVariable Long id) {
//        DoctorModel doctor = doctorRepository.findById(id).get();
//        doctor.inactivate();
//        try {
//            saveDoctorBusiness.save(doctor);
//        } catch (BusinessRuleException e) {
//        }
//        return new DoctorIndexView(doctorRepository.findByActiveTrue(), null, null);
//    }

    private DoctorFormView viewWithoutMessage(DoctorModel doctor) {
        return viewWithMessage(doctor, null);
    }

    private DoctorFormView viewWithMessage(DoctorModel doctor, String message) {
        return new DoctorFormView(doctor, message);
    }
}
