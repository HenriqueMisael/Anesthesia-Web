package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveMedicalProcedureBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.MedicalProcedureModel;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.model.repository.MedicalProcedureRepository;
import br.uem.iss.anesthesia.view.MedicalProcedureFormView;
import br.uem.iss.anesthesia.view.MedicalProcedureIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/medicalProcedure")
public class MedicalProcedureController {

    @Autowired
    private MedicalProcedureRepository medicalProcedureRepository;
    @Autowired
    private SaveMedicalProcedureBusiness saveMedicalProcedureBusiness;
    @Autowired
    private ExamRepository examRepository;

    @GetMapping
    public ModelAndView listMedicalProcedures(@RequestParam(value = "name", required = false) String name) {
        Iterable<MedicalProcedureModel> medicalProcedures;
        if (name != null) {
            medicalProcedures = medicalProcedureRepository.findAll();
        } else {
            medicalProcedures = medicalProcedureRepository.findAll();
        }
        return new MedicalProcedureIndexView(medicalProcedures);
    }

    @GetMapping("/new")
    public MedicalProcedureFormView newMedicalProcedure() {
        return viewWithoutMessage(new MedicalProcedureModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editMedicalProcedure(@PathVariable Long id) {
        return viewWithoutMessage(medicalProcedureRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveMedicalProcedure(@Valid MedicalProcedureModel medicalProcedure) {
        try {
            saveMedicalProcedureBusiness.save(medicalProcedure);
            return listMedicalProcedures(null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(medicalProcedure, e.getMessage());
        }
    }

    private MedicalProcedureFormView viewWithoutMessage(MedicalProcedureModel medicalProcedure) {
        return viewWithMessage(medicalProcedure, null);
    }

    private MedicalProcedureFormView viewWithMessage(MedicalProcedureModel medicalProcedure, String message) {
        Iterable<ExamModel> exams = examRepository.findAll();

        return new MedicalProcedureFormView(medicalProcedure, message, exams);
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Optional<MedicalProcedureModel> getMedicalProcedure(@PathVariable Long id) {
        try{
            Optional<MedicalProcedureModel> medicalProcedure;

            medicalProcedure = medicalProcedureRepository.findById(id);
            return medicalProcedure;
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}