package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.model.repository.MedicalProcedureRepository;
import br.uem.iss.anesthesia.view.ProcessFormView;
import br.uem.iss.anesthesia.view.ProcessIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;

    @GetMapping
    public ModelAndView listProcess(@RequestParam(value = "filtro_codigo", required = false) String codigo,
                                    @RequestParam(value = "filtro_nome_paciente", required = false) String nome_paciente,
                                    @RequestParam(value = "filtro_nome_medico", required = false) String nome_medico) {
        Iterable<ProcessModel> process;

        process = processRepository.findByActiveTrue();
        return new ProcessIndexView(process, codigo, nome_paciente, nome_medico);
    }

    @GetMapping("/new")
    public ModelAndView newDoctor() {
        return viewWithoutMessage(new ProcessModel(), "Adicionar ");
    }

    @GetMapping("/{id}")
    public ModelAndView editProcess(@PathVariable Long id) {
        return viewWithoutMessage(processRepository.findById(id).get(), "Editar ");
    }

    @PostMapping
    public ModelAndView savePatient(@Valid ProcessModel process) {
        try {
            saveProcessBusiness.save(process);
            return listProcess(null, null, null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(process, e.getMessage(), "Adicionar ");
        }
    }

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

    private ProcessFormView viewWithoutMessage(ProcessModel process, String metodo) {
        return viewWithMessage(process, null, metodo);
    }

    private ProcessFormView viewWithMessage(ProcessModel process, String message, String metodo) {
        return new ProcessFormView(metodo, process, message, doctorRepository.findByActiveTrue(), patientRepository.findByActiveTrue(),
                medicalProcedureRepository.findByActiveTrue());
    }
}
