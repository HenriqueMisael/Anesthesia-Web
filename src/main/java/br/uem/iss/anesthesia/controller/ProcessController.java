package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.*;
import br.uem.iss.anesthesia.view.ProcessFormView;
import br.uem.iss.anesthesia.view.ProcessIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

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

    @Autowired
    private ExamRepository examRepository;

    @GetMapping
    public ModelAndView listProcess(@RequestParam(value = "filtro_paciente", required = false) PatientModel patient,
                                    @RequestParam(value = "filtro_medico", required = false) DoctorModel doctor) {
        Iterable<ProcessModel> process;
        if(doctor != null && patient != null){
            process = processRepository.findByPatientAndDoctorAndActiveTrue(patient, doctor);
        }else if (doctor != null){
            process = processRepository.findByDoctorAndActiveTrue(doctor);
        }else if(patient != null){
            process = processRepository.findByPatientAndActiveTrue(patient);
        }else{
            process = processRepository.findByActiveTrue();
        }
        return new ProcessIndexView(process, doctorRepository.findByActiveTrue(), patientRepository.findByActiveTrue(), doctor, patient);
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
    @ResponseBody
    public ProcessModel saveProcess(@RequestBody ProcessModel process) {
        try {
            process.setInicialDate(LocalDate.now());
            saveProcessBusiness.save(process);
            return process;
        } catch (Exception e) {
            System.out.println("Deu erro: "+e.getMessage()+e.getClass());
            return null;
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
        return new ProcessIndexView(processRepository.findByActiveTrue(), null, null, null, null);
    }

    private ProcessFormView viewWithoutMessage(ProcessModel process, String metodo) {
        return viewWithMessage(process, null, metodo);
    }

    private ProcessFormView viewWithMessage(ProcessModel process, String message, String metodo) {
        return new ProcessFormView(metodo, process, message, doctorRepository.findByActiveTrue(), patientRepository.findByActiveTrue(),
                medicalProcedureRepository.findByActiveTrue(), examRepository.findByActiveTrue());
    }
}
