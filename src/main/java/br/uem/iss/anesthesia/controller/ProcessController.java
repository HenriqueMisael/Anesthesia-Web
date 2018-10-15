package br.uem.iss.anesthesia.controller;


import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.ProcessFormView;
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

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ModelAndView listDoctors(@RequestParam(value = "filtro_codigo", required = false) String codigo,
                                    @RequestParam(value = "filtro_nome_paciente", required = false) String nome_paciente,
                                    @RequestParam(value = "filtro_nome_medico", required = false) String nome_medico) {
        Iterable<ProcessModel> process;

        process = processRepository.findByActiveTrue();
        return new ProcessIndexView(process, codigo, nome_paciente, nome_medico);
    }

    @GetMapping("/new")
    public ModelAndView newDoctor() {
        return viewWithoutMessage(new ProcessModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editProcess(@PathVariable Long id) {
        return viewWithoutMessage(processRepository.findById(id).get());
    }
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
    @GetMapping("/delete/{id}")
    public ModelAndView deleteProcess(@PathVariable Long id) {
        ProcessModel process = processRepository.findById(id).get();
        process.inactivate();
        try {
            saveProcessBusiness.save(process);
        } catch (BusinessRuleException e) {
        }
        return new ProcessIndexView(processRepository.findByActiveTrue(), null, null, null);
    }

    private ProcessFormView viewWithoutMessage(ProcessModel process) {
        return viewWithMessage(process, null);
    }

    private ProcessFormView viewWithMessage(ProcessModel process, String message) {
        return new ProcessFormView(process, message, doctorRepository.findByActiveTrue(), patientRepository.findByActiveTrue());
    }
}
